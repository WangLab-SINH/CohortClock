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
for i in range(24*60):
    temp_input_current_time = input_current_time + i/60
    if temp_input_current_time < limit_8 and temp_input_current_time < 22:
        time_level.append(4)
    elif temp_input_current_time < limit_10 and temp_input_current_time < 22:
        time_level.append(3)
    elif temp_input_current_time < limit_12 and temp_input_current_time < 22:
        time_level.append(2)
    elif temp_input_current_time < limit_no12:
        time_level.append(1)
    # elif temp_input_current_time >= 22 and temp_input_current_time < limit_no12:
    #     time_level.append(1)
    elif temp_input_current_time >= limit_no12:
        if temp_input_current_time < limit_8:
            time_level.append(4)
        elif temp_input_current_time < limit_10:
            time_level.append(3)
        elif temp_input_current_time < limit_12:
            time_level.append(2)
        elif temp_input_current_time < limit_no12 + 8:
            time_level.append(4)
        elif temp_input_current_time < limit_no12 + 10:
            time_level.append(3)
        elif temp_input_current_time < limit_no12 + 12:
            time_level.append(2)
        elif temp_input_current_time < limit_no12 + 24:
            time_level.append(1)
    else:
        time_level.append(1)
first_1 = 0
first_2 = 0
first_8 = 0
first_10 = 0
end_1 = 0
end_2 = 0
end_8 = 0
end_10 = 0
for i in range(time_level.__len__()):
    if time_level[i] == 1:
        if first_1 == 0:
            first_1 = i
        end_1 = i
    elif time_level[i] == 2:
        if first_2 == 0:
            first_2 = i
        end_2 = i
    elif time_level[i] == 8:
        if first_8 == 0:
            first_8 = i
        end_8 = i
    elif time_level[i] == 10:
        if first_10 == 0:
            first_10 = i
        end_10 = i

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
for i in range(time_level.__len__()):
    temp_i = time_level[i]
    if i == 0:
        time_value.append(temp_i)
        temp_num = 0
    elif i > 0:
        temp_i = time_level[i - 1]
    if time_level[i] == temp_i:
        temp_num += 1
        continue
    else:
        temp_i = time_level[i]
        time_value.append(temp_i)
        time_num.append(temp_num)
        temp_num = 0
time_num.append(temp_num)

font = [u'不推荐进食', u'12小时进食', u'10小时进食', u'8小时进食']
colors = ["#50a3ba", "#eac763", "#d94e5d", "#63a950"]
color = []
labels = []
explode = []
for i in range(time_value.__len__()):
    if time_value[i] == 1:
        explode.append(0.1)
    else:
        explode.append(0.01)
    color.append(colors[int(time_value[i]) - 1])
    labels.append(font[int(time_value[i]) - 1])



plt.figure(figsize=(9,6)) #调节图形大小
#labels = [u'大型',u'中型',u'小型',u'微型'] #定义标签
sizes = [46,253,321,66] #每块值
#colors = ['#50a3ba','#eac763','#d94e5d','#63a950'] #每块颜色定义
#explode = (0,0,0.02,0) #将某一块分割出来，值越大分割出的间隙越大
test = [1,2,3,4,5]

this_hour = re.split(r':', current_hour)[0]
this_minute = re.split(r':', current_hour)[1]
global_num = str(this_hour) + ":" + str(this_minute)
def absolute_value(val):
    global global_num
    val = val * 1440 / 100
    a_1 = int(floor(val / 60))
    a_2 = int(round(val % 60))
    start = global_num
    inner_hour = int(re.split(r':', start)[0])
    inner_minute = int(re.split(r':', start)[1])
    inner_hour = inner_hour + a_1
    inner_minute = inner_minute + a_2
    if inner_hour >= 24:
        inner_hour -= 24
    if inner_minute >= 60:
        inner_minute -= 60
        inner_hour += 1
    end = str(inner_hour) + ":" + str(inner_minute)
    global_num = end

    return start + " - " + end
patches,text1,text2 = plt.pie(time_num,
                    labels=labels,
                    explode=explode,
                      colors=color,
                      labeldistance = 1.2,#图例距圆心半径倍距离
                      autopct = absolute_value, #数值保留固定小数位
                      shadow = True, #无阴影设置
                      startangle =90, #逆时针起始角度设置
                      pctdistance = 1,
                              counterclock = False) #数值距圆心半径倍数距离
#patches饼图的返回值，texts1饼图外label的文本，texts2饼图内部文本
# x，y轴刻度设置一致，保证饼图为圆形
plt.axis('equal')
plt.legend()
file_path = "/var/www/html/deeplearning_photo/result_photo/" + str(user_name1) + "/heatmap.png"
plt.savefig(file_path)






cur.close()

conn.close()
