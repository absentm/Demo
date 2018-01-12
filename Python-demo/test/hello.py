#coding:utf-8
# hello.py   ----> this is singleLine commit
#单行注释

'''           ----> this is more line commit 这是一个多行注释
first python test
'''

print "hello, world"

print 45678 + 0x12fd2
print "Learn Python in imooc"
print 100 < 99
print 0xff == 255
print "The test object", "jump here", "hahah"

s = r'''Python is created by "Guido".
It is free and easy to learn.
Let's start learn Python in imooc!'''

print s

print ur'''静夜思

床前明月光，
疑是地上霜。
举头望明月，
低头思故乡。'''


print 11 / 4
print 11 % 4
print 11.0 / 4

a = 'python'
print 'hello,', a or 'world'

b = ''
print 'hello,', b or 'world'

L = ['Adam', 95.5, 'Lisa', 85, 'Bart', 59]
print L
print L[0]

L = [95.5, 85, 59]
print L[-1]     # 倒序
print L[-2]
print L[-3]
#print L[-4]


# List测试
list = ['aa', 'bb', 'cc'] 
print list
list.append('dd')
print list
list.insert(1, 'AXC')
print list
list.pop(0)
print list


# 元组测试 tuple
tuple = ('AA', 'BB', 'CC')      # 一经创建不能修改
print tuple


#因为()既可以表示tuple，又可以作为括号表示运算时的优先级，结果 (1) 被Python解释器计算出结果
#1，导致我们得到的不是tuple，而是整数 1。
#正是因为用()定义单元素的tuple有歧义，所以 Python 规定，单元素 tuple 要多加一个逗号“,”，这样就避免了歧义：

t = (1)
print t

t = (1,)
print t

# test if else
age = 8

if age >= 18:
    print 'adult'
elif age >= 6:
    print 'teenger'
elif age >= 3:
    print 'kid'
else:
    print 'baby'

# test while 

sum = 0
x = 1

while x < 100:
    if (x % 2) == 1:
        sum += x
        print x
    x = x + 1
print sum 


# test break
sum = 0
x = 1
n = 1
while True:
    sum = sum + x
    x = x * 2
    n = n + 1

    print x 
    if n > 20:
        break
print sum


# test dict
dcd = {
    'Adam': 95,
    'Lisa': 85,
    'Bart': 59
}

for key in dcd:
    print key + ':', dcd[key]

def move(n, a, b, c):
    if n ==1:
        print a, '-->', c
        return
    move(n-1, a, c, b)
    print a, '-->', c
    move(n-1, b, a, c)

move(4, 'A', 'B', 'C')

# test moude
import fileRename
fileRename.renameFiles("D:\迅雷下载".encode('GBK'))

# 测试异常
fr = open("testFile.txt", "w")
try:
    import os
    print os.getcwd().decode("GBK")

    fr.write("this is write append")
except IOError:
    print "IOError"
else:
    print "write OK!"
finally:
    fr.close()


# test class
import classTest

emp1 = classTest.Employee("Wang", 1500)
emp2 = classTest.Employee("LIU", 3000)
# emp1.displayCount() 更改为私有方法后不能在类外部调用
emp1.displayEmployee()
# emp2.displayCount() 更改为私有方法后不能在类外部调用
emp2.displayEmployee()

print "Total employees: %d" % classTest.Employee.empCount

emp1.age = 20
emp2.age = 30

print hasattr(emp2, "age")
print getattr(emp1, "age")
print getattr(emp2, "name")
setattr(emp2, "ddd", 12.3)
delattr(emp2, "ddd")

print "__doc__: ", classTest.Employee.__doc__
print "__name__: ", classTest.Employee.__name__
print "__module__: ", classTest.Employee.__module__
print "__bases__: ", classTest.Employee.__bases__
print "__dict__: ", classTest.Employee.__dict__

del emp2
manager1 = classTest.Manager("Unix", "Dong", 10000)
manager1.displayEmployee()
# manager1.displayCount() 更改为私有方法后不能在类外部调用

print isinstance(manager1, classTest.Manager)