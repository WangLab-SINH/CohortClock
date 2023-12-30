from utils import sqlConnect
import json
import pandas as pd

def GetUserDateList():
    connect = sqlConnect.MySQLConnection()


    sql_cell_type_total = 'SELECT  * FROM day_time_table'
    cell_type_total = connect.query(sql_cell_type_total)


    x_axis = []
    y_axis = []
    for item in cell_type_total:
        x_axis.append(item['day_time'])
        y_axis.append(item['day_number'])

    return x_axis, y_axis




def GetUserHourList():
    connect = sqlConnect.MySQLConnection()


    sql_cell_type_total = 'SELECT  * FROM hour_time_table'
    cell_type_total = connect.query(sql_cell_type_total)


    x_axis = []
    y_axis = []
    for item in cell_type_total:
        x_axis.append(item['hour_time'])
        y_axis.append(item['hour_number'])



    return x_axis, y_axis



def GetDietTypeList():
    connect = sqlConnect.MySQLConnection()


    sql_cell_type_total = 'SELECT  * FROM diet_type_table'
    cell_type_total = connect.query(sql_cell_type_total)


    data = []
    for item in cell_type_total:
        data.append({'name':item['diet_type'], 'value':item['diet_number']})




    return data