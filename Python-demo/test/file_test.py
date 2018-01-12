# coding=utf-8
# file_test.py

import os
import os.path
import ConfigParser


class student_info(object):
    "使用文件配置管理学生信息"

    def __init__(self, recordFile):
        self.logFile = recordFile
        self.cfg = ConfigParser.ConfigParser()

    def cfg_load(self):
        self.cfg.read(self.logFile)

    def cfg_dump(self):
        se_list = self.cfg.sections()
        print "============> "
        for se in se_list:
            print se
            print self.cfg.items(se)
        print "<==========="

    def delete_item(self, section, key):
        self.cfg.remove_option(section, key)

    def delete_section(self, section):
        self.cfg.remove_section(section)

    def add_section(self, section):
        self.cfg.add_section(section)

    def set_item(self, section, key, value):
        self.cfg.set(section, key, value)

    def save(self):
        fp = open(self.logFile, "w")
        self.cfg.write(fp)
        fp.close()


def main_test():
    info = student_info("test.txt")
    info.cfg_load()
    info.cfg_dump()
    info.set_item("userInfo", "pwd", "abc")

    info.cfg_dump()
    info.add_section("login")
    info.set_item("login", "2015-10-11", "20")
    info.cfg_dump()

    info.save()


if __name__ == "__main__":
    main_test()
