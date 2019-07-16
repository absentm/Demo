#!/usr/bin/env python
# -*- coding: utf-8 -*-

# from src.interval_job import run_job
from lib.time_util import *
from src.bg_job import run_job


def test_time():
    print get_now_timestamp()
    print get_now_time_ymdhms()
    print get_now_time_ymdhm()
    print get_now_time_ymd()
    print timestamp_to_readable(get_now_timestamp(), "xxxx")


def test_aps_demo():
    run_job()
    # interval_show_hello()


def main_test():
    """
    Main running entry
    :return:
    """
    test_time()
    # test_aps_demo()


if __name__ == '__main__':
    main_test()
