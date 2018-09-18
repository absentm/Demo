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
    Test args
    """
    print "first normal arg:", f_arg
    for arg in argv:
        print "another arg through *argv:", arg


def greet_me(**kwargs):
    """
    Test kwargs
    """
    for key, value in kwargs.items():
        print "{0} : {1}".format(key, value)


def test_map():
    """
    Test map
    Map会将一个函数映射到一个输入列表的所有元素上
    """
    items = [1, 2, 3, 4, 5]
    squared = list(map(lambda x: x**2, items))
    return squared


def test_filter():
    """
    Test filter
    filter过滤列表中的元素，并且返回一个由所有符合要求的元素所构成的列表，
    符合要求即函数映射到该元素时返回值为True
    """
    number_list = range(-5, 5)
    less_than_zero = filter(lambda x: x < 0, number_list)
    return less_than_zero


def test_reduce():
    """
    Test reduce
    当需要对一个列表进行一些计算并返回结果时，Reduce 是个非常有用的函数。
    """
    from functools import reduce
    product = reduce((lambda x, y: x * y), [1, 2, 3, 4])
    return product


def test_three_op():
    """
    Test three op
    """
    # first test
    # 如果条件为真，返回真 否则返回假
    # condition_is_true if condition else condition_is_false 
    is_fat = True
    state = "fat" if is_fat else "not fat"
    print state

    # second test
    # (返回假，返回真)[真或假]
    # (if_test_is_false, if_test_is_true)[test]
    fat = True
    fitness = ("skinny", "fat")[fat]
    print "Ali is ", fitness



if __name__ == '__main__':
    test_var_args('yasoob', 'python', 'eggs', 'test')
    greet_me(name="yasoob")
    str = "Hello, {0}: I am {1}, nice to meet you".format("Any", "Tiny")
    print str


    # run test_map
    print test_map()

    # run test_filter
    print test_filter()

    # run test_reduce
    print test_reduce()

    # run test_three_op()
    print test_three_op()
    