# from pyecharts import options as opts
# from pyecharts.charts import Pie
# from snapshot_selenium import snapshot as driver
# from pyecharts.render import make_snapshot
# """
# Gallery 使用 pyecharts 1.1.0
# 参考地址: https://echarts.baidu.com/examples/editor.html?c=pie-doughnut
#
# 目前无法实现的功能:
#
# 1、迷之颜色映射的问题
# """
#
# x_data = ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"]
# y_data = [335, 310, 274, 235, 400]
# data_pair = [list(z) for z in zip(x_data, y_data)]
# data_pair.sort(key=lambda x: x[1])
# center_list = ['50%','50%']
# def bar_chart() -> Pie:
#     c = (
#         Pie(init_opts=opts.InitOpts(width="1600px", height="800px", bg_color="#2c343c"))
#         .add(
#             series_name="访问来源",
#             data_pair=data_pair,
#             rosetype="radius",
#             radius="55%",
#             center=center_list,
#             label_opts=opts.LabelOpts(is_show=False, position="center"),
#         )
#         .set_global_opts(
#             title_opts=opts.TitleOpts(
#                 title="Customized Pie",
#                 pos_left="center",
#                 pos_top="20",
#                 title_textstyle_opts=opts.TextStyleOpts(color="#fff"),
#             ),
#             legend_opts=opts.LegendOpts(is_show=False),
#         )
#         .set_series_opts(
#             tooltip_opts=opts.TooltipOpts(
#                 trigger="item", formatter="{a} <br/>{b}: {c} ({d}%)"
#             ),
#             label_opts=opts.LabelOpts(color="rgba(255, 255, 255, 0.3)"),
#         )
#         #.render("customized_pie.html")
#     )
#     return c
# make_snapshot(driver, bar_chart().render("customized_pie.html"), "bar.png")
#
# from snapshot_selenium import snapshot as driver
#
# from pyecharts import options as opts
# from pyecharts.charts import Bar
# from pyecharts.render import make_snapshot
#
#
# def bar_chart() -> Bar:
#     c = (
#         Bar()
#         .add_xaxis(["衬衫", "毛衣", "领带", "裤子", "风衣", "高跟鞋", "袜子"])
#         .add_yaxis("商家A", [114, 55, 27, 101, 125, 27, 105])
#         .add_yaxis("商家B", [57, 134, 137, 129, 145, 60, 49])
#         .reversal_axis()
#         .set_series_opts(label_opts=opts.LabelOpts(position="right"))
#         .set_global_opts(title_opts=opts.TitleOpts(title="Bar-测试渲染图片"))
#     )
#     return c
#
# # 需要安装 snapshot-selenium 或者 snapshot-phantomjs
# make_snapshot(driver, bar_chart().render(), "bar.png")
import pyecharts.options as opts
from pyecharts.charts import Line, Grid
from pyecharts.faker import Faker
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

    cur.close()

    conn.close()
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
    #print(week_y)
    x_axis = []
    for i in range(0, 24):
        x_axis.append(str(i) + ":" + "00")
        x_axis.append(str(i) + ":" + "30")

    # c = (
    #     Line()
    #     .add_xaxis(x_axis)
    #     .add_yaxis("工作日", new_y
    #                )
    # .add_yaxis("休息日", week_y)
    #     .set_series_opts(
    #         areastyle_opts=opts.AreaStyleOpts(opacity=0.5),
    #         label_opts=opts.LabelOpts(is_show=False),
    #     tooltip_opts=opts.TooltipOpts(is_show=True)
    #     )
    #     .set_global_opts(
    #         title_opts=opts.TitleOpts(title="Line-面积图（紧贴 Y 轴）"),
    # tooltip_opts=opts.TooltipOpts(trigger="item", formatter="{a} <br/>{b}"),
    #         xaxis_opts=opts.AxisOpts(
    #             type_ = 'category',
    #             split_number=12,
    #             min_interval=4,
    #             axistick_opts=opts.AxisTickOpts(is_align_with_label=True),
    #             is_scale=False,
    #             boundary_gap=False,
    #         ),
    #     )
    #     .render("line_areastyle_boundary_gap.html")
    # )
    # (
    #     Line()
    #     .add_xaxis(xaxis_data=range(0,48))
    #     .add_yaxis(
    #         series_name="",
    #         y_axis=work_plot_data,
    #         symbol="emptyCircle",
    #         is_symbol_show=True,
    #         label_opts=opts.LabelOpts(is_show=False),
    #         areastyle_opts=opts.AreaStyleOpts(opacity=1, color="#C67570"),
    #     )
    #     .set_global_opts(
    #         tooltip_opts=opts.TooltipOpts(is_show=False),
    #         yaxis_opts=opts.AxisOpts(
    #             type_="value",
    #             axistick_opts=opts.AxisTickOpts(is_show=True),
    #             splitline_opts=opts.SplitLineOpts(is_show=True),
    #         ),
    #         xaxis_opts=opts.AxisOpts(type_="category", boundary_gap=False),
    #     )
    #     # 设置 boundary_gap 的时候一定要放在最后一个配置项里, 不然会被覆盖
    #     .render("basic_area_chart.html")
    # )






    #
    # z = list(zip(range(0,96),work_plot_data))
    # X = np.array(z)
    # kde = kde.KernelDensity(kernel='gaussian', bandwidth=0.2).fit(X)
    # new_x = kde.score_samples(X)
    line = (
        Line(init_opts=opts.InitOpts(width = "500px",height="300px"))
        .add_xaxis(xaxis_data=x_axis)
        .add_yaxis(
            series_name="工作日",
            y_axis=new_y,
            is_smooth=True,
            is_symbol_show=False,
            symbol="circle",
            #symbol_size=6,
            #linestyle_opts=opts.LineStyleOpts(color=JsCode(area_color_js)),
            linestyle_opts=opts.LineStyleOpts(color='#C9451E'),

            label_opts=opts.LabelOpts(is_show=False, position="top", color="white"),
            itemstyle_opts=opts.ItemStyleOpts(
                color="#FFB99B",  border_width=1
            ),
            #tooltip_opts=opts.TooltipOpts(is_show=True),
            areastyle_opts=opts.AreaStyleOpts(color=JsCode(area_color_js1), opacity=1),
            #areastyle_opts=opts.AreaStyleOpts(color='#F4D4D5', opacity=1),
        )
    .add_yaxis(
            series_name="休息日",
            y_axis=week_y,
            is_smooth=True,
            is_symbol_show=False,
            # symbol="circle",
            # symbol_size=6,
            #linestyle_opts=opts.LineStyleOpts(color=JsCode(area_color_js)),
            linestyle_opts=opts.LineStyleOpts(color='#C9451E'),
            label_opts=opts.LabelOpts(is_show=False, position="top", color="white"),
            itemstyle_opts=opts.ItemStyleOpts(
                color="#FFB99B", border_width=1
            ),
            #tooltip_opts=opts.TooltipOpts(is_show=True),
            areastyle_opts=opts.AreaStyleOpts(color=JsCode(background_color_js), opacity=1),
            #areastyle_opts=opts.AreaStyleOpts(color='#F4D4D5', opacity=1),
        )
    # .add_yaxis(
    #         series_name="注册总量1",
    #         y_axis=week_y,
    #         is_smooth=True,
    #         is_symbol_show=True,
    #         symbol="circle",
    #         symbol_size=6,
    #         linestyle_opts=opts.LineStyleOpts(color="#fff"),
    #         label_opts=opts.LabelOpts(is_show=True, position="top", color="white"),
    #         itemstyle_opts=opts.ItemStyleOpts(
    #             color="red", border_color="#fff", border_width=3
    #         ),
    #         tooltip_opts=opts.TooltipOpts(is_show=False),
    #         areastyle_opts=opts.AreaStyleOpts(color=JsCode(area_color_js), opacity=1),
    #     )
        .set_global_opts(
    tooltip_opts=opts.TooltipOpts(formatter="{a} <br/>{b}",trigger="axis", axis_pointer_type="shadow",),
            title_opts=opts.TitleOpts(
                title="你的饮食规律",

                title_textstyle_opts=opts.TextStyleOpts(color="black",font_size=15),
            ),
            xaxis_opts=opts.AxisOpts(
                type_="category",
                boundary_gap=False,
                axislabel_opts=opts.LabelOpts(color="black"),
                axisline_opts=opts.AxisLineOpts(is_show=True),
                axistick_opts=opts.AxisTickOpts(
                    is_show=True,
                    length=25,
                    linestyle_opts=opts.LineStyleOpts(color="#ffffff1f"),
                ),
                splitline_opts=opts.SplitLineOpts(
                    is_show=True, linestyle_opts=opts.LineStyleOpts(color="#ffffff1f")
                ),
            ),
            yaxis_opts=opts.AxisOpts(
                type_="value",
                position="right",
                is_show = False,
                axislabel_opts=opts.LabelOpts(margin=20, color="#ffffff63"),
                axisline_opts=opts.AxisLineOpts(
                    linestyle_opts=opts.LineStyleOpts(width=2, color="#fff")
                ),
                axistick_opts=opts.AxisTickOpts(
                    is_show=True,
                    length=15,
                    linestyle_opts=opts.LineStyleOpts(color="#ffffff1f"),
                ),
                splitline_opts=opts.SplitLineOpts(
                    is_show=True, linestyle_opts=opts.LineStyleOpts(color="#ffffff1f")
                ),
            ),
            legend_opts=opts.LegendOpts(is_show=True,selected_mode="single"),
        )
    )

    # (
    #     Grid()
    #     .add(
    #         c,
    #         grid_opts=opts.GridOpts(
    #             pos_top="20%",
    #             pos_left="10%",
    #             pos_right="10%",
    #             pos_bottom="15%",
    #             is_contain_label=True,
    #         ),
    #     )
    #     .render("beautiful_line_chart.html")
    # )
    results = cur.fetchall()
    date_list = []
    for i in range(results.__len__()):
        date_list.append(results[i][2])

    data_dict = {}
    max_id = 0
    current_time = new_date_list[len(new_date_list) - 1]
    current_day = re.split(r'-',current_time)[0]
    current_hour = re.split(r'-',current_time)[1]
    temp = re.split(r':', current_hour)
    hour_list = temp[0]
    minute_list = temp[1]
    past_time_minute = re.split(r':', current_hour)[1]
    past_time_hour = re.split(r':', current_hour)[0]
    if int(temp[0]) < 3:
        current_day = [current_day]
        temp_day = datetime.datetime.strptime(current_day[0], '%Y.%m.%d') - datetime.timedelta(days=1)
        temp_day = str(temp_day.year) + "." + str(temp_day.month) + "." + str(temp_day.day)
        current_day.append(temp_day)
    else:
        current_day = [current_day]
    time_list = []


    input_time = []
    for i in range(new_date_list.__len__()):
        temp_i = new_date_list.__len__() - i - 1
        temp_current_day = re.split(r'-', new_date_list[i])[0]
        temp_current_hour = re.split(r'-', new_date_list[i])[1]
        if len(current_day) > 1:
            if temp_current_day == current_day[1]:
                temp2 = re.split(r":",temp_current_hour)
                input_time.append(temp2[0])
                input_time.append(temp2[1])
                input_hour = float(int(temp2[0]) + (int(temp2[1]) / 60))


                time_list.append(input_hour)
            elif temp_current_day == current_day[0]:
                temp2 = re.split(r":", temp_current_hour)
                input_time.append(temp2[0])
                input_time.append(temp2[1])
                input_hour = float(int(temp2[0]) + (int(temp2[1]) / 60)) + 24


                time_list.append(input_hour)
        else:
            if temp_current_day == current_day[0]:
                temp2 = re.split(r":", temp_current_hour)
                input_time.append(temp2[0])
                input_time.append(temp2[1])
                input_hour = float(int(temp2[0]) + (int(temp2[1]) / 60))

                time_list.append(input_hour)


    print(time_list)
    new_time_list = []
    time_series = []
    time_level = []
    first_time = time_list[0]
    input_current_time = time_list[len(time_list) - 1]
    time_series_new = []
    start_flag = 0
    start_hour = floor(input_current_time)
    if input_current_time % 1 >= 0.5:
        input_current_time = floor(input_current_time) + 0.5
        start_flag = 1
    else:
        input_current_time = floor(input_current_time)
    for i in range(0, 48):
        temp_time = input_current_time + i * 30
        if temp_time >= 24:
            temp_time = temp_time - 24
        time_series.append(temp_time)
        temp_string = re.split(r'\.', str(temp_time))
        time_series_new.append(str(temp_string[0]) + ":" + str(input_time[1]))
    if input_current_time >= 17:

        if first_time > 22:

            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        elif input_current_time >= 22:
            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        elif input_current_time >= first_time + 12:
            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        else:
            limit_8 = first_time + 8
            limit_10 = first_time + 10
            limit_12 = first_time + 12
            limit_no12 = input_current_time + 12
            if limit_12 >= limit_no12:
                limit_no12 = limit_12
    else:
        if first_time > 22:

            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        elif input_current_time >= 22:
            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        elif input_current_time >= first_time + 12:
            limit_no12 = input_current_time + 12
            limit_8 = limit_no12 + 8
            limit_10 = limit_no12 + 10
            limit_12 = limit_no12 + 12
        else:
            limit_8 = first_time + 8
            limit_10 = first_time + 10
            limit_12 = first_time + 12
            limit_no12 = min(limit_12 + 12, 22 + 12)

    print(limit_8)
    print(limit_10)
    print(limit_12)
    print(limit_no12)
    color_list = []
    colors = ['#50a3ba','#eac763','#d94e5d','#63a950']
    past_time_level = []
    if int(past_time_minute) > 30:
        for i in range(60 - int(past_time_minute)):
            temp_input_current_time = input_current_time + i/60
            if temp_input_current_time < limit_8 and temp_input_current_time < 22:
                past_time_level.append(4)
                color_list.append(colors[3])
            elif temp_input_current_time < limit_10 and temp_input_current_time < 22:
                past_time_level.append(3)
                color_list.append(colors[2])
            elif temp_input_current_time < limit_12 and temp_input_current_time < 22:
                past_time_level.append(2)
                color_list.append(colors[1])
            elif temp_input_current_time < limit_no12:
                past_time_level.append(1)
                color_list.append(colors[0])
            # elif temp_input_current_time >= 22 and temp_input_current_time < limit_no12:
            #     time_level.append(1)
            elif temp_input_current_time >= limit_no12:
                if temp_input_current_time < limit_8:
                    past_time_level.append(4)
                    color_list.append(colors[3])
                elif temp_input_current_time < limit_10:
                    past_time_level.append(3)
                    color_list.append(colors[2])
                elif temp_input_current_time < limit_12:
                    past_time_level.append(2)
                    color_list.append(colors[1])
                elif temp_input_current_time < limit_no12 + 8:
                    past_time_level.append(4)
                    color_list.append(colors[3])
                elif temp_input_current_time < limit_no12 + 10:
                    past_time_level.append(3)
                    color_list.append(colors[2])
                elif temp_input_current_time < limit_no12 + 12:
                    past_time_level.append(2)
                    color_list.append(colors[1])
                elif temp_input_current_time < limit_no12 + 24:
                    past_time_level.append(1)
                    color_list.append(colors[0])
            else:
                time_level.append(1)
                color_list.append(colors[0])



    for i in range(0, 48):
        temp_input_current_time = ceil(input_current_time) + i/2
        if temp_input_current_time < limit_8 and temp_input_current_time < 22:
            time_level.append(4)
            color_list.append(colors[3])
        elif temp_input_current_time < limit_10 and temp_input_current_time < 22:
            time_level.append(3)
            color_list.append(colors[2])
        elif temp_input_current_time < limit_12 and temp_input_current_time < 22:
            time_level.append(2)
            color_list.append(colors[1])
        elif temp_input_current_time < limit_no12:
            time_level.append(1)
            color_list.append(colors[0])
        # elif temp_input_current_time >= 22 and temp_input_current_time < limit_no12:
        #     time_level.append(1)
        elif temp_input_current_time >= limit_no12:
            if temp_input_current_time < limit_8:
                time_level.append(4)
                color_list.append(colors[3])
            elif temp_input_current_time < limit_10:
                time_level.append(3)
                color_list.append(colors[2])
            elif temp_input_current_time < limit_12:
                time_level.append(2)
                color_list.append(colors[1])
            elif temp_input_current_time < limit_no12 + 8:
                time_level.append(4)
                color_list.append(colors[3])
            elif temp_input_current_time < limit_no12 + 10:
                time_level.append(3)
                color_list.append(colors[2])
            elif temp_input_current_time < limit_no12 + 12:
                time_level.append(2)
                color_list.append(colors[1])
            elif temp_input_current_time < limit_no12 + 24:
                time_level.append(1)
                color_list.append(colors[0])
        else:
            time_level.append(1)
            color_list.append(colors[0])


    # color = []
    # for i in range(time_level.__len__()):
    #     if time_level[i] == 1:
    #         color.append("#50a3ba")
    #     elif time_level[i] == 2:
    #         color.append("#eac763")
    #     elif time_level[i] == 3:
    #         color.append("#d94e5d")
    #     elif time_level[i] == 4:
    #         color.append("#63a950")
    time_value = []
    time_num = []
    temp_num = 0
    time_dict = {}
    for i in range(time_level.__len__()):
        temp_i = time_level[i]
        if i == 0:
            time_dict[temp_i] = []
            time_dict[temp_i].append(i)
            time_value.append(temp_i)
            temp_num = 0
        elif i > 0:
            temp_i = time_level[i - 1]
        if time_level[i] == temp_i:
            temp_num += 1
            continue
        else:
            time_dict[temp_i].append(i)
            temp_i = time_level[i]

            time_value.append(temp_i)
            time_num.append(temp_num)
            if temp_i in time_dict.keys():
                time_dict[temp_i].append(i)
            else:
                time_dict[temp_i] = []
                time_dict[temp_i].append(i)
            temp_num = 0
    time_num.append(temp_num)
    time_dict[temp_i].append(48)


    # color = []
    # labels = []
    # explode = []
    # for i in range(time_value.__len__()):
    #     if time_value[i] == 1:
    #         explode.append(0.1)
    #     else:
    #         explode.append(0.01)
    #     color.append(colors[int(time_value[i]) - 1])
    #     labels.append(font[int(time_value[i]) - 1])

    input_current_time = []
    input_current_time.append('start')
    num = 0
    if int(minute_list) > 0:
        hour_list = int(hour_list) + 1
        if hour_list >= 24:
            hour_list -= 24
    hour_list = str(hour_list)

    for i in range(24):
        num += 1
        if i == 0:
            #input_current_time.append("现在")
            input_current_time.append(datetime.datetime.now())
        elif num % 4 == 0:
            hour_list = int(hour_list) + 4
            if hour_list >= 24:
                hour_list -= 24
            input_current_time.append(str(hour_list) + ":" + "00")




    mpl.rc("figure", figsize=(12,4))

    plt.figure(figsize=(18,6)) #调节图形大小
    plt.figure(1)
    ax1 = plt.axes()
    #第一行第二列图形
    # ax2 = plt.subplot(1,2,2)
    #plt.subplots_adjust(wspace =0.01, hspace =0.01)
    plt.sca(ax1)
    font_string = [u'不推荐进食', u'12小时进食', u'10小时进食', u'8小时进食']
    lwidths = abs(5)
    heatmap_data = []
    data11 = []
    data21 = []
    data31 = []
    data41 = []
    heatmap_data1 = []
    flag = []


    test_data = []


    # for i in range(0, 60 - int(past_time_minute)):
    #     if past_time_level[i] == 1:
    #         if 1 not in flag:
    #             flag.append(1)
    #         data11.append(1)
    #         data21.append(0)
    #         data31.append(0)
    #         data41.append(0)
    #
    #     if past_time_level[i] == 2:
    #         if 2 not in flag:
    #             flag.append(2)
    #         data11.append(2)
    #         data21.append(2)
    #         data31.append(0)
    #         data41.append(0)
    #     if past_time_level[i] == 3:
    #         if 3 not in flag:
    #             flag.append(3)
    #         data11.append(3)
    #         data21.append(3)
    #         data31.append(3)
    #         data41.append(0)
    #     if past_time_level[i] == 4:
    #         if 4 not in flag:
    #             flag.append(4)
    #         data11.append(4)
    #         data21.append(4)
    #         data31.append(4)
    #         data41.append(4)
    # heatmap_data1.append(data11)
    # heatmap_data1.append(data21)
    # heatmap_data1.append(data31)
    # heatmap_data1.append(data41)


    data1 = []
    data2 = []
    data3 = []
    data4 = []
    data0 = []
    for i in range(0, 48):
        if time_level[i] == 1:

            data1.append(0)
            data2.append(0)
            data3.append(0)
            data4.append(0)

            test_data.append([i, 0, 0.5])
        if time_level[i] == 2:

            data1.append(2)
            data2.append(2)
            data3.append(0)
            data4.append(0)

            test_data.append([i, 1, 2])
            test_data.append([i, 0, 2])
        if time_level[i] == 3:

            data1.append(3)
            data2.append(3)
            data3.append(3)
            data4.append(0)

            test_data.append([i, 2, 3])
            test_data.append([i, 1, 3])
            test_data.append([i, 0, 3])
        if time_level[i] == 4:

            data1.append(4)
            data2.append(4)
            data3.append(4)
            data4.append(4)

            test_data.append([i, 3, 4])
            test_data.append([i, 2, 4])
            test_data.append([i, 1, 4])
            test_data.append([i, 0, 4])
    # for i in range(0, 1440):
    #     if time_level[i] == 1:
    #         data1.append('12')
    #         data2.append('0')
    #         data3.append('0')
    #         data4.append('0')
    #     if time_level[i] == 2:
    #         data1.append('0')
    #         data2.append('10')
    #         data3.append('0')
    #         data4.append('0')
    #     if time_level[i] == 3:
    #         data1.append('0')
    #         data2.append('0')
    #         data3.append('8')
    #         data4.append('0')
    #     if time_level[i] == 4:
    #         data1.append('0')
    #         data2.append('0')
    #         data3.append('0')
    #         data4.append('test')

    heatmap_data.append(data1)
    heatmap_data.append(data2)
    heatmap_data.append(data3)
    heatmap_data.append(data4)
    from matplotlib import colors
    color_list_1 = ['white','#3F4342','#D4A68D','#FFB99B','#C9451E']

    cmap = colors.ListedColormap(['white','#3F4342','#D4A68D','#FFB99B','#C9451E'])
    #cmap1 = colors.ListedColormap(['white', color_list_1])
    now_color = []
    now_color.append('white')
    for i in range(flag.__len__()):
        now_color.append(color_list_1[flag[i]])


    # value = []
    # value.append([0]*48)
    # value.append([1]*48)
    # value.append([2]*48)
    # value.append([3]*48)
    # print(value)
    time_list_input = []
    time_list_input_date = []
    data_dic = {}
    num = 0
    x_axis = []
    if int(re.split(r':', current_hour)[1]) < 10:
        current_h_m = re.split(r':', current_hour)[0] + ":0" + re.split(r':', current_hour)[1]
    else:
        current_h_m = re.split(r':', current_hour)[0] + ":" + re.split(r':', current_hour)[1]
    if start_flag == 1:
        for i in range(0, 25):
            temp_time = start_hour + i
            temp_time1 = start_hour + i + 1
            if temp_time >= 24:
                temp_time -= 24
            if temp_time1 >= 24:
                temp_time1 -= 24
            if i == 0:
                x_axis.append("现在")
                #x_axis.append(current_h_m)
                #x_axis.append(datetime.datetime.now().minute)
            else:
                x_axis.append(str(int(temp_time)) + ":" + "30")
                x_axis.append(str(int(temp_time1)) + ":" + "00")
    else:
        for i in range(0, 25):
            temp_time = start_hour + i
            if temp_time >= 24:
                temp_time -= 24
            if i == 0:
                x_axis.append("现在")
                #x_axis.append(current_h_m)
                #x_axis.append(datetime.datetime.now().minute)
            else:
                x_axis.append(str(int(temp_time)) + ":" + "00")
                x_axis.append(str(int(temp_time)) + ":" + "30")
    # for i in range(0, 24):
    #     for j in range(0, 60):
    #         if i < 10:
    #             if j < 10:
    #                 st = '0' + str(i) + ":0" + str(j)+ ':00'
    #                 time_list_input_date.append(datetime.datetime.strptime(st, "%H:%M:%S"))
    #                 time_list_input.append('0' + str(i) + ":0" + str(j) + ':00')
    #                 data_dic['0' + str(i) + ":0" + str(j)+ ':00'] = num
    #             else:
    #                 st = '0' + str(i) + ":" + str(j)+ ':00'
    #                 time_list_input_date.append(datetime.datetime.strptime(st, "%H:%M:%S"))
    #                 time_list_input.append('0' + str(i) + ":" + str(j)+ ':00')
    #                 data_dic['0' + str(i) + ":" + str(j)+ ':00'] = num
    #         else:
    #             if j < 10:
    #                 st = str(i) + ":0" + str(j)+ ':00'
    #                 time_list_input_date.append(datetime.datetime.strptime(st, "%H:%M:%S"))
    #                 time_list_input.append(str(i) + ":0" + str(j)+ ':00')
    #                 data_dic[str(i) + ":0" + str(j)+ ':00'] = num
    #             else:
    #                 st = str(i) + ":" + str(j)+ ':00'
    #                 time_list_input_date.append(datetime.datetime.strptime(st, "%H:%M:%S"))
    #                 time_list_input.append(str(i) + ":" + str(j)+ ':00')
    #                 data_dic[str(i) + ":" + str(j)+ ':00'] = num
    #        num += 1
    heatmap = (
        HeatMap(init_opts=opts.InitOpts(width="500px",height="300px"))
        .add_xaxis(x_axis)
        .add_yaxis(
            "饮食时间",
            ['不推荐饮食','','','最佳饮食时间','','','','','','','',''],
            test_data,
            #label_opts=opts.LabelOpts(is_show=True, position="inside"),
        )
        .set_global_opts(


    tooltip_opts=opts.TooltipOpts(formatter="{a} <br/>{b}",trigger="axis", axis_pointer_type="shadow"),

            title_opts=opts.TitleOpts(title="饮食时间推荐",title_textstyle_opts=opts.TextStyleOpts(color="black",font_size=15),),
            visualmap_opts=opts.VisualMapOpts(
                pos_top = 0.05,
                pos_right=0.0,
    item_width = 15,
                item_height=80,
                range_text = ['最佳饮食时间','不推荐饮食'],
                min_ = 1,
                max_=4,
    is_calculable = False,
                range_color = ['#3F4342','#D4A68D','#FFB99B','#C9451E'],
                #range_color=['#e50000','#75bbfd','#01386a','#1b2431'],
                #range_color=['#000000', 'darksalmon', 'coral', 'orangered'],
                #range_color=['#4b0101', '#658cbb', '#448ee4', '#2c6fbb'],

                range_size=1,
                textstyle_opts =opts.TextStyleOpts(color="black",font_size=10) ),
    yaxis_opts=opts.AxisOpts(

                is_show = False,

            ),
            legend_opts=opts.LegendOpts(is_show=False),
        )

    )


    page = Page(layout=Page.SimplePageLayout)
    page.add(
            line,
            heatmap
        )
    files = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1)
    if not os.path.exists(files):
        os.mkdir(files)
    file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.html"
    page.render(file_path)

    cur.close()

    conn.close()
    # from pyecharts.charts import Gauge
    # data_pair = [{}]
    # c = (
    #     Gauge()
    #     .add(
    #         "业务指标",
    #         [("完成率", 55.5)],
    # max_ = 1440,
    # start_angle = 90,
    # end_angle = -269,
    #         axisline_opts=opts.AxisLineOpts(
    #             linestyle_opts=opts.LineStyleOpts(
    #                 color=[(0.3, "green"), (0.6, "#37a2da"),(0.8, "#37a2da"), (1, "red")], width=30
    #             )
    #         ),
    #     )
    #     .add_dataset()
    #     .set_global_opts(
    #         title_opts=opts.TitleOpts(title="Gauge-不同颜色"),
    #         legend_opts=opts.LegendOpts(is_show=False),
    #     )
    #     .render("gauge_color.html")
    # )