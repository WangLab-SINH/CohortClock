# coding=utf8
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns
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
from matplotlib.ticker import MultipleLocator, FormatStrFormatter
from scipy.stats import ks_2samp
from scipy.stats import norm
from pylab import *
parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
parser.add_argument('-u', '--user', metavar='user', default='50470b6627e1e211')




user_name1 = sys.argv[1]
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()
sql = 'select * from photo_path where user_name ="'+user_name1+'"'
cur.execute(sql)
results = cur.fetchall()
#print(results)
data_dict = {}
max_id = 0
time_list = []
for i in range(results.__len__()):
    if results[i][5] == 'food':
        if int(results[i][0]) > max_id:
            max_id = int(results[i][0])
            current_time = results[i][2]

        temp = re.split(r'-', results[i][2])
        temp_time = re.split(r':', temp[1])
        time_list.append(round(float(temp_time[0]) + float(temp_time[1]) / 60, 2))
        if temp[0] in data_dict.keys():
            data_dict[temp[0]].append(temp[1])
        else:
            data_dict[temp[0]] = [temp[1]]

end_time = time_list[time_list.__len__() - 1]
flag = 0
file_list = ['model_10h','model_8h','model_12h','model_day','model_night','model_random','model_4hintervel']
if time_list.__len__() < 2100:
    flag = 2
if flag == 0:
    for i in range(7):
        name = '/var/www/html/deeplearning_photo/model/final_0412/' + file_list[i] + '.csv'
        csv_reader = csv.reader(open(name, encoding='utf-8'))
        num = 0
        plot_data = []
        for line in csv_reader:
            if num == 0:
                num += 1
                continue
            plot_data.append(float(line[1]))

        plot_data = sorted(plot_data)



        test_stat = ks_2samp(np.array(time_list), np.array(plot_data))
        if test_stat.pvalue > 0.05:
            flag = 1
            break


time_series = []
for i in range(6):
    current_time = round(end_time) + i * 4
    if current_time >= 24:
        current_time -= 24

    time_series.append(current_time)


if flag == 1:
    #xmajorLocator   = MultipleLocator(4)
    data = plot_data
    bins_interval = 1
    bins = 240
    ax = plt.gca()
    #ax.xaxis.set_major_locator(xmajorLocator)
    plt.xticks(range(round(end_time), round(end_time) + 24, 4), time_series)
    plt.xlim(end_time, end_time + 24)

    mu = np.mean(data)
    sigma = np.std(data)

    sns.set_palette("hls") #设置所有图的颜色，使用hls色彩空间
    sns.distplot(data,color="skyblue",bins=480,kde=True)
    file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.png"
    plt.savefig(file_path)
else:
    csv_reader = csv.reader(
        open('/var/www/html/deeplearning_photo/model/final_0412/model_10h.csv', encoding='utf-8'))
    num = 0
    plot_data = []
    for line in csv_reader:
        if num == 0:
            num += 1
            continue
        plot_data.append(float(line[1]))

    plot_data = sorted(plot_data)
    #xmajorLocator = MultipleLocator(4)
    data = plot_data
    bins_interval = 1
    bins = 240
    ax = plt.gca()
    #ax.xaxis.set_major_locator(xmajorLocator)
    plt.xticks(range(round(end_time), round(end_time) + 24, 4), time_series)
    plt.xlim(end_time, end_time+24)

    mu = np.mean(data)
    sigma = np.std(data)

    sns.set_palette("hls")  # 设置所有图的颜色，使用hls色彩空间
    #sns.distplot(data, color="skyblue", bins=480, kde=True)
    sns.distplot(data, color="skyblue", bins=480, kde=False)
    file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.png"
    plt.savefig(file_path)






cur.close()

conn.close()
