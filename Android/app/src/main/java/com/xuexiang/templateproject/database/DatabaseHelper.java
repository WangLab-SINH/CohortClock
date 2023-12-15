/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句 并 执行,相当于初始化数据库，这里是新建了一张表
        //这个方法继承自SQLiteOpenHelper,会自动调用  也就是 会 当新建了一个DatabaseHelper对象时，就会m=默认新建一张表，表里存着name
//        String sql = "create table user_info_food(" +
//                "ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
//                "date varchar(255))";
        String sql = "CREATE TABLE IF NOT EXISTS photo_path(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name  varchar(255)," +
                "user_time varchar(255)," +
                "photo_type varchar(255)" +
                ")";
        db.execSQL(sql);




        String sqlnew = "CREATE TABLE IF NOT EXISTS photo_path_new(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name  varchar(255)," +
                "user_time varchar(255)," +
                "photo_type varchar(255)," +
                "photo_url varchar(255)," +
                "photo_kind varchar(255)," +
                "photo_cal varchar(255)," +
                "photo_web_url varchar(255)," +
                "workday varchar(255)," +
                "weekend varchar(255)" +
                ")";
        db.execSQL(sqlnew);



        String sql_url = "CREATE TABLE IF NOT EXISTS photo_web_url_table(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "photo_type varchar(255)," +
                "photo_url varchar(255)," +
                "photo_kind varchar(255)," +
                "photo_cal varchar(255)" +
                ")";
        db.execSQL(sql_url);








        String sql1 = "CREATE TABLE IF NOT EXISTS user_weight_sleep(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name varchar(255)," +
                "weight varchar(255)," +
                "start_time varchar(255)," +
                "end_time varchar(255)," +
                "upload_time varchar(255)" +
                ")";
        db.execSQL(sql1);




        String sqlindex = "CREATE TABLE IF NOT EXISTS body_index(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name  varchar(255)," +
                "user_time varchar(255)," +
                "index_value varchar(255)," +
                "index_name varchar(255)" +
                ")";
        db.execSQL(sqlindex);


//        String sql2 = "CREATE TABLE IF NOT EXISTS workday_data(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "time0 varchar(255)," +
//                "time1 varchar(255)," +
//                "time2 varchar(255)," +
//                "time3 varchar(255)," +
//                "time4 varchar(255)," +
//                "time5 varchar(255)," +
//                "time6 varchar(255)," +
//                "time7 varchar(255)," +
//                "time8 varchar(255)," +
//                "time9 varchar(255)," +
//                "time10 varchar(255)," +
//                "time11 varchar(255)," +
//                "time12 varchar(255)," +
//                "time13 varchar(255)," +
//                "time14 varchar(255)," +
//                "time15 varchar(255)," +
//                "time16 varchar(255)," +
//                "time17 varchar(255)," +
//                "time18 varchar(255)," +
//                "time19 varchar(255)," +
//                "time20 varchar(255)," +
//                "time21 varchar(255)," +
//                "time22 varchar(255)," +
//                "time23 varchar(255)," +
//                "time24 varchar(255)," +
//                "time25 varchar(255)," +
//                "time26 varchar(255)," +
//                "time27 varchar(255)," +
//                "time28 varchar(255)," +
//                "time29 varchar(255)," +
//                "time30 varchar(255)," +
//                "time31 varchar(255)," +
//                "time32 varchar(255)," +
//                "time33 varchar(255)," +
//                "time34 varchar(255)," +
//                "time35 varchar(255)," +
//                "time36 varchar(255)," +
//                "time37 varchar(255)," +
//                "time38 varchar(255)," +
//                "time39 varchar(255)," +
//                "time40 varchar(255)," +
//                "time41 varchar(255)," +
//                "time42 varchar(255)," +
//                "time43 varchar(255)," +
//                "time44 varchar(255)," +
//                "time45 varchar(255)," +
//                "time46 varchar(255)," +
//                "time47 varchar(255)" +
//                ")";
//        db.execSQL(sql2);
//
//        String sql3 = "CREATE TABLE IF NOT EXISTS weekend_data(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "time0 varchar(255)," +
//                "time1 varchar(255)," +
//                "time2 varchar(255)," +
//                "time3 varchar(255)," +
//                "time4 varchar(255)," +
//                "time5 varchar(255)," +
//                "time6 varchar(255)," +
//                "time7 varchar(255)," +
//                "time8 varchar(255)," +
//                "time9 varchar(255)," +
//                "time10 varchar(255)," +
//                "time11 varchar(255)," +
//                "time12 varchar(255)," +
//                "time13 varchar(255)," +
//                "time14 varchar(255)," +
//                "time15 varchar(255)," +
//                "time16 varchar(255)," +
//                "time17 varchar(255)," +
//                "time18 varchar(255)," +
//                "time19 varchar(255)," +
//                "time20 varchar(255)," +
//                "time21 varchar(255)," +
//                "time22 varchar(255)," +
//                "time23 varchar(255)," +
//                "time24 varchar(255)," +
//                "time25 varchar(255)," +
//                "time26 varchar(255)," +
//                "time27 varchar(255)," +
//                "time28 varchar(255)," +
//                "time29 varchar(255)," +
//                "time30 varchar(255)," +
//                "time31 varchar(255)," +
//                "time32 varchar(255)," +
//                "time33 varchar(255)," +
//                "time34 varchar(255)," +
//                "time35 varchar(255)," +
//                "time36 varchar(255)," +
//                "time37 varchar(255)," +
//                "time38 varchar(255)," +
//                "time39 varchar(255)," +
//                "time40 varchar(255)," +
//                "time41 varchar(255)," +
//                "time42 varchar(255)," +
//                "time43 varchar(255)," +
//                "time44 varchar(255)," +
//                "time45 varchar(255)," +
//                "time46 varchar(255)," +
//                "time47 varchar(255)" +
//                ")";
//        db.execSQL(sql3);


        String sql4 = "CREATE TABLE IF NOT EXISTS workday_data_new(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data varchar(255)" +

                ")";
        db.execSQL(sql4);


        String sql5 = "CREATE TABLE IF NOT EXISTS weekend_data_new(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data varchar(255)" +

                ")";
        db.execSQL(sql5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cursor cursor = null;
        boolean result = false;
        cursor = db.rawQuery( "select * from sqlite_master where name = ? and sql like ?"
                , new String[]{"photo_path_new" , "%" + "photo_web_url" + "%"} );
        result = null != cursor && cursor.moveToFirst() ;
        if(result == true){

        }else{
            String sql_alter2 = "ALTER TABLE photo_path_new ADD COLUMN photo_web_url varchar(255)";
            db.execSQL(sql_alter2);
        }

//        if(oldVersion != newVersion) {
//            String sql_alter2 = "ALTER TABLE photo_path_new ADD COLUMN photo_kind varchar(255)";
//            db.execSQL(sql_alter2);
//
//            String sql_alter1 = "ALTER TABLE photo_path_new ADD COLUMN photo_cal varchar(255)";
//            db.execSQL(sql_alter1);
//        }

    }
}
