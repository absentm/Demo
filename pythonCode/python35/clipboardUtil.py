# clipBoardUtil.py
# coding=utf-8
# 使用win32 api 操作Windows剪切板

# import sys
# sys.path.append("/to/path/win32clipboard")
# sys.path.append("/to/path/win32con")

import win32clipboard as w
import win32con


def getText():
    "获取windows剪切板内容"
    w.OpenClipboard()
    d = w.GetClipboardData(win32con.CF_TEXT)
    w.CloseClipboard()

    return d


def setText(aString):
    "设置windows剪切板的内容"
    w.OpenClipboard()
    w.EmptyClipboard()

    # 设置Unicode编码格式
    w.SetClipboardData(win32con.CF_UNICODETEXT, aString)
    w.CloseClipboard()


def main():
    "测试程序"
    print getText()
    setText("我在剪切板中")
    print getText()


# 判断是否是在直接运行该.py文件
if __name__ == "__main__":
    main()
