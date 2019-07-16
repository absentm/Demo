#!/usr/bin/env python
# -*- coding: utf-8 -*-


from apscheduler.executors.pool import ThreadPoolExecutor, ProcessPoolExecutor
from apscheduler.jobstores.sqlalchemy import SQLAlchemyJobStore
from apscheduler.schedulers.background import BackgroundScheduler

from lib.time_util import get_now_time_ymdhms

jobstores = {
    'default': SQLAlchemyJobStore(url='sqlite:///jobs.sqlite')
}

executes = {
    'default': ThreadPoolExecutor(20),
    'processpool': ProcessPoolExecutor(5)
}

job_default = {
    'coalesce': False,
    'max_instances': 3
}

scheduler = BackgroundScheduler(
    jobstores=jobstores,
    executes=executes,
    job_default=job_default
)


def hello_job():

    print "Say hello at %s" % get_now_time_ymdhms()


def run_job():
    scheduler.add_job(hello_job, "interval", seconds=5)
    print scheduler.get_jobs()
    scheduler.start()

