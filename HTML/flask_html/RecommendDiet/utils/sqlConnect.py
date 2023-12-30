import os

import pymysql
import configparser
import logging
from dbutils.pooled_db import PooledDB

class MySQLConnection(object):
    def __init__(self, dbName='hamburger'):
        # 读取数据库配置信息
        config = configparser.ConfigParser()
        path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'db.conf')
        config.read(path, encoding='utf8')
        sections = config.sections()
        # 数据库工厂
        dbFactory = {}
        for dbName in sections:
            # 读取相关属性
            maxconnections = config.get(dbName, "maxconnections")
            mincached = config.get(dbName, "mincached")
            maxcached = config.get(dbName, "maxcached")
            host = config.get(dbName, "host")
            port = config.get(dbName, "port")
            user = config.get(dbName, "user")
            password = config.get(dbName, "password")
            database = config.get(dbName, "database")
            databasePooled = PooledDB(creator=pymysql,
                                      maxconnections=int(maxconnections),
                                      mincached=int(mincached),
                                      maxcached=int(maxcached),
                                      blocking=True,
                                      cursorclass=pymysql.cursors.DictCursor,
                                      host=host,
                                      port=int(port),
                                      user=user,
                                      password=password,
                                      database=database)
            dbFactory[dbName] = databasePooled

        self.connect = dbFactory[dbName].connection()
        self.cursor = self.connect.cursor()
        logging.debug("获取数据库连接对象成功,连接池对象:{}".format(str(self.connect)))

    def query(self, sql, param=None):
        """
        查询数据库
        :param sql: 查询SQL语句
        :param param: 参数
        :return: 返回集合
        """
        self.cursor.execute(sql, param)
        result = self.cursor.fetchall()
        return result

    def edit(self, sql, param=None):
        """
        查询数据库
        :param sql: 查询SQL语句
        :param param: 参数
        :return: 返回集合
        """
        self.cursor.execute(sql, param)
        self.connect.commit()
        result = self.cursor.fetchall()
        return result

    def queryOne(self, sql, param=None):
        """
        查询数据返回第一条
        :param sql: 查询SQL语句
        :param param: 参数
        :return: 返回第一条数据的字典
        """
        result = self.query(sql, param)
        if result:
            return result[0]
        else:
            return None

    def close(self):
        """
        关闭数据库连接
        :return:
        """
        if self.cursor:
            self.cursor.close()
        if self.connect:
            self.connect.close()
        logging.debug("释放数据库连接")
        return None