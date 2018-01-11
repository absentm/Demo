# lybTvSpider_Py2.py
# coding=utf-8
# python-version: 2.x

import urllib2
import bs4
import chardet


class LybTvSpider:
    "琅琊榜电视剧爬虫工具类"

    def __init__(self):
        "构造函数"
        self.__headers = ('User-Agent',
                          'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36')
        self.__opener = urllib2.build_opener()
        self.__opener.addheaders = [self.__headers]

    def open_url(self, url):
        "指定URL，打开链接地址"
        content = self.__opener.open(url).read()
        encoding = chardet.detect(content)['encoding']
        content = content.decode(encoding, 'ignore')

        return content

    def get_tvs(self, content):
        "获取每集电视相关的网页标签： <td style='WORD-WRAP: break-word><a></a></td>"
        soup = bs4.BeautifulSoup(content)
        # 查找所有的带属性"style='WORD-WRAP: break-word"的'td'标签，
        tvs_list = soup.findAll('td', style='WORD-WRAP: break-word')

        return tvs_list

    def get_download_url(self, tvs_list):
        "获取每集电视中的下载地址"
        tvs_list_url = []

        for tv in tvs_list:  # 遍历标签
            tvs_list_url.append(tv.a['href'] + "\n")  # 获取所有的下载列表添加到list中

        return tvs_list_url

    def save_download_urls(self, tvs_list_url):
        "将下载链接保存至文件"

        fr = file("lyb_urls.txt", "w+")
        for item in tvs_list_url:
            fr.write(item.encode("utf-8"))
        fr.close()


def main_test():
    "模块测试"

    url = "http://www.dy2018.com/i/95650.html"

    lybTvSpider = LybTvSpider()
    content = lybTvSpider.open_url(url)  # 获取网页源代码，同在浏览器中右键查看源代码内容一致
    # print content

    tvs = lybTvSpider.get_tvs(content)
    print "tvs List---> ", tvs

    tvs_url = lybTvSpider.get_download_url(tvs)
    print "tvs_url ---> ", tvs_url

    lybTvSpider.save_download_urls(tvs_url)


if __name__ == "__main__":
    main_test()
