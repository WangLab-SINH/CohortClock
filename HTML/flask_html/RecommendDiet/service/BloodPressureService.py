from utils import sqlConnect
import json
import pandas as pd


# def getPressureInfo(page, limit):
#     connect = sqlConnect.MySQLConnection()
#     limit_min = 0 + (page - 1) * limit
#     limit_max = limit
#     # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
#     sql_cell_type_total = 'SELECT  * FROM pressure_table order by id DESC limit {}, {}'.format(limit_min,limit_max)
#     cell_type_total = connect.query(sql_cell_type_total)
#     sql_get_total_count = 'SELECT COUNT(*) FROM pressure_table'
#     total_count = connect.query(sql_get_total_count)
#     photo_result = []
#
#     for item in cell_type_total:
#         photo_result.append({
#             'user_name': item['user_name'],
#             'phone_id': item['phone_id'],
#             'upload_date': item['upload_date'],
#             'upload_time': item['upload_time'],
#             'Dia_pressure': item['Dia_pressure'],
#             'Dia_AMP': item['Dia_AMP'],
#             'Dia_pvalue': item['Dia_pvalue'],
#             'Dia_period': item['Dia_period'],
#             'Dia_adjphase': item['Dia_adjphase'],
#             'Sys_pressure': item['Sys_pressure'],
#             'Sys_AMP': item['Sys_AMP'],
#             'Sys_pvalue': item['Sys_pvalue'],
#             'Sys_period': item['Sys_period'],
#             'Sys_adjphase': item['Sys_adjphase']
#         })
#
#
#     sql_cell_type_total = 'select distinct index_name from group_index_table where group_id="1" and is_circadian="true"'
#     cell_type_total = connect.query(sql_cell_type_total)
#     index_name_list = []
#     for item in cell_type_total:
#         index_name_list.append({
#             'index_name': item['index_name']
#         })
#
#
#     return photo_result, total_count[0]['COUNT(*)'], index_name_list



def getPressureInfo(page, limit):
    connect = sqlConnect.MySQLConnection()
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    sql_cell_type_total = 'SELECT  * FROM circadian_table order by id DESC limit {}, {}'.format(limit_min,limit_max)
    cell_type_total = connect.query(sql_cell_type_total)
    sql_get_total_count = 'SELECT COUNT(*) FROM circadian_table'
    total_count = connect.query(sql_get_total_count)
    photo_result = []

    for item in cell_type_total:
        photo_result.append({
            'user_name': item['user_name'],
            'phone_id': item['phone_id'],
            'upload_time': item['upload_time'],
            'index_value': item['index_value'],
            'index_name': item['index_name'],
            'AMP': item['AMP'],
            'Pvalue': item['Pvalue'],
            'Period': item['Period'],
            'Phase_time': item['Phase_time']
        })


    sql_cell_type_total = 'select distinct index_name from group_index_table where group_id="1" and is_circadian="true"'
    cell_type_total = connect.query(sql_cell_type_total)
    index_name_list = []
    for item in cell_type_total:
        index_name_list.append({
            'index_name': item['index_name']
        })


    return photo_result, total_count[0]['COUNT(*)'], index_name_list