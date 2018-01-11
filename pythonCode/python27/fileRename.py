# fileRename.py
# coding:utf-8

import os
import sys  # 设置系统默认编码格式：utf-8
reload(sys)
sys.setdefaultencoding('utf8')


def renameFiles(dirPath):
    "指定文件目录，重命名该目录下以.mp4结尾的文件"
    os.chdir(dirPath)  # 切换到当前目录下
    dirContent = os.listdir(dirPath)

    for file in dirContent:
        if file.startswith("[") and file.endswith('.mp4'):
            temp = file.split(']')
            content = temp[1].split(".")
            newName = content[0] + "." + content[len(content) - 1]
            os.rename(file, newName)

            print file + " -> " + newName + " ------> OK!"
        elif file.endswith(".mp4"):
            temp = file.split('.')
            newName = temp[0] + "." + temp[len(temp) - 1]
            os.rename(file, newName)

            print file + " -> " + newName + " ------> OK!"

    return;


def main_test():
    "在当前模块中测试"
    renameFiles("D:\迅雷下载".encode('GBK'))


if __name__ == "__main__":
    main_test()
