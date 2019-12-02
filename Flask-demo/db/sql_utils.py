# coding=utf-8

import sqlite3
import traceback

from common.constants import DB_FILE_PATH
from log.log_util import register_logger

log = register_logger("demo")


def get_db_conn(db_path=DB_FILE_PATH):
    """
    Get db connection
    :param db_path: db file path
    :return:
    """
    conn = None
    try:
        conn = sqlite3.connect(db_path)
    except Exception:
        log.error(traceback.format_exc())

    return conn


def close_db_conn(conn):
    """
    Close note db connection
    :param conn: db connection
    :return:
    """
    try:
        if conn is not None:
            conn.close()
    finally:
        if conn is not None:
            conn.close()


def select_one(sql, data=None):
    """
    Query data by sql
    :param sql: sql
    :param data: data
    :return:
    """
    log.debug("run sql: {}, data: {}".format(sql, data))

    row = None
    conn = get_db_conn()

    try:
        if data is not None:
            row = conn.execute(sql, data).fetchone()
        else:
            row = conn.execute(sql).fetchone()
    except Exception:
        log.error(traceback.format_exc())
    finally:
        close_db_conn(conn)

    return row


def select_all(sql, data=None):
    """
    Query all data by sql and data
    :param sql: sql
    :param data: data
    :return:
    """
    log.debug("run sql: {}, data: {}".format(sql, data))

    row = []
    conn = get_db_conn()

    try:
        if data is not None:
            row = conn.execute(sql, data).fetchall()
        else:
            row = conn.execute(sql).fetchall()
    except Exception:
        log.error("fetchall error: {}".format(traceback.format_exc()))
    finally:
        close_db_conn(conn)

    return row


def persist_data(sql, data=None):
    """
    Persist sql data
    :param sql: sql
    :param data: data
    :return:
    """
    log.debug("run sql: {}, data: {}".format(sql, data))

    if type(sql) is not list:
        sql = [sql]
        data = [data]

    conn = get_db_conn()
    try:
        with conn:
            for index in range(len(sql)):
                if data[index] is not None:
                    conn.executemany(sql[index], data[index])
                else:
                    conn.execute(sql[index])
    except Exception:
        log.error("execute error: {}".format(traceback.format_exc()))
    finally:
        close_db_conn(conn)


def persist_many_data(data_list):
    """
    Persist data with batch
    :param data_list: list of data
    :return:
    """
    sql, data = [], []
    for index in range(len(data_list)):
        sql.append(data_list[index].get("sql"))
        data.append(data_list[index].get("data"))

    persist_data(sql, data)

