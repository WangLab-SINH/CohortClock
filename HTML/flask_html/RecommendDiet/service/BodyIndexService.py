from utils import sqlConnect
import json
import pandas as pd


def getBodyindexInfo(select_body_index, page, limit):
    connect = sqlConnect.MySQLConnection()
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    if select_body_index == '':
        sql_cell_type_total = 'SELECT  * FROM body_index order by id DESC limit {}, {}'.format(limit_min,limit_max)
        cell_type_total = connect.query(sql_cell_type_total)
        sql_get_total_count = 'SELECT COUNT(*) FROM body_index'
        total_count = connect.query(sql_get_total_count)

    else:
        sql_cell_type_total = 'SELECT  * FROM body_index where index_name = "{}" order by id DESC limit {}, {}'.format(select_body_index,limit_min,limit_max)
        cell_type_total = connect.query(sql_cell_type_total)
        sql_get_total_count = 'SELECT COUNT(*) FROM body_index'
        total_count = connect.query(sql_get_total_count)


    photo_result = []

    for item in cell_type_total:
        photo_result.append({
            'user_name': item['user_name'],
            'phone_id': item['phone_id'],
            'upload_time': item['upload_time'],
            'data_time': item['data_time'],
            'index_value': item['index_value'],
            'index_name': item['index_name']
        })

    sql_cell_type_total = 'select distinct index_name from group_index_table where group_id="1"'
    cell_type_total = connect.query(sql_cell_type_total)
    index_name_list = []
    for item in cell_type_total:
        index_name_list.append({
            'index_name': item['index_name']
        })

    return photo_result, total_count[0]['COUNT(*)'], index_name_list