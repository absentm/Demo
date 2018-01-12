#coding:utf-8

import numpy as np
import matplotlib.pyplot as plt
from pylab import *

x_size = [1, 3, 5, 7, 9, 11, 13]
y_time_point = [0, 200, 415, 628, 714, 862, 0]

x_zhe = [3, 5, 7, 9, 11]
y_zhe = [200, 415, 628, 714, 862]

# 设置刻度标签
plt.xticks(x_zhe, ('3', '5', '7', '9', '11'))

# 绘制柱状图
plt.bar(left=(x_size), height=(y_time_point), width=1.0, 
	align="center", facecolor='lightblue', edgecolor='white')

# 添加文本解释
for x, y in zip(x_zhe, y_zhe):
    plt.text(x, y+10, '%.0f' % y, ha='center', va='bottom')

#设置y轴范围
ylim(0, 1000)

# 画折线图
plt.plot(x_zhe, y_zhe, 'k.-')
plt.title('Time values change with Size')
plt.xlabel('Size')
plt.ylabel('Time (s)')
plt.grid(True)
plt.show()
