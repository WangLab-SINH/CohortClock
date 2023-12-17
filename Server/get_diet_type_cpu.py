#!/usr/bin/python
# -*- coding: UTF-8 -*-
import argparse as argparse
import datetime
import re
import sys


import torch
import torch.nn as nn
from torchvision import datasets, transforms
from torch.utils.data import DataLoader
from torch.autograd import Variable
import torch.nn.functional as F
import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
import pandas as pd
import torch.utils.data as data
import torch
import torch.nn as nn
from torch.autograd import Variable
import torch.utils.data as data
import matplotlib.pyplot as plt
from torch.utils.data import TensorDataset
from torchvision import models
import pymysql
import argparse
import math
import joblib



torch.manual_seed(1)
loss_list = []
accuracy_list = []

EPOCH = 100
BATCH_SIZE = 50
LR = 0.0008
DOWNLOAD_MNIST = True
np.random.seed(1)

parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
parser.add_argument('-u', '--user', metavar='user', default='50470b6627e1e211')
conn = pymysql.connect(host="rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com", port=3306, user="chiyuhao", passwd="Mll950223", db="apis", charset="utf8")
cur = conn.cursor()  # 获取游标
#sql =  r'''select * from photo_path where user_name = "'+user_name1+'" '''
user_name1 = sys.argv[1]
sql = 'select * from photo_path where user_name ="'+user_name1+'"'
cur.execute(sql)
results = cur.fetchall()
day_list = []
date_list = []

for i in range(results.__len__()):
    if results[i][5] == 'food':

        date_list.append(results[i][2])
def get_list(date):
    return datetime.datetime.strptime(date,"%Y.%m.%d-%H:%M:%S").timestamp()
new_date_list = sorted(date_list,key=lambda date:get_list(date))

for i in range(new_date_list.__len__()):
    temp_all_day = re.split(r'-', new_date_list[i])[0]
    temp_year = re.split(r'\.', temp_all_day)[0]
    temp_month = re.split(r'\.', temp_all_day)[1]
    temp_day = re.split(r'\.', temp_all_day)[2]
    if int(temp_month) < 10:
        temp_month = "0" + temp_month
    if int(temp_day) < 10:
        temp_day = "0" + temp_day
    day_list.append(temp_year + '.' + temp_month + '.' + temp_day)
def dateRange(beginDate, num):
    """

    :param beginDate:
    :return:
    """
    yes_time = beginDate + datetime.timedelta(days=+1)
    aWeekDelta = datetime.timedelta(days=num)
    aWeekAgo = yes_time - aWeekDelta
    dates = []
    i = 0
    begin = aWeekAgo.strftime("%Y.%m.%d")
    dt = datetime.datetime.strptime(begin, "%Y.%m.%d")
    date = begin[:]
    while i < num:
        dates.append(date)
        dt = dt + datetime.timedelta(1)
        date = dt.strftime("%Y.%m.%d")
        i += 1
    return dates


current_day = re.split(r'-',new_date_list[-1])[0]
start_day = dateRange(datetime.datetime.strptime(current_day, "%Y.%m.%d"), 2)[0]
time_list = dateRange(datetime.datetime.strptime(start_day, "%Y.%m.%d"), 28)
true_day_list = []
true_day_flag_list = []

for i in range(day_list.__len__()):
    if day_list[i] in time_list:
        true_day_list.append(new_date_list[i])
        true_day_flag_list.append(day_list[i])
output_string = ""
if true_day_list.__len__() < 28:
    output_string += "0;0;0;0"
else:
    input_list = []
    temp_input = []
    per_day_list = []
    for i in range(28):
        temp_day_input = []
        if time_list[i] in true_day_flag_list:
            temp_day_list = []
            for j in range(true_day_flag_list.__len__()):
                if true_day_flag_list[j] == time_list[i]:
                    temp_day_list.append(true_day_list[j])
            temp_hour_list = []
            for j in range(temp_day_list.__len__()):
               temp1 = re.split(r":", re.split(r"-", temp_day_list[j])[1])
               temp_hour_list.append(int(temp1[0]) * 4 + math.floor(float(temp1[1])/15))
            for j in range(96):
                if j in temp_hour_list:
                    temp_input.append(1)
                    temp_day_input.append(1)
                else:
                    temp_input.append(0)
                    temp_day_input.append(0)
        else:
            for j in range(96):
                temp_input.append(0)
                temp_day_input.append(0)
        per_day_list.append(temp_day_input)
    input_list.append(temp_input)

    df = pd.DataFrame(input_list)

    new_clf = joblib.load('/var/www/html/deeplearning_photo/NaiveBayes.model')
    output_string += new_clf.predict(df)[0] + ";"


    total_day_in_week = [0,0,0,0,0,0,0]
    for i in range(4):
        for j in range(7):
            temp = sum(per_day_list[i * 7 + j])
            total_day_in_week[j] += temp
    new_total_day_in_week = sorted(enumerate(total_day_in_week), key=lambda x: x[1])
    current_flag = 0
    next_flag = 0
    if new_total_day_in_week[6][0] <= 1:
        current_flag = 1
    if new_total_day_in_week[0][0] <= 1:
        next_flag = 1

    output_string += str(current_flag) + ";" + str(next_flag) + ";"


    per_day_dataframe = pd.DataFrame(per_day_list)
    col_data = per_day_dataframe.apply(sum)
    temp_max = 0
    temp_max_index = 0
    for i in range(44):
        if col_data[i] > temp_max:
            temp_max_index = i
            temp_max =  col_data[i]
    first_hour = temp_max_index
    if first_hour % 4 < 2:
        first_hour = math.floor(first_hour / 4)
    else:
        first_hour = math.floor(first_hour / 4) + 0.5

    output_string += str(first_hour)

print(output_string)


