#!/usr/bin/env python
# -*- coding: utf-8 -*-

from __future__ import absolute_import, division, unicode_literals

import codecs
import logging
import multiprocessing
import os
import re
import sys
import threading
import time
import traceback
from logging.handlers import BaseRotatingHandler, _MIDNIGHT
from stat import ST_MTIME


def register_handler(logger=None):
    """Wraps the handlers in the given Logger with an MultiProcHandler.

    :param logger: whose handlers to wrap. By default, the root logger.
    """
    if logger is None:
        logger = logging.getLogger()

    for i, orig_handler in enumerate(list(logger.handlers)):
        handler = MultiProcHandler(
            'mp-handler-{0}'.format(i), sub_handler=orig_handler
        )

        logger.removeHandler(orig_handler)
        logger.addHandler(handler)


class MultiProcHandler(logging.Handler):

    def __init__(self, name, sub_handler=None):
        super(MultiProcHandler, self).__init__()

        if sub_handler is None:
            sub_handler = logging.StreamHandler()
        self.sub_handler = sub_handler

        self.setLevel(self.sub_handler.level)
        self.setFormatter(self.sub_handler.formatter)

        self.queue = multiprocessing.Queue(-1)
        self._is_closed = False
        # The thread handles receiving records asynchronously.
        self._receive_thread = threading.Thread(target=self._receive, name=name)
        self._receive_thread.daemon = True
        self._receive_thread.start()

    def setFormatter(self, fmt):
        super(MultiProcHandler, self).setFormatter(fmt)
        self.sub_handler.setFormatter(fmt)

    def _receive(self):
        while not (self._is_closed and self.queue.empty()):
            try:
                record = self.queue.get()
                self.sub_handler.emit(record)
            except (KeyboardInterrupt, SystemExit):
                raise
            except EOFError:
                break
            except:
                traceback.print_exc(file=sys.stderr)

        self.queue.close()
        self.queue.join_thread()

    def _send(self, s):
        self.queue.put_nowait(s)

    def _format_record(self, record):
        if record.args:
            record.msg = record.msg % record.args
            record.args = None
        if record.exc_info:
            self.format(record)
            record.exc_info = None

        return record

    def emit(self, record):
        try:
            s = self._format_record(record)
            self._send(s)
        except (KeyboardInterrupt, SystemExit):
            raise
        except:
            self.handleError(record)

    def close(self):
        if not self._is_closed:
            self._is_closed = True
            self._receive_thread.join(5.0)  # Waits for receive queue to empty.

            self.sub_handler.close()
            super(MultiProcHandler, self).close()


