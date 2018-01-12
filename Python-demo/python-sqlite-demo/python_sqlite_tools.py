# coding=utf-8

import sqlite3
import os


class SqliteTool(object):
    # is or not print sql
    is_print_sql = True

    def __init__(self, db_path):
        self.db_path = db_path

    # get the connection of sqlite
    def get_connection(self):
        try:
            connection = sqlite3.connect(self.db_path)
            # conn.text_factory = lambda x: unicode(x, 'utf-8', 'ignore')
            connection.text_factory = str

            if (os.path.exists(self.db_path)) and (os.path.isfile(self.db_path)):
                print("The path: [{}] is on the drive！".format(self.db_path))
                return connection
        except sqlite3.OperationalError, e:
            print "Error: %s" % e

    # get db cursor
    def get_cursor(self, connection):
        if connection is not None:
            return connection.cursor()
        else:
            return self.get_connection().cursor()

    # close cursor
    def close_cursor(self, cursor):
        try:
            cursor.close()
        except sqlite3.OperationalError, e:
            print "Error: %s" % e

    # close connection
    def close_connection(self, connection):
        try:
            connection.close()
        except sqlite3.OperationalError, e:
            print "Error: %s" % e

    # close all connection of cursor and conn
    def close_all(self, connection, cursor):
        try:
            cursor.close()
            connection.close()
        except sqlite3.OperationalError, e:
            print "Error: %s" % e

    # create database table
    def create_db_table(self, sql):
        if (sql is not None) and (sql != ""):
            connection = self.get_connection()
            cursor = self.get_cursor(connection)

            if self.is_print_sql:
                print('Exec sql: [{}]'.format(sql))
                cursor.execute(sql)
                connection.commit()

            print("Create table success!")
            self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(sql))

    # drop database table
    def drop_db_table(self, table):
        if (table is not None) and (table != ""):
            sql = 'DROP TABLE IF EXISTS ' + table
            if self.is_print_sql:
                print("Exec sql: [{}]".format(sql))
            connection = self.get_connection()
            cursor = self.get_cursor(connection)

            cursor.execute(sql)
            connection.commit()
            print("Delete table [{}] success!".format(table))
            self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(table))

    # insert data to table
    def insert(self, sql, data):
        if sql is not None and sql != "":
            if data is not None:
                connection = self.get_connection()
                cursor = self.get_cursor(connection)

                for value in data:
                    if self.is_print_sql:
                        print("Exec sql: [{}], Params: [{}]".format(sql, value))
                        cursor.execute(sql, value)
                    connection.commit()
                self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(sql))

    def fetch_all(self, sql):
        if sql is not None and sql != "":
            connection = self.get_connection()
            cursor = self.get_cursor(connection)

            if self.is_print_sql:
                print("Exec sql: [{}]".format(sql))
                cursor.execute(sql)
            rows = cursor.fetchall()

            if len(rows) > 0:
                for row in range(len(rows)):
                    print(rows[row])
            self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(sql))

    # search one data only
    def fetch_one(self, sql, data):
        if (sql is not None) and (sql != ""):
            if data is not None:
                # replace
                item = (data,)
                connection = self.get_connection()
                cursor = self.get_cursor(connection)

                if self.is_print_sql:
                    print("Exec sql: [{}], Params: [{}]".format(sql, data))
                cursor.execute(sql, item)
                rows = cursor.fetchall()

                if len(rows) > 0:
                    for element in range(len(rows)):
                        print(rows[element])
                self.close_all(connection, cursor)
            else:
                print("The [{}] is None!".format(data))
        else:
            print("The [{}] is empty or None!".format(sql))

    # update data
    def update(self, sql, data):
        if (sql is not None) and (sql != ""):
            if data is not None:
                connection = self.get_connection()
                cursor = self.get_cursor(connection)

                for item in data:
                    if self.is_print_sql:
                        print("Exec sql: [{}], Params :[{}]".format(sql, item))
                        cursor.execute(sql, item)
                    connection.commit()
                self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(sql))

    # delete data
    def delete(self, sql, data):
        if (sql is not None) and (sql != ""):
            if data is not None:
                connection = self.get_connection()
                cursor = self.get_cursor(connection)

                for item in data:
                    if self.is_print_sql:
                        print("Exec sql: [{}], Params :[{}]".format(sql, item))
                        cursor.execute(sql, item)
                    connection.commit()
                self.close_all(connection, cursor)
        else:
            print("The [{}] is empty or None!".format(sql))
