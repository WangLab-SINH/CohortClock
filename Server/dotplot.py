# -*- coding: utf-8 -*-
import matplotlib.pyplot as plt
import numpy as np
import pymysql # 操作mysql的模块
import openpyxl # xlsx格式对应的操作模块
import time
import math
from datetime import datetime
import csv
import os
import pandas
import re
import argparse
import sys
from sklearn.impute import SimpleImputer


plt.rcParams['font.sans-serif']=['simhei']
plt.rcParams['axes.unicode_minus']=False
parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
parser.add_argument('-u', '--user', metavar='user', default='50470b6627e1e211')



def isLUN(year):
    if year % 100 == 0:
        if year % 400 == 0:
            return 1
    else:
        if year % 4 == 0:
            return 1
    return 0


def isDay(YEAR, Month, day):
    ret = 0
    ping = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    lun = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if isLUN(YEAR):
        for i in range(Month - 1):
            ret = ret + lun[i]
    else:
        for i in range(Month - 1):
            ret = ret + ping[i]
    return ret + day;


def isWeekEnd(YEAR, Month, day):
    S = (YEAR + (YEAR - 1) // 4 - (YEAR - 1) // 100 + (YEAR - 1) // 400) % 7
    days = (isDay(YEAR, Month, day) + S - 1) % 7
    # return days
    if days == 0 or days == 6:
        return 1
    else:
        return 0









# 定义画散点图的函数
def draw_scatter(plot_data_x, plot_data_y):
    """
    :param n: 点的数量，整数
    :param s:点的大小，整数
    :return: None
    """
    # 加载数据

    # 通过切片获取横坐标x1
    #x1 = data[:, 0]
    # 通过切片获取纵坐标R
    #y1 = data[:, 1]
    # 横坐标x2

    fig = plt.figure()
    # 将画图窗口分成1行1列，选择第一块区域作子图
    ax1 = fig.add_subplot(1, 1, 1)
    # 设置标题
    ax1.set_title('Result Analysis')
    # 设置横坐标名称
    ax1.set_xlabel('gamma-value')
    # 设置纵坐标名称
    ax1.set_ylabel('R-value')
    # 画散点图
    # ax1.scatter(x1, y1)
    # # 调整横坐标的上下界
    # plt.ylim(ymax=1,ymin=0)
    # # 显示
    # plt.show()

    plt.plot(plot_data_x, plot_data_y)
    plt.show()

plot_data = []
plot_data_x = []
plot_data_y = []
#args = parser.parse_args()
user_name1 = sys.argv[1]
#print(user_name1)
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()  # 获取游标
#sql =  r'''select * from photo_path where user_name = "'+user_name1+'" '''
sql = 'select * from photo_path where user_name ="'+user_name1+'"'
cur.execute(sql)
results = cur.fetchall()
#print(results)
data_dict = {}
max_id = 0
data_dict_weekend = {}
for i in range(results.__len__()):
    if results[i][5] == 'food':
        if int(results[i][0]) > max_id:
            max_id = int(results[i][0])
            current_time = results[i][2]
        temp = re.split(r'-', results[i][2])
        year = int(re.split(r'\.', temp[0])[0])
        month = int(re.split(r'\.', temp[0])[1])
        day = int(re.split(r'\.', temp[0])[2])
        if isWeekEnd(year, month, day) == 0:
            if temp[0] in data_dict.keys():
                data_dict[temp[0]].append(temp[1])
            else:
                data_dict[temp[0]] = [temp[1]]
        else:
            if temp[0] in data_dict_weekend.keys():
                data_dict_weekend[temp[0]].append(temp[1])
            else:
                data_dict_weekend[temp[0]] = [temp[1]]

input_data = []
num = 0
time_series = [0,60,120,180,240,300,360,420,480,540,600,660,720,780,840,900,960,1020,1080,1140,1200]
current_day = re.split(r'-', current_time)[0]
new_data_dict = {}
new_data_dict_weekend = {}
time = []
time_weekend = []
for key in data_dict.keys():

    temp_k = 0
    for j in range(data_dict[key].__len__()):
        temp = re.split(r':', data_dict[key][j])
        minute_data = int(temp[0]) * 60 + int(temp[1])
        time.append((float(temp[0]) * 60 + int(temp[1])) / 60)
        #print(time)
    if max(time) - min(time) < 10:
        new_data_dict[key] = data_dict[key]

for key in data_dict_weekend.keys():

    temp_k = 0
    for j in range(data_dict_weekend[key].__len__()):
        temp = re.split(r':', data_dict_weekend[key][j])
        minute_data = int(temp[0]) * 60 + int(temp[1])
        time_weekend.append((float(temp[0]) * 60 + int(temp[1])) / 60)
        #print(time)
    if max(time_weekend) - min(time_weekend) < 10:
        new_data_dict_weekend[key] = data_dict_weekend[key]



num_count={}
for i in time:
    if i not in num_count:
        num_count[i]=1
    else:
        num_count[i]+=1
value_x = []
value_y = []
for key in num_count.keys():
    value_x.append(key)
    value_y.append(num_count[key])
#print(num_count)
data =time
plt.figure(1)
#第一行第一列图形
ax1 = plt.subplot(1,2,1)
#第一行第二列图形
ax2 = plt.subplot(1,2,2)
plt.sca(ax1)


bins_interval=1
margin=0.5
bins = range(0, 24 + bins_interval - 1, bins_interval)
plt.xlim(0 - margin, 24 + margin)
plt.title("饮食时间统计 工作日")
plt.xlabel('时间')
plt.ylabel('次数')
prob,left,rectangle = plt.hist(x=data, bins=bins, histtype='bar', color=['royalblue'], density=True)
#for x, y in zip(left, prob):
    # 字体上边文字
    # 频率分布数据 normed=True

    # 频次分布数据 normed=False
    #plt.text(x + bins_interval / 2, y, '%.f' % y, ha='center', va='top')

plt.sca(ax2)
data = time_weekend
bins_interval=1
margin=0.5
bins = range(0, 24 + bins_interval - 1, bins_interval)
plt.xlim(0 - margin, 24 + margin)
plt.title("饮食时间统计 周末")
plt.xlabel('时间')
prob1,left1,rectangle1 = plt.hist(x=data, bins=bins, histtype='bar', color=['royalblue'], density=True)
# bins_interval=1
# margin=0.5
# bins = range(0, 24 + bins_interval - 1, bins_interval)
#
# #print(len(bins))
# #for i in range(0, len(bins)):
# #    print(bins[i])
# plt.xlim(0 - margin, 24 + margin)
# plt.title("饮食时间统计")
# plt.xlabel('时间')
# plt.ylabel('次数')
# # 频率分布normed=True，频次分布normed=False
# prob,left,rectangle = plt.hist(x=data, bins=bins, histtype='bar', color=['royalblue'], density=True)
#for x, y in zip(left, prob):
    # 字体上边文字
    # 频率分布数据 normed=True

    # 频次分布数据 normed=False
    #plt.text(x + bins_interval / 2, y, '%.f' % y, ha='center', va='top')
#plt.show()
files = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1)
if not os.path.exists(files):
    os.mkdir(files)

file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/dotplot.png"
plt.savefig(file_path)


#new_plot_data = np.array(plot_data)
        #if time3 - time1 < 600:










#print(results)
cur.close()

conn.close()
