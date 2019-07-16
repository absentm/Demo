#!/usr/bin/env python
# -*- coding: utf-8 -*-


from apscheduler.schedulers.blocking import BlockingScheduler
from datetime import datetime


def hello_job():
    print "Say hello at %s" % datetime.now()


def run_job():
    scheduler = BlockingScheduler()
    scheduler.add_job(hello_job, "interval", seconds=5)
    print scheduler.get_jobs()
    scheduler.start()

