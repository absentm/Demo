# coding=utf-8

from datetime import datetime


def get_current_utc_time():
    time_now = datetime.utcnow()
    time_now_iso = time_now.isoformat()[:-3] + 'Z'
    return time_now_iso
