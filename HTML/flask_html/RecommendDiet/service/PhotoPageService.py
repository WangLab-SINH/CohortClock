from utils import sqlConnect
import json
import pandas as pd


def getPhotoInfo(page, limit):
    connect = sqlConnect.MySQLConnection()
    limit_min = 0 + (page - 1) * limit
    limit_max = limit
    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    sql_cell_type_total = 'SELECT  * FROM photo_path_new order by id DESC limit {}, {}'.format(limit_min,limit_max)
    cell_type_total = connect.query(sql_cell_type_total)
    sql_get_total_count = 'SELECT COUNT(*) FROM photo_path_new'
    total_count = connect.query(sql_get_total_count)
    photo_result = []

    for item in cell_type_total:
        photo_result.append({
            'user_name': item['user_name'],
            'user_group': item['user_group'],
            'phone_id': item['phone_id'],
            'upload_time': item['upload_time'],
            'user_time': item['user_time'],
            'photo_name': item['photo_name'],
            'photo_path': item['photo_path'],
            'photo_type': item['photo_type'],
            'photo_cal': item['photo_cal'],
            'photo_kind': item['photo_kind']
        })

    return photo_result, total_count[0]['COUNT(*)']


def editPhotoDatabase(data_time, phone_id,photo_cal,
                      photo_kind,photo_name,photo_path,photo_type ,
                      upload_time,user_group,user_name):
    connect = sqlConnect.MySQLConnection()

    # cur = connect.cursor()
    sql_cell_type_total = 'UPDATE photo_path_new set photo_type="{}",photo_kind="{}",photo_cal="{}" where user_time="{}" and user_name="{}"'.format(photo_type,photo_kind,photo_cal,data_time,user_name)
    # cur.execute('UPDATE photo_path set photo_type=%s,photo_kind=%s,photo_cal=%s where data_time=%s and user_name=%s', (photo_type,photo_kind,photo_cal,data_time,user_name))
    # connect.commit()

    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    cell_type_total = connect.edit(sql_cell_type_total)


    return ""


def editEafflagDatabase(user_name, phone_id,start_time,end_time,edit_flag):
    connect = sqlConnect.MySQLConnection()

    # cur = connect.cursor()
    sql_cell_type_total = 'UPDATE user_table_time set start_time="{}",end_time="{}",edit_flag="{}" where user_name="{}"'.format(start_time,end_time,edit_flag,user_name)
    # cur.execute('UPDATE photo_path set photo_type=%s,photo_kind=%s,photo_cal=%s where data_time=%s and user_name=%s', (photo_type,photo_kind,photo_cal,data_time,user_name))
    # connect.commit()

    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    cell_type_total = connect.edit(sql_cell_type_total)


    return ""



def editEafflagDatabase(user_name, phone_id,start_time,end_time,edit_flag):
    connect = sqlConnect.MySQLConnection()

    # cur = connect.cursor()
    sql_cell_type_total = 'UPDATE user_table_time set start_time="{}",end_time="{}",edit_flag="{}" where user_name="{}"'.format(start_time,end_time,edit_flag,user_name)
    # cur.execute('UPDATE photo_path set photo_type=%s,photo_kind=%s,photo_cal=%s where data_time=%s and user_name=%s', (photo_type,photo_kind,photo_cal,data_time,user_name))
    # connect.commit()

    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    cell_type_total = connect.edit(sql_cell_type_total)


    return ""


def editBodyIndexTypeDatabase(index_console, index_query,index_unit,min_num,max_num,scale,average,index_name,is_circadian):
    connect = sqlConnect.MySQLConnection()

    # cur = connect.cursor()
    sql_cell_type_total = 'insert into group_index_table (`index_console`, `index_query`,`index_unit`,`min_num`,`max_num`,`scale`,`average`,`index_name`,`is_circadian`) values ("{}","{}","{}","{}","{}","{}","{}","{}","{}")'.format(index_console, index_query,index_unit,min_num,max_num,scale,average,index_name,is_circadian)
    # cur.execute('UPDATE photo_path set photo_type=%s,photo_kind=%s,photo_cal=%s where data_time=%s and user_name=%s', (photo_type,photo_kind,photo_cal,data_time,user_name))
    # connect.commit()

    # sql_cell_type_total = 'SELECT  * FROM photo_path order by id DESC limit {} offset {} '.format(limit_min, limit_max)
    cell_type_total = connect.edit(sql_cell_type_total)


    return ""
