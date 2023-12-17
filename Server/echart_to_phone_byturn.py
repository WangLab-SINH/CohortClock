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
#print(user_name1)
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()  # 获取游标

file_name_list = []
output_string = ""
for root,dirs,files in os.walk("/var/www/html/deeplearning_photo/temp_photo_byturn/train/0/"):
    for file in files:
        file_name_list.append(file)
user_name_list = []
for file_num in range(file_name_list.__len__()):
    sql = 'select user_name from photo_path where photo_name ="'+file_name_list[file_num]+'"'
    cur.execute(sql)
    results = cur.fetchall()

    user_name1 = results[0][0]

    output_string += file_name_list[file_num] + ":"



#sql =  r'''select * from photo_path where user_name = "'+user_name1+'" '''
    sql = 'select * from photo_path where user_name ="'+user_name1+'"'
    cur.execute(sql)
    results = cur.fetchall()
    #print(results)
    date_list = []
    for i in range(results.__len__()):
        if results[i][5] == 'food':
            date_list.append(results[i][2])
    #对数据进行排序
    def get_list(date):
        return datetime.datetime.strptime(date,"%Y.%m.%d-%H:%M:%S").timestamp()
    new_date_list = sorted(date_list,key=lambda date:get_list(date))

    if len(new_date_list) == 0:


        page = Page(layout=Page.SimplePageLayout)
        page.add(
        )
        files = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1)
        if not os.path.exists(files):
            os.mkdir(files)
        file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.html"
        page.render(file_path)
        output_str = ""
        for i in range(48):
            if i != 47:
                output_str += "0" + ","
            else:
                output_str += "0" + ";"
        for i in range(48):
            if i != 47:
                output_str += "0" + ","
            else:
                output_str += "0"
        output_string += output_str + "<"



    else:

        current_time = re.split(r'-',new_date_list[-1])[0]
        #***
         #   分割为工作日和休息日
        #***
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
                            if minus_time > 30:
                                temp_str = re.split(r'-', new_date_list[i])
                                temp_str1 = int(
                                    round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
                                work_plot_data_list.append(temp_str1)
                                work_plot_data_dic[temp_string].append(temp_str1)
                                # 有效的进食时间flag
                                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                        else:
                            temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                            temp = temp_time - last_time
                            minus_time = temp.days * 24 * 60 + temp.seconds / 60
                            # 15分钟间隔以内算一顿饭
                            if minus_time > 30:
                                temp_str = re.split(r'-', new_date_list[i])
                                temp_str1 = int(
                                    round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
                                work_plot_data_list.append(temp_str1)
                                work_plot_data_dic[temp_string] = []
                                work_plot_data_dic[temp_string].append(temp_str1)
                                # 有效的进食时间flag
                                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    else:
                        temp_string = re.split(r'-', new_date_list[i])[0]
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
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
                            if minus_time > 30:
                                temp_str = re.split(r'-', new_date_list[i])
                                temp_str1 = int(
                                    round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
                                weekend_plot_data_list.append(temp_str1)
                                weekend_plot_data_dic[temp_string].append(temp_str1)
                                # 有效的进食时间flag
                                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                        else:
                            temp_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                            temp = temp_time - last_time
                            minus_time = temp.days * 24 * 60 + temp.seconds / 60
                            # 15分钟间隔以内算一顿饭
                            if minus_time > 30:
                                temp_str = re.split(r'-', new_date_list[i])
                                temp_str1 = int(
                                    round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
                                weekend_plot_data_list.append(temp_str1)
                                weekend_plot_data_dic[temp_string] = []
                                weekend_plot_data_dic[temp_string].append(temp_str1)
                                # 有效的进食时间flag
                                last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                    else:
                        temp_string = re.split(r'-', new_date_list[i])[0]
                        last_time = datetime.datetime.strptime(new_date_list[i], "%Y.%m.%d-%H:%M:%S")
                        temp_str = re.split(r'-', new_date_list[i])
                        temp_str1 = int(round(int(re.split(r':', temp_str[1])[0]) * 60 + int(re.split(r':', temp_str[1])[1])) / 30)
                        weekend_plot_data_list.append(temp_str1)
                        weekend_plot_data_dic[temp_string] = []
                        weekend_plot_data_dic[temp_string].append(temp_str1)


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
        work_plot_data = [0] * 96
        for i in range(96):
            if i in work_plot_data_list_new.keys():
                work_plot_data[i] += work_plot_data_list_new[i]
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

        # work_plot_data = [0] * 96
        # for i in range(work_plot_data_list.__len__()):
        #     work_plot_data[work_plot_data_list[i]] += 1
        # print(work_plot_data)





        background_color_js = (
            "new echarts.graphic.LinearGradient(0, 0, 0, 1, "
            "[{offset: 0, color: '#2242c7'}, {offset: 1, color: '#87CEFA'}], false)"
        )
        area_color_js = (
            "new echarts.graphic.LinearGradient(0, 0, 0, 1, "
            "[{offset: 0, color: '#C9451E'}, {offset: 1, color: 'white'}], false)"
        )
        area_color_js1 = (
            "new echarts.graphic.LinearGradient(0, 0, 0, 1, "
            "[{offset: 0, color: '#f9bc08'}, {offset: 1, color: '#fbdd7e'}], false)"
        )
    #FFCF47

        from pyecharts.commons.utils import JsCode
        from sklearn.neighbors import kde
        import numpy as np
        if len(pd_y) == 0:
            pd_y.append(0)
        data = np.asarray(pd_y)
        data = data.astype(np.float64)
        if len(pd_y_weekend) == 0:
            pd_y_weekend.append(0)
        data1 = np.asarray(pd_y_weekend)
        data1 = data1.astype(np.float64)
        warn = False
        bivariate = False
        if isinstance(data, np.ndarray) and np.ndim(data) > 1:
            warn = True
            bivariate = True
            x, y = data.T
        bw="scott"
        gridsize=48
        cut=3
        clip = (-np.inf, np.inf)
        kernel="gau"
        cumulative=False
        import statsmodels.nonparametric.api as smnp
        def _statsmodels_univariate_kde(data, kernel, bw, gridsize, cut, clip,
                                        cumulative=False):
            """Compute a univariate kernel density estimate using statsmodels."""
            fft = kernel == "gau"
            kde = smnp.KDEUnivariate(data)
            kde.fit(kernel, bw, fft, gridsize=gridsize, cut=cut, clip=clip)
            if cumulative:
                grid, y = kde.support, kde.cdf
            else:
                grid, y = kde.support, kde.density
            return grid, y, kde
        x, y, equ = _statsmodels_univariate_kde(data, kernel, bw,
                                                   gridsize, cut, clip,
                                                   cumulative=cumulative)
        new_y = equ.evaluate(np.array(range(0,48)))
        x1, y1, equ1 = _statsmodels_univariate_kde(data1, kernel, bw,
                                                   gridsize, cut, clip,
                                                   cumulative=cumulative)
        week_y = equ1.evaluate(np.array(range(0,48)))
        output_str = ""
        #print(week_y)
        x_axis = []
        for i in range(0, 24):
            x_axis.append(str(i) + ":" + "00")
            x_axis.append(str(i) + ":" + "30")

        new_y = new_y.tolist()
        week_y = week_y.tolist()
        for i in range(48):
            if i != 47:
                output_str += str(new_y[i]) + ","
            else:
                output_str += str(new_y[i]) + ";"
        for i in range(48):
            if i != 47:
                output_str += str(week_y[i]) + ","
            else:
                output_str += str(week_y[i])
        output_string += output_str + "<"


cur.close()
conn.close()
output_string = output_string[:-1]
print(output_string)