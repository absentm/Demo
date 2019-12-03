# coding=utf-8

from flask import Flask, jsonify

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


@app.route('/')
def index():
    log.debug("Query home index...")
    return "Hello, welcome!"


if __name__ == '__main__':
    app.run(debug=True)
