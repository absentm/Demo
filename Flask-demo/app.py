# coding=utf-8

from flask import Flask, jsonify
from db.sql_utils import select_all
from log.log_util import register_logger

app = Flask(__name__)

log = register_logger("demo")


def get_all_notes():
    """
    Get all notes from db
    :return:
    """

    return None


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


@app.route('/')
def index():
    log.debug("Query home index...")
    return "Hello, welcome!"


if __name__ == '__main__':
    app.run(debug=True)
