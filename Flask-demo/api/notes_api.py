# coding=utf-8

import datetime

from apscheduler.executors.pool import ThreadPoolExecutor
from apscheduler.schedulers.background import BackgroundScheduler
from flask import Blueprint, jsonify

from common import global_variable
from common.date_util import get_current_utc_time
from db.sql_utils import persist_data
from db.sql_utils import select_all
from log.log_util import register_logger

note_blueprint = Blueprint("note", __name__)
log = register_logger("demo")


@note_blueprint.route('/list')
def list_all_notes():
    log.debug("Query all note data...")
    sql = "select * from note"
    result = select_all(sql)
    result_dic = {
        "code": 0,
        "data": result
    }

    return jsonify(result_dic)


@note_blueprint.route('/add')
def insert_one_notes():
    log.debug("Insert one note data...")
    sql = '''
        INSERT INTO note (
        id, title, content, create_time, update_time, username
        ) VALUES (?, ?, ?, ?, ?, ?)'''
    values = (
        None, "A nice dog", "Here is a cute dog, wanna a boll to play",
        get_current_utc_time(), get_current_utc_time(), "xiaoming"
    )
    persist_data(sql, [values])
    return "Success!"


@note_blueprint.route('/update')
def update_one_notes():
    log.debug("Update one note data...")
    sql = "UPDATE note set title=?, content=?, update_time=? where id=1"
    values = ("hahahah", "hhhhhhhhhhhhhhhhhhhhhhhhhhh", get_current_utc_time())
    persist_data(sql, [values])
    return "Success!"


@note_blueprint.route('/delete')
def delete_one_notes():
    log.debug("Update one note data...")
    sql = "delete from note where id=3"
    persist_data(sql)
    return "Success!"


@note_blueprint.route('/timed')
def auto_report_log_msg():
    log.debug("Start timed msg...")
    executors = {
        "default": ThreadPoolExecutor(10)
    }
    app_scheduler = BackgroundScheduler(executors)
    app_scheduler.start()

    global_variable.set_global_variable("app_scheduler", app_scheduler)
    app_job_id = "note_auto_record"
    app_scheduler.add_job(
        report_app_note_msg,
        'interval',
        minutes=1,
        id=app_job_id,
        args=["Say hello am here!"],
        name=app_job_id,
        next_run_time=datetime.datetime.now()
    )

    return "Success!"


@note_blueprint.route('/stoptimed')
def stop_auto_report_log_msg():
    log.debug("Stop timed msg...")

    app_scheduler = global_variable.get_global_variable("app_scheduler")
    app_job_id = "note_auto_record"

    if app_scheduler and app_scheduler.get_job(app_job_id):
        log.debug("Get app job id: " + str(app_scheduler.get_job(app_job_id)))
        app_scheduler.remove_job(app_job_id)

    log.debug("Remove job end!")
    return "Success!"


def report_app_note_msg(msg):
    """
    Auto report msg
    :param msg:
    :return:
    """
    print msg
    log.debug(msg)
