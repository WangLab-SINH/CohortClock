from utils import sqlConnect
import json
import pandas as pd


def getUserInfo(username, password):
    connect = sqlConnect.MySQLConnection()
    params = [username, password]
    sql = 'SELECT  * FROM t_user_list WHERE username=%s AND password=%s'
    user_info = connect.query(sql, params)

    return user_info
