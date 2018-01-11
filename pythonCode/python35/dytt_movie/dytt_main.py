# dytt_main.py
# coding=utf-8
# python-version: 2.x

from dyttSpider_Py2 import DyttSpider
from python27.python35 import clipboardUtil

url = "http://www.dytt8.net/html/gndy/dyzz/index.html"
# url = "http://www.dy2018.com/i/95650.html"

dyttSpider = DyttSpider()
content = dyttSpider.open_url(url)  # 获取网页源代码，同
print content

films = dyttSpider.get_films(content)
films_url = dyttSpider.get_download_url(films)

downloadList = []

for film_dic in films_url:
    print film_dic['film_title']
    print film_dic['download_url']
    downloadList.append(film_dic['download_url'] + "\n")  # 保存所有的下载链接，为下一步复制到Windows剪切板准备

print "**********************************************************"
# 将下载地址复制到剪切板，之后就可以用下载器批量下载了
clipboardUtil.setText("".join(downloadList))
print str(clipboardUtil.getText()).decode("GBK")
