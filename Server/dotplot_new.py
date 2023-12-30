# coding=utf8
import matplotlib.pyplot as plt
import numpy as np
import pymysql # 操作mysql的模块
import openpyxl # xlsx格式对应的操作模块
import time
import math
import csv
import os
import pandas
import re
import argparse
import sys
import datetime
import seaborn as sns
import matplotlib as mpl
from pylab import *
from sklearn.impute import SimpleImputer
from matplotlib.pyplot import MultipleLocator
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
    return ret + day


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
date_list = []
for i in range(results.__len__()):
    if results[i][5] == 'food':
        date_list.append(results[i][2])
def get_list(date):
    return datetime.datetime.strptime(date,"%Y.%m.%d-%H:%M:%S").timestamp()
new_date_list = sorted(date_list,key=lambda date:get_list(date))
current_time = re.split(r'-',new_date_list[-1])[0]
work_plot_data_list = []
work_plot_data_dic = {}
weekend_plot_data_list = []
weekend_plot_data_dic = {}
for i in range(new_date_list.__len__()):
    date_str = re.split(r'-', new_date_list[i])[0]
    if date_str != current_time:
        year = int(re.split(r'\.', date_str)[0])
        month = int(re.split(r'\.', date_str)[1])
        day = int(re.split(r'\.', date_str)[2])
        if isWeekEnd(year, month, day) == 0:
            if i > 0:
                temp_string = re.split(r'-', new_date_list[i])[0]
                if temp_string in work_plot_data_dic.keys():

                    temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    temp = temp_time - last_time
                    minus_time = temp.days * 24 * 60 + temp.seconds / 60
                    # 15分钟间隔以内算一顿饭
                    if minus_time > 15:
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(
                            round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                        work_plot_data_list.append(temp_str1)
                        work_plot_data_dic[temp_string].append(temp_str1)
                        # 有效的进食时间flag
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                else:
                    temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    temp = temp_time - last_time
                    minus_time = temp.days * 24 * 60 + temp.seconds / 60
                    # 15分钟间隔以内算一顿饭
                    if minus_time > 15:
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(
                            round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                        work_plot_data_list.append(temp_str1)
                        work_plot_data_dic[temp_string] = []
                        work_plot_data_dic[temp_string].append(temp_str1)
                        # 有效的进食时间flag
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
            else:
                temp_string = re.split(r'-', new_date_list[i])[0]
                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                temp_str = re.split(r'-', new_date_list[i])
                temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                work_plot_data_list.append(temp_str1)
                work_plot_data_dic[temp_string] = []
                work_plot_data_dic[temp_string].append(temp_str1)
        else:
            if i > 0:
                temp_string = re.split(r'-', new_date_list[i])[0]
                if temp_string in weekend_plot_data_dic.keys():

                    temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    temp = temp_time - last_time
                    minus_time = temp.days * 24 * 60 + temp.seconds / 60
                    # 15分钟间隔以内算一顿饭
                    if minus_time > 15:
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(
                            round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                        weekend_plot_data_list.append(temp_str1)
                        weekend_plot_data_dic[temp_string].append(temp_str1)
                        # 有效的进食时间flag
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                else:
                    temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    temp = temp_time - last_time
                    minus_time = temp.days * 24 * 60 + temp.seconds / 60
                    # 15分钟间隔以内算一顿饭
                    if minus_time > 15:
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(
                            round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                        weekend_plot_data_list.append(temp_str1)
                        weekend_plot_data_dic[temp_string] = []
                        weekend_plot_data_dic[temp_string].append(temp_str1)
                        # 有效的进食时间flag
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
            else:
                temp_string = re.split(r'-', new_date_list[i])[0]
                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                temp_str = re.split(r'-', new_date_list[i])
                temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
                weekend_plot_data_list.append(temp_str1)
                weekend_plot_data_dic[temp_string] = []
                weekend_plot_data_dic[temp_string].append(temp_str1)
# for i in range(new_date_list.__len__()):
#     date_str = re.split(r'-', new_date_list[i])[0]
#     year = int(re.split(r'\.', date_str)[0])
#     month = int(re.split(r'\.', date_str)[1])
#     day = int(re.split(r'\.', date_str)[2])
#     if isWeekEnd(year, month, day) == 0:
#         if i > 0:
#             temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#             temp = temp_time - last_time
#             minus_time = temp.days * 24 *60 + temp.seconds / 60
#             #15分钟间隔以内算一顿饭
#             if minus_time > 15:
#                 temp_str = re.split(r'-', new_date_list[i])
#                 temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
#                 work_plot_data_list.append(temp_str1)
#                 #有效的进食时间flag
#                 last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#         else:
#             last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#             temp_str = re.split(r'-', new_date_list[i])
#             temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
#             work_plot_data_list.append(temp_str1)
#     else:
#         if i > 0:
#             temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#             temp = temp_time - last_time
#             minus_time = temp.days * 24 *60 + temp.seconds / 60
#             # 15分钟间隔以内算一顿饭
#             if minus_time > 15:
#                 temp_str = re.split(r'-', new_date_list[i])
#                 temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
#                 weekend_plot_data_list.append(temp_str1)
#                 # 有效的进食时间flag
#                 last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#         else:
#             last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
#             temp_str = re.split(r'-', new_date_list[i])
#             temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 15)
#             weekend_plot_data_list.append(temp_str1)

work_plot_data_list_new = {}
for i in work_plot_data_dic.keys():
    temp_len = len(work_plot_data_dic[i])
    for j in range(work_plot_data_dic[i].__len__()):
        if work_plot_data_dic[i][j] in work_plot_data_list_new.keys():
            work_plot_data_list_new[work_plot_data_dic[i][j]] += float(1 / temp_len)
        else:
            work_plot_data_list_new[work_plot_data_dic[i][j]] = float(1 / temp_len)
time_series = range(0,1440, 120)
pd_y = []
for i in range(96):
    if i in work_plot_data_list_new.keys():
        temp = work_plot_data_list_new[i] * 100
        for j in range(round(temp)):
            pd_y.append(i)

weekend_plot_data_list_new = {}
for i in weekend_plot_data_dic.keys():
    temp_len = len(weekend_plot_data_dic[i])
    for j in range(weekend_plot_data_dic[i].__len__()):
        if weekend_plot_data_dic[i][j] in weekend_plot_data_list_new.keys():
            weekend_plot_data_list_new[weekend_plot_data_dic[i][j]] += float(1 / temp_len)
        else:
            weekend_plot_data_list_new[weekend_plot_data_dic[i][j]] = float(1 / temp_len)
time_series = range(0,1440, 120)
pd_y_weekend = []
for i in range(96):
    if i in weekend_plot_data_list_new.keys():
        temp = weekend_plot_data_list_new[i] * 100
        for j in range(round(temp)):
            pd_y_weekend.append(i)



time_series_new = []
for i in time_series:
    hour = int(math.floor(i / 60))
    minute = int(i % 60)
    time_series_new.append(str(hour) + ":" + str(minute))
time_series_new = ['start', '0:00', '4:00', '8:00', '12:00','16:00','20:00','24:00']
sns.set_palette("hls")
mpl.rc("figure", figsize=(12,4))
plt.figure(1)
#第一行第一列图形
ax1 = plt.subplot(1,2,1)
#第一行第二列图形
ax2 = plt.subplot(1,2,2)
plt.sca(ax1)
plt.title("工作日")
plt.xlabel('时间')
#sns.barplot(x=pd_x, y=pd_y)
sns.kdeplot(pd_y, shade=True)
#sns.distplot(pd_y,kde_kws={"color":"seagreen", "lw":5 }, hist_kws={ "color": "skyblue" },hist=False)
plt.xticks(np.arange(len(time_series_new)),time_series_new)

plt.xticks(np.arange(95),time_series_new)

plt.yticks([])
x_major_locator=MultipleLocator(16)
ax=plt.gca()
ax.xaxis.set_major_locator(x_major_locator)
plt.xlim(0, 96)
ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)
ax.spines['left'].set_visible(False)

plt.sca(ax2)

plt.title("休息日")
plt.xlabel('时间')
#sns.distplot(pd_y_weekend,bins=95,kde_kws={"color":"seagreen", "lw":5 }, hist_kws={ "color": "skyblue" },hist=False)
sns.kdeplot(pd_y_weekend, shade=True)
plt.xticks(np.arange(len(time_series_new)),time_series_new)

# plt.xticks(np.arange(95),time_series_new)

plt.yticks([])
x_major_locator=MultipleLocator(16)
ax=plt.gca()
ax.xaxis.set_major_locator(x_major_locator)
plt.xlim(0, 96)
ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)
ax.spines['left'].set_visible(False)

files = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1)
if not os.path.exists(files):
    os.mkdir(files)

file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/dotplot.png"
plt.savefig(file_path)
#plt.show()





cur.close()

conn.close()
