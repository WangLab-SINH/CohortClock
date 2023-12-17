//
//  DataBase.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/10/14.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
import SQLite

struct Database {

    var db: Connection!

    init() {
        connectDatabase()
    }

    // 与数据库建立连接
    mutating func connectDatabase(filePath: String = "/Documents") -> Void {

        let sqlFilePath = NSHomeDirectory() + filePath + "/db.sqlite3"

        do { // 与数据库建立连接
            db = try Connection(sqlFilePath)
            print("与数据库建立连接 成功")
        } catch {
            print("与数据库建立连接 失败：\(error)")
        }

    }

    // ===================================== 灯光 =====================================
    let TABLE_LAMP = Table("photo_path_new") // 表名称
    
    let TABLE_LAMP_WEIGHT = Table("user_weight_sleep_new")
    
    
    
    let TABLE_ID = Expression<Int64>("id")
    let TABLE_USER_NAME = Expression<String>("user_name") // 列表项及项类型
    let TABLE_USER_TIME = Expression<String>("user_time")
    let TABLE_PHOTO_TYPE = Expression<String>("photo_type")
    let TABLE_PHOTO_URL = Expression<String>("photo_url")
    let TABLE_PHOTO_KIND = Expression<String>("photo_kind")
    let TABLE_PHOTO_CAL = Expression<String>("photo_cal")
    let TABLE_PHOTO_WEB_URL = Expression<String>("photo_web_url")
    let TABLE_WORKDAY = Expression<String>("workday")
    let TABLE_WEEKEND = Expression<String>("weekend")
    
    
    
    let TABLE_WEIGHT = Expression<String>("weight")
    let TABLE_START_TIME = Expression<String>("start_time")
    let TABLE_END_TIME = Expression<String>("end_time")
    let TABLE_UPLOAD_TIME = Expression<String>("upload_time")
    
    
    
    
    

    // 建表
    func tableLampCreate() -> Void {
        do { // 创建表TABLE_LAMP
            
            try db.run(TABLE_LAMP.create { table in
                table.column(TABLE_ID, primaryKey: .autoincrement) // 主键自加且不为空
                table.column(TABLE_USER_NAME)
                table.column(TABLE_USER_TIME)
                table.column(TABLE_PHOTO_TYPE)
                table.column(TABLE_PHOTO_URL)
                table.column(TABLE_PHOTO_KIND)
                table.column(TABLE_PHOTO_CAL)
                table.column(TABLE_PHOTO_WEB_URL)
                table.column(TABLE_WORKDAY)
                table.column(TABLE_WEEKEND)
                
            })
//            print("创建表 TABLE_LAMP 成功")
        } catch {
//            print("创建表 TABLE_LAMP 失败：\(error)")
        }
    }
    
    
    func tableLampCreateWeight() -> Void {
        do { // 创建表TABLE_LAMP
            try db.run(TABLE_LAMP_WEIGHT.create { table in
                table.column(TABLE_ID, primaryKey: .autoincrement) // 主键自加且不为空
                table.column(TABLE_USER_NAME)
                table.column(TABLE_WEIGHT)
                table.column(TABLE_START_TIME)
                table.column(TABLE_END_TIME)
                table.column(TABLE_UPLOAD_TIME)

                
            })
//            print("创建表 TABLE_LAMP 成功")
        } catch {
//            print("创建表 TABLE_LAMP 失败：\(error)")
        }
    }
    
    
    
    
    func tableLampInsertItemWeight(user_name: String, weight: String, start_time: String, end_time: String, upload_time: String) -> Void {
        let insert = TABLE_LAMP_WEIGHT.insert(TABLE_USER_NAME <- user_name, TABLE_WEIGHT <- weight, TABLE_START_TIME <- start_time, TABLE_END_TIME <- end_time, TABLE_UPLOAD_TIME <- upload_time)
        do {
            let rowid = try db.run(insert)
            print("插入数据成功 id: \(rowid)")
        } catch {
            print("插入数据失败: \(error)")
        }
    }
    
    
    
    

    // 插入
    func tableLampInsertItem(user_name: String, user_time: String, photo_type: String, photo_url: String, photo_kind: String, photo_cal:String, photo_web_url:String, workday:String, weekend:String) -> Void {
        let insert = TABLE_LAMP.insert(TABLE_USER_NAME <- user_name, TABLE_USER_TIME <- user_time, TABLE_PHOTO_TYPE <- photo_type, TABLE_PHOTO_URL <- photo_url, TABLE_PHOTO_KIND <- photo_kind,TABLE_PHOTO_CAL <- photo_cal,TABLE_PHOTO_WEB_URL <- photo_web_url,TABLE_WORKDAY <- workday,TABLE_WEEKEND <- weekend)
        do {
            let rowid = try db.run(insert)
            print("插入数据成功 id: \(rowid)")
        } catch {
            print("插入数据失败: \(error)")
        }
    }
    
//    func tableLampInsertItem_short(user_name: String, user_time: String, photo_type: String, photo_url: String) -> Void {
//        let insert = TABLE_LAMP.insert(TABLE_USER_NAME <- user_name, TABLE_USER_TIME <- user_time, TABLE_PHOTO_TYPE <- photo_type, TABLE_PHOTO_URL <- photo_url)
//        do {
//            let rowid = try db.run(insert)
//            print("插入数据成功 id: \(rowid)")
//        } catch {
//            print("插入数据失败: \(error)")
//        }
//    }
    

