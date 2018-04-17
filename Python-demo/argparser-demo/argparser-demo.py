#!/usr/bin/python
# coding:utf-8

"""
 # argparser-demo.py
 #
 # Copyright(C) by AbsentM. 2018
 #
 # Author: AbsentM
 # Date:   2018/04/15
 #
 # Description:
 # Use argparser function to execute simple run flow.
 # link: http://blog.xiayf.cn/2013/03/30/argparse/
 #    
 # Change Log:
 # AbsentM 2018/04/15 Create the file
 #
"""

import argparse
parser = argparse.ArgumentParser(description="Hello, I am here")
parser.add_argument("-a", action="store_true", default=False)
parser.add_argument("-b", action="store", dest="b")
parser.add_argument("-c", action="store", dest="c", type=int)

print parser.parse_args(["-a", "-bvar", "-c", "3"])




