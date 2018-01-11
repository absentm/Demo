# coding=utf-8
# match_test.py 正则表达式测试
# 爬去慕课网图片

import urllib2
import re

req = urllib2.urlopen("http://www.imooc.com/course/list")
buf = req.read()
print buf

listUrl = re.findall(r"http:.+\.jpg", buf)
print listUrl

i = 0

for url in listUrl:
    f = open(str(i) + ".jpg", "w")
    req = urllib2.urlopen(url)
    buf = req.read()
    f.write(buf)
    i += 1
    print i
