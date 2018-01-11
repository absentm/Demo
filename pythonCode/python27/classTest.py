# classTest.py
# coding:utf-8

class Employee:
    "所有员工的基类"

    __name = ""     # 私有变量
    __salary = 0.0  # 私有变量
    empCount = 0    # 共有变量

    # def __init__(self):
    #     Employee.empCount += 1

    def __init__(self, name, salary):
        "构造函数，初始化成员变量"
        self.name = name
        self.salary = salary
        Employee.empCount += 1

    def __displayCount(self):
        "显示员工总数"
        print "Total Employee %d" % Employee.empCount

    def displayEmployee(self):
        "显示员工详情"
        print "Name: ", self.name, ", Salary: ", self.salary
        self.__displayCount()

    def __del__(self):
        "析构函数，销毁对象"
        class_name = self.__class__.__name__
        print class_name, "destroyed"


class Manager(Employee):
    "定义经理类，是员工的派生类"

    @classmethod
    def printHello(cls):
        "类方法测试"
        return "Hello, Everyone!"

    def __init__(self, department, name, salary):
        """
        派生类成员初始化器
        :param department: 部门类别
        :param name: 姓名
        :param salary: 薪金
        """
        Employee.__init__(self, name, salary)
        self.department = department
        Employee.empCount += 1

    def displayEmployee(self):
        "方法重写， 显示经理详情"
        print "Department: ", self.department, "Name: ", self.name, ", Salary: ", self.salary
