#-*- coding: utf-8 -*-
from matplotlib import pyplot as plt
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


# print(time_list)
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

# print(limit_8)
# print(limit_10)
# print(limit_12)
# print(limit_no12)
color_list = []
colors = ['#50a3ba','#eac763','#d94e5d','#63a950']
for i in range(24*60):
    temp_input_current_time = input_current_time + i/60
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
for i in range(24):
    num += 1
    if i == 0:
        input_current_time.append(hour_list + ":" + minute_list)
    elif num % 4 == 0:
        hour_list = int(hour_list) + 4
        if hour_list >= 24:
            hour_list -= 24
        input_current_time.append(str(hour_list) + ":" + minute_list)




plt.figure(figsize=(9,6)) #调节图形大小


font_string = [u'不推荐进食', u'12小时进食', u'10小时进食', u'8小时进食']
lwidths = abs(3)
x = np.array(range(0,1440))
y = time_level
#print(x)
#print(y)
#print('--------------------------------------')
points = np.array([x, y]).T.reshape(-1, 1, 2)
#print(points)
#print('--------------------------------------')
segments = np.concatenate([points[:-1], points[1:]], axis=1)
#print(segments)
lc = LineCollection(segments, linewidths=lwidths, color=color_list)
plt.xticks(np.arange(1450),input_current_time)
ax = plt.axes()
for i in time_dict.keys():
   temp_list = time_dict[i]
   for j in range(temp_list.__len__()):
       if j % 2 == 0:
           start = time_dict[i][j]
       else:
           end = time_dict[i][j]
           temp_x = (start + end) / 2.5
           temp_y = int(i) + 0.3
           plt.text(temp_x, temp_y, font_string[int(i) - 1],size = 15)

#ax.set_xlim(min(x), max(x))
ax.set_ylim(0, 5)
ax.add_collection(lc)
plt.yticks([])
x_major_locator=MultipleLocator(240)
ax=plt.gca()
ax.xaxis.set_major_locator(x_major_locator)
file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.png"
plt.savefig(file_path)


cur.close()

conn.close()
