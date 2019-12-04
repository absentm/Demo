# coding=utf-8

import datetime

from apscheduler.executors.pool import ThreadPoolExecutor
from apscheduler.schedulers.background import BackgroundScheduler
from flask import Flask, jsonify

from common import global_variable
from common.data_util import get_current_utc_time
from db.sql_utils import persist_data
from db.sql_utils import select_all
from log.log_util import register_logger

app = Flask(__name__)

log = register_logger("demo")


@app.route('/notes/api/v1.0/list')
def list_all_notes():
    log.debug("Query all note data...")
    sql = "select * from note"
    result = select_all(sql)
    result_dic = {
        "code": 0,
        "data": result
    }

    return jsonify(result_dic)


@app.route('/notes/api/v1.0/add')
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


@app.route('/notes/api/v1.0/update')
def update_one_notes():
    log.debug("Update one note data...")
    sql = "UPDATE note set title=?, content=?, update_time=? where id=1"
    values = ("hahahah", "hhhhhhhhhhhhhhhhhhhhhhhhhhh", get_current_utc_time())
    persist_data(sql, [values])
    return "Success!"


@app.route('/notes/api/v1.0/delete')
def delete_one_notes():
    log.debug("Update one note data...")
    sql = "delete from note where id=3"
    persist_data(sql)
    return "Success!"


@app.route('/notes/api/v1.0/timed')
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


@app.route('/notes/api/v1.0/stoptimed')
def stop_auto_report_log_msg():
    log.debug("Stop timed msg...")

    app_scheduler = global_variable.get_global_variable("app_scheduler")
    app_job_id = "note_auto_record"

    if app_scheduler and app_scheduler.get_job(app_job_id):
        log.debug("Get app job id: " + str(app_scheduler.get_job(app_job_id)))
        app_scheduler.remove_job(app_job_id)

    log.debug("Remove job end!")
    return "Success!"


@app.route('/')
def index():
    log.debug("Query home index...")
    return "Hello, welcome!"


def report_app_note_msg(msg):
    """
    Auto report msg
    :param msg:
    :return:
    """
    print msg
    log.debug(msg)


if __name__ == '__main__':
    app.run(debug=True)
