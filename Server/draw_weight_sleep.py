# coding=utf8
import pyecharts.options as opts
from pyecharts.charts import Line, Grid
from pyecharts.faker import Faker

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
import pandas as pd
from pylab import *
from scipy.stats import stats
from sklearn.impute import SimpleImputer
from matplotlib.pyplot import MultipleLocator
from pyecharts.charts import HeatMap
from pyecharts.charts import Page
plt.rcParams['font.sans-serif']=['simhei']
plt.rcParams['axes.unicode_minus']=False
parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
parser.add_argument('-u', '--user', metavar='user', default='50470b6627e1e211')
user_name1 = sys.argv[1]
#print(user_name1)
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()  # 获取游标
#sql =  r'''select * from photo_path where user_name = "'+user_name1+'" '''
sql = 'select * from user_weight_sleep where user_id ="'+user_name1+'"'
cur.execute(sql)
results = cur.fetchall()
#print(results)
date_list = []
past_time = datetime.datetime.strptime("1900-1-1 00:00:00", "%Y-%m-%d %H:%M:%S")
current_time = results[len(results) - 1][5]
current_day = re.split(r' ', current_time)[0]


def dateRange(beginDate):
    """

    :param beginDate:
    :return:
    """
    yes_time = beginDate + datetime.timedelta(days=+1)
    aWeekDelta = datetime.timedelta(days=30)
    aWeekAgo = yes_time - aWeekDelta
    dates = []
    i = 0
    begin = aWeekAgo.strftime("%Y-%m-%d")
    dt = datetime.datetime.strptime(begin, "%Y-%m-%d")
    date = begin[:]
    while i < 30:
        dates.append(date)
        dt = dt + datetime.timedelta(1)
        date = dt.strftime("%Y-%m-%d")
        i += 1
    return dates

def get_list(date):
    return date.timestamp()
new_date_list = sorted(date_list,key=lambda date:get_list(date))

sql_weight_data ={}
sql_sleep_data ={}
sql_wake_data ={}
input_data_list = []
for i in range(results.__len__()):
    temp_time = datetime.datetime.strptime(results[i][5], "%Y-%m-%d %H:%M:%S")
    input_data_list.append(temp_time)
    day = re.split(r' ', results[i][5])[0]
    if results[i][2] != "0.0":
        sql_weight_data[day] = float(results[i][2])
    if results[i][3] != "null" and results[i][4] != "null":
        sleep_day = re.split(r' ', results[i][3])[0]
        wake_day = re.split(r' ', results[i][4])[0]
        sql_sleep_data[sleep_day] = re.split(r' ', results[i][3])[1]
        sql_wake_data[wake_day] = re.split(r' ', results[i][4])[1]
new_date_list = sorted(input_data_list,key=lambda date:get_list(date))
current_time = new_date_list[len(new_date_list) - 1]
current_day = str(current_time.year) + "-" + str(current_time.month) + "-" + str(current_time.day)
time_list = dateRange(datetime.datetime.strptime(current_day, "%Y-%m-%d"))

weight_list = []
sleep_list = []
wake_list = []
for i in range(time_list.__len__()):
    if time_list[i] in sql_weight_data.keys():
        weight_list.append(sql_weight_data[time_list[i]])
    if time_list[i] not in sql_weight_data.keys():
        weight_list.append(None)
    if time_list[i] in sql_sleep_data.keys():
        sleep_list.append(sql_sleep_data[time_list[i]])
    if time_list[i] not in sql_sleep_data.keys():
        sleep_list.append(None)
    if time_list[i] in sql_wake_data.keys():
        wake_list.append(sql_wake_data[time_list[i]])
    if time_list[i] not in sql_wake_data.keys():
        wake_list.append(None)
sleep_data_list = []
wake_data_list = []
for i in range(sleep_list.__len__()):
    if sleep_list[i] != None:
        temp = float(re.split(r":", sleep_list[i])[0] + "." + re.split(r":", sleep_list[i])[1])
        sleep_data_list.append(temp)
    else:

        sleep_data_list.append(None)
for i in range(wake_list.__len__()):
    if wake_list[i] != None:
        temp = float(re.split(r":", wake_list[i])[0] + "." + re.split(r":", wake_list[i])[1])
        wake_data_list.append(temp)
    else:

        wake_data_list.append(None)
#sleep_list = ['23:01:00'] * 30
#
# y = sleep_data_list
# c = (
#     Line()
#     .add_xaxis(time_list)
#     .add_yaxis("商家A", y, is_connect_nones=True)
#     .set_global_opts(title_opts=opts.TitleOpts(title="Line-连接空数据"),
#     yaxis_opts=opts.AxisOpts(type_="value"),)
#     .render("line_connect_null.html")
# )
#print(sleep_data_list)

sleep_time = (
    Line(init_opts=opts.InitOpts(width="1000px",height="500px"))
    .add_xaxis(xaxis_data=time_list)
    .add_yaxis(
        series_name="入睡时间",
        y_axis=sleep_data_list,


    )
    .add_yaxis(
        series_name="起床时间",
        y_axis=wake_data_list,
        is_connect_nones=True,

    )
    .set_global_opts(
datazoom_opts=[opts.DataZoomOpts()],
        title_opts=opts.TitleOpts(),
        tooltip_opts=opts.TooltipOpts(trigger="axis"),
        #toolbox_opts=opts.ToolboxOpts(is_show=True),
        xaxis_opts=opts.AxisOpts(type_="category", boundary_gap=False),
    )
    #.render("temperature_change_line_chart.html")
)
wight_time = (
    Line(init_opts=opts.InitOpts(width="1000px",height="500px"))
    .set_global_opts(
title_opts=opts.TitleOpts(title="体重变化情况"),
datazoom_opts=[opts.DataZoomOpts()],
        tooltip_opts=opts.TooltipOpts(trigger="axis"),
        xaxis_opts=opts.AxisOpts(type_="category", boundary_gap=False),
        yaxis_opts=opts.AxisOpts(
            type_="value",
            axistick_opts=opts.AxisTickOpts(is_show=True),
            splitline_opts=opts.SplitLineOpts(is_show=True),
        ),
    )
    .add_xaxis(xaxis_data=time_list)
    .add_yaxis(
        series_name="",
        y_axis=weight_list,
        is_symbol_show=True,
        is_connect_nones=True,
    )
)
page = Page(layout=Page.SimplePageLayout)
#page=Page()
page.add(
        sleep_time,
        wight_time
    )
files = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1)
if not os.path.exists(files):
    os.mkdir(files)
file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/sleep_weight.html"
page.render(file_path)

cur.close()

conn.close()