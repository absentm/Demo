# func_test.py
# coding=utf-8

# 要“试用”某一新的特性，就可以通过导入__future__模块的某些功能来实现。
from __future__ import division
import math
import functools

try:
    from cStringIO import StringIO
except ImportError:
    from StringIO import StringIO


def my_add(x, y, f):
    "测试函数作为参数"
    return f(x) + f(y)


def my_map(x):
    "测试map()函数"
    return x * x


def format_name(s):
    "规范化名字书写"
    return s[0].upper() + s[1:].lower()


def my_reduce(x, y):
    "测试reduce()函数"
    return x * y


def is_odd(x):
    "是否为偶数"
    return x % 2 == 0


def is_sqrt(x):
    "测试是否为平方根"
    r = int(math.sqrt(x))
    return r * r == x


def reversed_cmp(x, y):
    "数组降序排列"
    if x > y:
        return -1
    if x < y:
        return 1

    return 0


def cmp_ignore_case(s1, s2):
    "字符串忽略大小写排序"
    u1 = s1.lower()
    u2 = s2.lower()

    if u1 < u2:
        return -1
    if u1 > u2:
        return 1
    return 0


def calc_sum(lst):
    "测试返回函数，延迟计算"

    def lazy_sum():
        return sum(lst)

    return lazy_sum


def calc_prod(lst):
    "返回函数，延迟计算连乘"

    def lazy_prod():
        def f(x, y):
            return x * y

        return reduce(f, lst, 1)

    return lazy_prod


def count():
    "测试闭包"
    fs = []
    for i in range(1, 4):
        def f(j):
            def g():
                return j * j

            return g

        r = f(i)
        fs.append(r)
    return fs


import time


def performance(f):
    "测试装饰器， decorator"

    def fn(*args, **kw):
        t1 = time.time()
        r = f(*args, **kw)
        t2 = time.time()
        print 'call %s() in %fs' % (f.__name__, (t2 - t1))
        return r

    return fn


@performance
def factorial(n):
    "调用装饰器"
    return reduce(lambda x, y: x * y, range(1, n + 1))


def bais_func():
    "测试偏函数"
    int2 = functools.partial(int, base=2)
    return int2


def main_test():
    "主程序测试"
    print "函数作为参数测试： ", my_add(-5, 10, abs)

    print "map()函数测试： ", map(my_map, [1, 2, 3])
    print map(format_name, ['adam', 'LISA', 'barT'])
    print "reduce()函数测试： ", reduce(my_reduce, [1, 2, 3, 4, 5])

    print "filter()函数测试： ", filter(is_odd, [1, 2, 3, 4, 5, 6, 7, 8, 9])
    print filter(is_sqrt, range(1, 101))

    print "sorted()函数测试： ", sorted([1, 34, 23, 12, 4, 1])
    print "sorted()函数测试： ", sorted([1, 34, 23, 12, 4, 1], reversed_cmp)
    print sorted(['bob', 'about', 'Zoo', 'Credit'], cmp_ignore_case)

    f = calc_sum([1, 3, 3, 4])
    print f, f()

    f = calc_prod([1, 2, 3, 4])
    print f()

    f1, f2, f3 = count()
    print f1(), f2(), f3()

    print "测试匿名函数： ", map(lambda x: x * x, [1, 2, 3, 4, 5])
    print "测试匿名函数： ", sorted([1, 3, 9, 5, 0], lambda x, y: -cmp(x, y))

    print "测试装饰器", factorial(10)

    int2 = bais_func()
    print "测试偏函数", int2("1000")

    sorted_ignore_case = functools.partial(sorted, cmp=lambda s1, s2: cmp(s1.upper(), s2.upper()))
    print sorted_ignore_case(['bob', 'about', 'Zoo', 'Credit'])

    print 10 / 3

if __name__ == "__main__":
    main_test()
