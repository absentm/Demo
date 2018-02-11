# lybTv_main.py
# coding=utf-8
# python-version: 2.x

from lybTvSpider_Py2 import LybTvSpider
from python27.python35 import clipboardUtil

url = "http://www.dy2018.com/i/95650.html"

lybTvSpider = LybTvSpider()
content = lybTvSpider.open_url(url)  # 获取网页源代码，同
tvs = lybTvSpider.get_tvs(content)
tvs_url = lybTvSpider.get_download_url(tvs)

# 将下载地址复制到剪切板，之后就可以用下载器批量下载了
clipboardUtil.setText("".join(tvs_url))
# print str(clipboardUtil.getText()).decode("GBK")
lybTvSpider.save_download_urls(tvs_url)  # 同时在文本文件中保存一份下载地址