class TimedRotatingFileHandler(BaseRotatingHandler):
    """
    Handler for logging to a file, rotating the logs file at certain timed
    intervals.

    If backup_count is > 0, when rollover is done, no more than backup_count
    files are kept - the oldest ones are deleted.
    """

    def __init__(self, filename, when='h', interval=1, backup_count=0,
                 encoding=None, delay=False, utc=False):
        self.when = when.upper()
        self.backup_count = backup_count
        self.utc = utc
        self.base_filename = filename

        if self.when == 'S':
            self.interval = 1  # one second
            self.suffix = "%Y-%m-%d_%H-%M-%S"
            self.extMatch = r"^\d{4}-\d{2}-\d{2}_\d{2}-\d{2}-\d{2}$"
        elif self.when == 'M':
            self.interval = 60  # one minute
            self.suffix = "%Y-%m-%d_%H-%M"
            self.extMatch = r"^\d{4}-\d{2}-\d{2}_\d{2}-\d{2}$"
        elif self.when == 'H':
            self.interval = 60 * 60  # one hour
            self.suffix = "%Y-%m-%d_%H"
            self.extMatch = r"^\d{4}-\d{2}-\d{2}_\d{2}$"
        elif self.when == 'D' or self.when == 'MIDNIGHT':
            self.interval = 60 * 60 * 24  # one day
            self.suffix = "%Y-%m-%d"
            self.extMatch = r"^\d{4}-\d{2}-\d{2}$"
        elif self.when.startswith('W'):
            self.interval = 60 * 60 * 24 * 7  # one week

            if len(self.when) != 2:
                raise ValueError(
                    "You must specify a day for weekly rollover "
                    "from 0 to 6 (0 is Monday): %s" % self.when
                )

            if self.when[1] < '0' or self.when[1] > '6':
                raise ValueError(
                    "Invalid day specified for weekly rollover: %s" % self.when)
            self.day_of_week = int(self.when[1])
            self.suffix = "%Y-%m-%d"
            self.extMatch = r"^\d{4}-\d{2}-\d{2}$"
        else:
            raise ValueError(
                "Invalid rollover interval specified: %s" % self.when)

        self.extMatch = re.compile(self.extMatch)
        self.current_filename = self._compute_fn()
        BaseRotatingHandler.__init__(self, filename, 'a', encoding, delay)
        self.interval = self.interval * interval  # multiply by units requested

        if os.path.exists(filename):
            t = os.stat(filename)[ST_MTIME]
        else:
            t = int(time.time())
        self.rollover_time = self.compute_rollover(t)

    def compute_rollover(self, current_time):
        """
        Work out the rollover time based on the specified time.
        """
        result = current_time + self.interval

        # If we are rolling over at midnight or weekly, then the interval
        # is already known. What we need to figure out is WHEN the next
        # interval is.  In other words, if you are rolling over at midnight,
        # then your base interval is 1 day, but you want to start that one day
        # clock at midnight, not now.  So, we have to fudge the rollover_time
        # value in order to trigger the first rollover at the right time.
        # After that, the regular interval will take care of the rest.
        # Note that this code doesn't care about leap seconds.
        if self.when == 'MIDNIGHT' or self.when.startswith('W'):
            # This could be done with less code, but I wanted it to be clear
            if self.utc:
                t = time.gmtime(current_time)
            else:
                t = time.localtime(current_time)
            current_hour = t[3]
            current_minute = t[4]
            current_second = t[5]
            # r is the number of seconds left between now and midnight
            r = _MIDNIGHT - (
                    (current_hour * 60 + current_minute) * 60 + current_second)
            result = current_time + r

            # If we are rolling over on a certain day, add in the number of
            # days until the next rollover, but offset by 1 since we just
            # calculated the time until the next day starts.  There are three
            # cases:
            # Case 1) The day to rollover is today; in this case, do nothing
            # Case 2) The day to rollover is further in the interval (i.e.,
            #         today is day 2 (Wednesday) and rollover is on
            #         day 6 (Sunday).  Days to next rollover is simply
            #         6 - 2 - 1, or 3.
            # Case 3) The day to rollover is behind us in the interval (i.e.,
            #         today is day 5 (Saturday) and rollover is on day 3
            #         (Thursday). Days to rollover is 6 - 5 + 3, or 4.
            #         In this case, it's the number of days left in the current
            #         week (1) plus the number of days in the next week until
            #         the rollover day (3).
            # The calculations described in 2) and 3) above need to have a day
            # added. This is because the above time calculation takes us to
            # midnight on this day, i.e. the start of the next day.
            if self.when.startswith('W'):
                day = t[6]  # 0 is Monday
                if day != self.day_of_week:
                    if day < self.day_of_week:
                        daysToWait = self.day_of_week - day
                    else:
                        daysToWait = 6 - day + self.day_of_week + 1
                    new_rollover_time = result + (daysToWait * (60 * 60 * 24))
                    if not self.utc:
                        dest_now = t[-1]
                        dest_rollover_time = time.localtime(new_rollover_time)[
                            -1]
                        if dest_now != dest_rollover_time:
                            if not dest_now:
                                addend = -3600
                            else:
                                addend = 3600
                            new_rollover_time += addend
                    result = new_rollover_time
        return result

    def should_rollover(self, record):
        """
        Determine if rollover should occur.

        record is not used, as we are just comparing times, but it is needed so
        the method signatures are the same
        """
        if self.current_filename != self._compute_fn():
            return True
        # print "No need to rollover: %d, %d" % (t, self.rollover_time)
        return 0

    def _compute_fn(self):
        return self.base_filename + '.' + \
               time.strftime(self.suffix, time.localtime())

    def get_files_to_delete(self):
        """
        Determine the files to delete when rolling over.

        More specific than the earlier method, which just used glob.glob().
        """
        dir_name, base_name = os.path.split(self.base_filename)
        file_names = os.listdir(dir_name)
        result = []
        prefix = base_name + "."
        plen = len(prefix)

        for fileName in file_names:
            if fileName[:plen] == prefix:
                suffix = fileName[plen:]
                if self.extMatch.match(suffix):
                    result.append(os.path.join(dir_name, fileName))

        result.sort()
        if len(result) < self.backup_count:
            result = []
        else:
            result = result[:len(result) - self.backup_count]
        return result

    def doRollover(self):
        """
        do a rollover; in this case, a date/time stamp is appended to the
        filename when the rollover happens.  However, you want the file to be
        named for the start of the interval, not the current time.
        If there is a backup count, then we have to get a list of matching
        filenames, sort them and remove the one with the oldest suffix.
        """
        if self.stream:
            self.stream.close()
            self.stream = None

        # get the time that this sequence started at and make it a TimeTuple
        current_time = int(time.time())
        dest_now = time.localtime(current_time)[-1]
        t = self.rollover_time - self.interval

        if self.utc:
            time_tuple = time.gmtime(t)
        else:
            time_tuple = time.localtime(t)
            dstThen = time_tuple[-1]
            if dest_now != dstThen:
                if dest_now:
                    addend = 3600
                else:
                    addend = -3600
                time_tuple = time.localtime(t + addend)

        self.current_filename = self._compute_fn()
        if self.backup_count > 0:
            for s in self.get_files_to_delete():
                os.remove(s)

        # print "%s -> %s" % (self.base_filename, dfn)
        self.stream = self._open()
        new_rollover_time = self.compute_rollover(current_time)
        while new_rollover_time <= current_time:
            new_rollover_time = new_rollover_time + self.interval

        # If DST changes and midnight or weekly rollover, adjust for this.
        if (self.when == 'MIDNIGHT' or self.when.startswith(
                'W')) and not self.utc:
            dest_rollover_time = time.localtime(new_rollover_time)[-1]
            if dest_now != dest_rollover_time:
                if not dest_now:
                    addend = -3600
                else:
                    addend = 3600
                new_rollover_time += addend
        self.rollover_time = new_rollover_time

    def _open(self):
        if self.encoding is None:
            stream = open(self.current_filename, self.mode)
        else:
            stream = codecs.open(self.current_filename, self.mode,
                                 self.encoding)

        if os.path.exists(self.base_filename):
            try:
                os.remove(self.base_filename)
            except OSError:
                pass
        try:
            os.symlink(self.current_filename, self.base_filename)
        except OSError:
            pass

        return stream