    // 遍历
    func queryTableLamp() -> Void {
        for item in (try! db.prepare(TABLE_LAMP)) {
//            print("遍历 ———— id: \(item[TABLE_ID]), user_name: \(item[TABLE_USER_NAME]), user_time: \(item[TABLE_USER_TIME]), photo_type: \(item[TABLE_PHOTO_TYPE]), photo_url: \(item[TABLE_PHOTO_URL]), photo_kind: \(item[TABLE_PHOTO_KIND]), photo_cal: \(item[TABLE_PHOTO_CAL]), photo_web_url: \(item[TABLE_PHOTO_WEB_URL]), workday: \(item[TABLE_WORKDAY]), weekend: \(item[TABLE_WEEKEND])")
        }
    }
    
    
    
    func queryTableLampWeight() -> Void {
        for item in (try! db.prepare(TABLE_LAMP_WEIGHT)) {
//            print("遍历 ———— id: \(item[TABLE_ID]), user_name: \(item[TABLE_USER_NAME]), user_time: \(item[TABLE_USER_TIME]), photo_type: \(item[TABLE_PHOTO_TYPE]), photo_url: \(item[TABLE_PHOTO_URL]), photo_kind: \(item[TABLE_PHOTO_KIND]), photo_cal: \(item[TABLE_PHOTO_CAL]), photo_web_url: \(item[TABLE_PHOTO_WEB_URL]), workday: \(item[TABLE_WORKDAY]), weekend: \(item[TABLE_WEEKEND])")
        }
    }
    
    func queryTableWeight() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP_WEIGHT)) {
            resultString.append( "\(item[TABLE_WEIGHT])")
           
        }
        return resultString
    }
    
    
    func queryTableWeightTime() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP_WEIGHT)) {
            resultString.append( "\(item[TABLE_UPLOAD_TIME])")
           
        }
        return resultString
    }
    
    
    func queryTableStartTime() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP_WEIGHT)) {
            resultString.append( "\(item[TABLE_START_TIME])")
           
        }
        return resultString
    }
    
    
    func queryTableEndTime() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP_WEIGHT)) {
            resultString.append( "\(item[TABLE_END_TIME])")
           
        }
        return resultString
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //  遍历上传时间以便同步服务器数据
    func queryTableTime() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP)) {
            resultString.append( "\(item[TABLE_USER_TIME])")
           
        }
        return resultString
    }
    
    
    func queryTableInHeatmap() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP)) {
            if("\(item[TABLE_PHOTO_TYPE])" == "food"){
                resultString.append( "\(item[TABLE_USER_TIME])")
            }
            
           
        }
        return resultString
    }
    
    
    func queryTableInDistributionWork() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP)) {
            if("\(item[TABLE_PHOTO_TYPE])" == "food"){
                resultString.append( "\(item[TABLE_WORKDAY])")
            }
            
           
        }
        return resultString
    }
    
    
    func queryTableInDistributionWeekend() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP)) {
            if("\(item[TABLE_PHOTO_TYPE])" == "food"){
                resultString.append( "\(item[TABLE_WEEKEND])")
            }
            
           
        }
        return resultString
    }
    
    
    
    
    func queryTablePhotoName() -> [String] {
        var resultString:[String] = []
        for item in (try! db.prepare(TABLE_LAMP)) {
            
            resultString.append( "\(item[TABLE_PHOTO_URL])")
            
            
           
        }
        return resultString
    }
    
    
    
    

    // 读取
//    func readTableLampItem(address: Int64) -> Void {
//
//        for item in try! db.prepare(TABLE_LAMP.filter(TABLE_LAMP_ADDRESS == address)) {
//            print("\n读取（灯光）id: \(item[TABLE_LAMP_ID]), address: \(item[TABLE_LAMP_ADDRESS]), name: \(item[TABLE_LAMP_NAME]), colorValue: \(item[TABLE_LAMP_COLOR_VALUE]), lampType: \(item[TABLE_LAMP_LAMP_TYPE])")
//        }
//
//    }

    // 更新
    func tableLampUpdateItem(user_name: String, user_time: String, photo_type: String,  photo_kind: String, photo_cal:String, photo_web_url:String, workday:String, weekend:String) -> Void {
        let item = TABLE_LAMP.filter(TABLE_USER_NAME == user_name && TABLE_USER_TIME == user_time)
        do {
            if try db.run(item.update(TABLE_PHOTO_TYPE <- photo_type,  TABLE_PHOTO_KIND <- photo_kind, TABLE_PHOTO_CAL <- photo_cal, TABLE_PHOTO_WEB_URL <- photo_web_url, TABLE_WORKDAY <- workday, TABLE_WEEKEND <- weekend)) > 0 {
//                print("更新\(user_time) 更新成功")
            } else {
                print("没有发现更新条目 \(user_time)")
            }
        } catch {
            print("灯光\(user_time) 更新失败：\(error)")
        }
    }

   //  删除
    func tableLampDeleteItem(user_name: String, user_time: String) -> Void {
        let item = TABLE_LAMP.filter(TABLE_USER_NAME == user_name && TABLE_USER_TIME == user_time)
        do {
            if try db.run(item.delete()) > 0 {
                print("灯光\(user_time) 删除成功")
            } else {
                print("没有发现 灯光条目 \(user_time)")
            }
        } catch {
            print("灯光\(user_time) 删除失败：\(error)")
        }
    }

}
