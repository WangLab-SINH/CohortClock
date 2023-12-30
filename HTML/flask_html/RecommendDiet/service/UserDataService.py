from utils import sqlConnect
import json
import pandas as pd


def getUserInfo(page, limit):
    connect = sqlConnect.MySQLConnection()
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    sql_cell_type_total = 'SELECT  * FROM user_table_time order by id limit {}, {}'.format(limit_min,limit_max)
    cell_type_total = connect.query(sql_cell_type_total)
    sql_get_total_count = 'SELECT COUNT(*) FROM user_table_time'
    total_count = connect.query(sql_get_total_count)
    photo_result = []

    for item in cell_type_total:
        photo_result.append({
            'user_name': item['user_name'],
            'phone_id': item['phone_id'],
            'location': item['location'],
            'group_type': item['group_type'],
            'disease': item['disease'],
            'diet_type': item['diet_type'],
            'food_num': item['food_num'],
            'weight': item['weight'],
            'height': item['height'],
            'BMI': item['BMI'],
            'start_time': item['start_time'],
            'end_time': item['end_time'],
            'edit_flag': item['edit_flag'],
            'body_index_num': item['body_index_num']
        })

    return photo_result, total_count[0]['COUNT(*)']




def getBodyIndexInfo(page, limit):
    connect = sqlConnect.MySQLConnection()
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    sql_cell_type_total = 'SELECT  * FROM group_index_table order by id'
    cell_type_total = connect.query(sql_cell_type_total)
    sql_get_total_count = 'SELECT COUNT(*) FROM group_index_table'
    total_count = connect.query(sql_get_total_count)
    photo_result = []

    for item in cell_type_total:
        photo_result.append({
            'index_console': item['index_console'],
            'index_query': item['index_query'],
            'index_unit': item['index_unit'],
            'min_num': item['min_num'],
            'max_num': item['max_num'],
            'scale': item['scale'],
            'average': item['average'],
            'index_name': item['index_name'],
            'is_circadian': item['is_circadian']
        })

    return photo_result, total_count[0]['COUNT(*)']