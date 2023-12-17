#-*- coding: utf-8 -*-
from matplotlib import pyplot as plt, gridspec
import numpy as np
import matplotlib.mlab as mlab
import matplotlib.pyplot as plt
import pymysql
import re
import math
import numpy as np
import sys
from pylab import *
import datetime
from matplotlib.collections import LineCollection


plt.rcParams['font.sans-serif']=['simhei']
plt.rcParams['axes.unicode_minus']=False
user_name1 = sys.argv[1]
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()
sql = 'select * from photo_path where user_name ="'+user_name1+'"'
cur.execute(sql)
results = cur.fetchall()
date_list = []
for i in range(results.__len__()):
    date_list.append(results[i][2])
def get_list(date):
    return datetime.datetime.strptime(date,"%Y.%m.%d-%H:%M:%S").timestamp()
new_date_list = sorted(date_list,key=lambda date:get_list(date))
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
for i in range(24*60):
    temp_time = input_current_time + i
    if temp_time >= 24:
        temp_time = temp_time - 24
    time_series.append(temp_time)
    temp_string = re.split(r'\.', str(temp_time))
    time_series_new.append(str(temp_string[0]) + ":" + str(input_time[1]))
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
if int(past_time_minute) > 0:
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



for i in range(24*60):
    temp_input_current_time = ceil(input_current_time) + i/60
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
time_dict[temp_i].append(1440)


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
        input_current_time.append("现在")
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
if int(past_time_minute) > 0:
    for i in range(0, 60 - int(past_time_minute)):
        if past_time_level[i] == 1:
            if 1 not in flag:
                flag.append(1)
            data11.append(1)
            data21.append(0)
            data31.append(0)
            data41.append(0)
        if past_time_level[i] == 2:
            if 2 not in flag:
                flag.append(2)
            data11.append(0)
            data21.append(2)
            data31.append(0)
            data41.append(0)
        if past_time_level[i] == 3:
            if 3 not in flag:
                flag.append(3)
            data11.append(0)
            data21.append(0)
            data31.append(3)
            data41.append(0)
        if past_time_level[i] == 4:
            if 4 not in flag:
                flag.append(4)
            data11.append(0)
            data21.append(0)
            data31.append(0)
            data41.append(4)
    heatmap_data1.append(data11)
    heatmap_data1.append(data21)
    heatmap_data1.append(data31)
    heatmap_data1.append(data41)


    data1 = []
    data2 = []
    data3 = []
    data4 = []

    for i in range(0, 1440):
        if time_level[i] == 1:
            data1.append(1)
            data2.append(0)
            data3.append(0)
            data4.append(0)
        if time_level[i] == 2:
            data1.append(0)
            data2.append(2)
            data3.append(0)
            data4.append(0)
        if time_level[i] == 3:
            data1.append(0)
            data2.append(0)
            data3.append(3)
            data4.append(0)
        if time_level[i] == 4:
            data1.append(0)
            data2.append(0)
            data3.append(0)
            data4.append(4)
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
    color_list_1 = ['white','black','#d94e5d','#eac763','#63a950']

    cmap = colors.ListedColormap(['white','black','#d94e5d','#eac763','#63a950'])
    #cmap1 = colors.ListedColormap(['white', color_list_1])
    now_color = []
    now_color.append('white')
    for i in range(flag.__len__()):
        now_color.append(color_list_1[flag[i]])


    ratio = 1440 / (60 - int(past_time_minute))
    gs=gridspec.GridSpec(1,2,width_ratios=[1,ratio])
    plt.figure(figsize=(18,6))
    ax1=plt.subplot(gs[0])
    plt.sca(ax1)
    heatmap1 = plt.pcolor(np.array(heatmap_data1), cmap=colors.ListedColormap(now_color))

    plt.yticks([])
    ax1.spines['right'].set_visible(False)
    plt.xticks([])
    # x_major_locator=MultipleLocator(240)
    # ax1.xaxis.set_major_locator(x_major_locator)



    ax2=plt.subplot(gs[1])
    plt.sca(ax2)
    heatmap = plt.pcolor(np.array(heatmap_data), cmap=cmap)
    cbar = plt.colorbar(heatmap, ticks=[0, 1, 2, 3, 4])
    plt.yticks([])
    cbar.ax.set_yticklabels(['不推荐进食', '', '', '', '最佳进食时间'], size=25)
    ax2.spines['left'].set_visible(False)
    plt.xticks(np.arange(1440),input_current_time)
    x_major_locator=MultipleLocator(240)
    ax2.xaxis.set_major_locator(x_major_locator)
    plt.tick_params(labelsize=25)

    plt.subplots_adjust(wspace=0)
else:
    data1 = []
    data2 = []
    data3 = []
    data4 = []

    for i in range(0, 1440):
        if time_level[i] == 1:
            data1.append(1)
            data2.append(0)
            data3.append(0)
            data4.append(0)
        if time_level[i] == 2:
            data1.append(0)
            data2.append(2)
            data3.append(0)
            data4.append(0)
        if time_level[i] == 3:
            data1.append(0)
            data2.append(0)
            data3.append(3)
            data4.append(0)
        if time_level[i] == 4:
            data1.append(0)
            data2.append(0)
            data3.append(0)
            data4.append(4)


    heatmap_data.append(data1)
    heatmap_data.append(data2)
    heatmap_data.append(data3)
    heatmap_data.append(data4)
    from matplotlib import colors

    color_list_1 = ['white', 'black', '#d94e5d', '#eac763', '#63a950']

    cmap = colors.ListedColormap(['white', 'black', '#d94e5d', '#eac763', '#63a950'])
    # cmap1 = colors.ListedColormap(['white', color_list_1])
    now_color = []
    now_color.append('white')
    for i in range(flag.__len__()):
        now_color.append(color_list_1[flag[i]])

    ratio = 1440 / (60 - int(past_time_minute))

    plt.figure(figsize=(18, 6))

    ax2 = plt.axes()


    heatmap = plt.pcolor(np.array(heatmap_data), cmap=cmap)
    cbar = plt.colorbar(heatmap, ticks=[0, 1, 2, 3, 4])
    plt.yticks([])
    cbar.ax.set_yticklabels(['不推荐进食', '', '', '', '最佳进食时间'], size=25)
    plt.xticks(np.arange(1440), input_current_time)
    x_major_locator = MultipleLocator(240)
    ax2.xaxis.set_major_locator(x_major_locator)
    plt.tick_params(labelsize=25)

    plt.subplots_adjust(wspace=0)

file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.png"
plt.savefig(file_path)
cur.close()

conn.close()