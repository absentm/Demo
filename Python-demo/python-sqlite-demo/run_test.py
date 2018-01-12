# coding=utf-8

import os
from python_sqlite_tools import SqliteTool


# test delete table
def drop_table_test(test_db):
    print('delete table testing...')
    test_db.drop_db_table('test_table')


# create table test
def create_table_test(test_db):
    print('create table testing...')

    create_table_sql = '''CREATE TABLE 'test_table' (
                          'id' int(11) NOT NULL,
                          'name' varchar(20) NOT NULL,
                          'gender' varchar(8) DEFAULT NULL,
                          'age' int(11) DEFAULT NULL,
                          'address' varchar(200) DEFAULT NULL,
                          'phone' varchar(20) DEFAULT NULL,
                           PRIMARY KEY ('id')
                        )'''
    test_db.create_db_table(create_table_sql)


# insert test
def insert_test(test_db):
    print('Insert testing...')

    sql = '''INSERT INTO test_table values (?, ?, ?, ?, ?, ?)'''
    data = [(1, 'Dave', 'man', 20, 'BeiJing', '183****62'),
            (2, 'test_table', 'man', 22, 'ShenZhen', '178****63'),
            (3, 'Oracle', 'women', 18, 'GuangDong', '177****87'),
            (4, 'Python', 'women', 21, 'ShangHai', '135****32')]
    test_db.insert(sql, data)


# search all test
def fetch_all_test(test_db):
    print('Search all testing...')

    fetchall_sql = '''SELECT * FROM test_table'''
    test_db.fetch_all(fetchall_sql)


# search one test
def fetch_one_test(test_db):
    print('Search one data testing...')

    fetchone_sql = 'SELECT * FROM test_table WHERE id = ? '
    data = 1
    # fetchone_sql = 'SELECT * FROM test_table WHERE address = ? '
    # data = 'ShenZhen'
    test_db.fetch_one(fetchone_sql, data)


# update test
def update_test(test_db):
    print('Update tesing...')

    update_sql = 'UPDATE test_table SET name = ? WHERE id = ? '
    data = [('Oracle', 1),
            ('MySQL', 2)]
    test_db.update(update_sql, data)


# delete data test
def delete_test(test_db):
    print('Detele data testing...')

    delete_sql = 'DELETE FROM test_table WHERE NAME = ? AND id = ? '
    data = [('Oracle', 1),
            ('python', 2)]
    test_db.delete(delete_sql, data)


# print menu
def show_menu():
    print "******* Welcome Menu *******\n"
    print "  1 ---> Create table test."
    print "  2 ---> Drop table test."
    print "  3 ---> Insert data test."
    print "  4 ---> Search only test."
    print "  5 ---> Search all test."
    print "  6 ---> Update data test."
    print "  7 ---> Delete data test."
    print "  0 ---> Quit."
    print "\n****************************"
    print ">> Input your choice: "


# main test
def main_test():
    db_file_path = "test.db"
    test_db = SqliteTool(db_file_path)

    # clean screen and show menu
    os.system("cls")
    show_menu()
    input_value = input()

    while input_value:
        # run test case
        if (input_value >= 1) and (input_value <= 7):
            if input_value == 1:
                create_table_test(test_db)
            elif input_value == 2:
                drop_table_test(test_db)
            elif input_value == 3:
                insert_test(test_db)
            elif input_value == 4:
                fetch_one_test(test_db)
            elif input_value == 5:
                fetch_all_test(test_db)
            elif input_value == 6:
                update_test(test_db)
            elif input_value == 7:
                delete_test(test_db)
            elif input_value == 0:
                break
        else:
            print "Error: input value is wrong!"

        print ">> Input your choice: "
        input_value = input()


if __name__ == '__main__':
    main_test()
