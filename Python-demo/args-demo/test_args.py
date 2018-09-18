# coding:utf-8

"""
 # test_args.py
 #
 # Copyright(C) by AbsentM. 2018
 #
 # Author: AbsentM
 # Date:   2018/09/18
 #
 # Description:
 # test python args and kwargs paramter
 #
"""

def test_var_args(f_arg, *argv):
    """
    test args
    """
    print "first normal arg:", f_arg
    for arg in argv:
        print "another arg through *argv:", arg


def greet_me(**kwargs):
    """
    test kwargs
    """
    for key, value in kwargs.items():
        print "{0} : {1}".format(key, value)


if __name__ == '__main__':
    test_var_args('yasoob', 'python', 'eggs', 'test')
    greet_me(name="yasoob")
    str = "Hello, {0}: I am {1}, nice to meet you".format("Any", "Tiny")
    print str