# dyttSpider_Py2.py
# coding=utf-8
# python-version: 2.x

import urllib2
import bs4
import chardet


class DyttSpider:
    "电影天堂爬虫工具类"

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

    def get_films(self, content):
        "获取影片相关的网页标签"
        soup = bs4.BeautifulSoup(content)
        films_list = soup.findAll('a', class_='ulink')  # 查找所有的带属性"class=ulink"的'a'标签，

        return films_list

    def get_download_url(self, films_list):
        "获取每部电影中的下载地址"
        films_list_url = []

        # 遍历标签
        for film in films_list:
            film_dic = {}
            film_href = "http://www.dytt8.net" + film['href']  # 拼接item的下载url

            film_content = self.__opener.open(film_href).read()  # 读取内容
            encoding = chardet.detect(film_content)['encoding']
            film_content = film_content.decode(encoding, 'ignore')

            film_soup = bs4.BeautifulSoup(film_content)  # 创建BeautifulSoup对象
            title_all = film_soup.findAll('div', class_='title_all')  # 获取电影标题
            film_dic['film_title'] = title_all[-1].h1.font.string

            if film_dic['film_title']:  # 获取下载地址
                film_dic['download_url'] = film_soup.findAll('td', style='WORD-WRAP: break-word')[0].a['href']
                films_list_url.append(film_dic)
            else:
                break
        return films_list_url


def main_test():
    url = "http://www.dy2018.com/i/95650.html"

    dyttSpider = DyttSpider()
    content = dyttSpider.open_url(url)  # 获取网页源代码，同
    # print content

    films = dyttSpider.get_films(content)
    print "films List---> ", films
    # print "soups---> ", soups

    films_url = dyttSpider.get_download_url(films)
    # print "films_url ---> ", films_url


if __name__ == "__main__":
    main_test()
