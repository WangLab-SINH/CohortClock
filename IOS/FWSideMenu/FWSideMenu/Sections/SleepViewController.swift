//
//  SleepViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/21.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import WebKit
import SwiftDate
class SleepViewController: UIViewController, WKNavigationDelegate, WKScriptMessageHandler {
    

    
    lazy var webView: WKWebView = {
        // 创建WKPreferences
        let preferences = WKPreferences()
        // 开启js
        preferences.javaScriptEnabled = true
        // 创建WKWebViewConfiguration
        let configuration = WKWebViewConfiguration()
        // 设置WKWebViewConfiguration的WKPreferences
        configuration.preferences = preferences
        // 创建WKUserContentController
        let userContentController = WKUserContentController()
        // 配置WKWebViewConfiguration的WKUserContentController
        configuration.userContentController = userContentController
        // 给WKWebView与Swift交互起一个名字：callbackHandler，WKWebView给Swift发消息的时候会用到
        // 此句要求实现WKScriptMessageHandler协议
        configuration.userContentController.add(self, name: "callbackHandler")
        
        // 创建WKWebView
        //var webView = WKWebView(frame: self.view.frame, configuration: configuration)
        var webView = WKWebView(frame: CGRectFlat(0,0,view.bounds.width, view.bounds.height - 100), configuration: configuration)
        
        // 让webview翻动有回弹效果
        webView.scrollView.bounces = true
        // 只允许webview上下滚动
        webView.scrollView.alwaysBounceVertical = true
        
        // 此句要求实现WKNavigationDelegate协议
        webView.navigationDelegate = self
        
        return webView
    }()
    
    // 加载html
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "sleep_new", ofType: "html")!, encoding: String.Encoding.utf8)
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
        view.addSubview(webView)
        

        
        

        
        let bundleStr = Bundle.main.url(forResource: "sleep_new", withExtension: "html")

       

        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        
       
    //    NotificationCenter.default.addObserver(self, selector: "refreshPage", name: NSNotification.Name(rawValue: "homeRefresh"), object: nil)
        
        
        
        
//        webView.loadHTMLString(html, baseURL: nil)
    }
    
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let bundleStr = Bundle.main.url(forResource: "sleep_new", withExtension: "html")
        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
    }
    
    
    

    
    
   
    func webView(_ webView: WKWebView,  didFinish navigation: WKNavigation!) {
        
        

        
        let temp_string: String = getWeightFunction()
        //print(temp_string)
    
        webView.evaluateJavaScript(temp_string) { (result, err) in
            // result是JS返回的值
            print(result, err)
        }
        
        let temp_string1: String = getWeightNumFunction()
        webView.evaluateJavaScript(temp_string1) { (result, err) in
            // result是JS返回的值
            print(result, err)
        }
        
        let temp_string2: String = getWeightFunction1()
        webView.evaluateJavaScript(temp_string2) { (result, err) in
            // result是JS返回的值
            print(result, err)
        }
        
        
        let temp_string3: String = getWeightNumFunction1()
        webView.evaluateJavaScript(temp_string3) { (result, err) in
            // result是JS返回的值
            print(result, err)
        }
        
        

//        webView.evaluateJavaScript(temp_string1) { (result, err) in
//            // result是JS返回的值
//            print(result, err)
//        }
        //刷新页面
        //self.navigationController?.popToRootViewController(animated: false)
            
        
        
        
        
//
        
        

        
        
        }
    

    
    
    // Swift方法，可以在JS中调用
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        print(message.body)
    }
    
    
    
    func getWeightFunction() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let weight_string_temp: [String] = database.queryTableStartTime()
        var weight_string: [String] = []
        var weight_time_string: [String] = []
        
        
        if(weight_string_temp.count > 0){
            for i in 0...(weight_string_temp.count - 1){
                if(weight_string_temp[i] != ""){
                    let temp21: [Substring] = weight_string_temp[i].split(separator: " ")
                    let temp22: [Substring] = String(temp21[1]).split(separator: ":")
                    weight_string.append(String(temp22[0]) + "." + temp22[1])
                    weight_time_string.append(String(temp21[0]))
                }
                
            }
        }

        
        
        var weight_time_dic:[String:String] = [:]
        
        if(weight_time_string.count > 0){
            for i in 0...(weight_time_string.count - 1){
                if(weight_time_string[i] == ""){
                    continue
                }
                else{
                    var temp_time_string: [Substring] = weight_time_string[i].split(separator: " ")
                    if(weight_string[i] != ""){
                        weight_time_dic.updateValue(weight_string[i], forKey: String(temp_time_string[0]))
                    }
                    
                }
                
            }
        }

        
        var weight_time_list: [DateInRegion] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
        }
        
        weight_time_list = DateInRegion.sortedByOldest(list: weight_time_list)
        
        
        var x_axis = "["
        var y_axis = "["
        
        if(weight_time_dic.count > 0){
            for i in 0...(weight_time_dic.count - 1){
                x_axis += "'" + weight_time_list[i].toFormat("yyyy-MM-dd") + "',"
                y_axis += (weight_time_dic[weight_time_list[i].toFormat("yyyy-MM-dd")] ?? "0") + ","
            }
        }

        x_axis += "]"
        y_axis += "]"
        
        var output_string = "setView1(" + x_axis + "," + y_axis + ")"
        
        return output_string

        
        
    }
    
    
    func getWeightNumFunction() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let weight_string_temp: [String] = database.queryTableStartTime()
        var weight_string: [String] = []
        var weight_time_string: [String] = []
        
        if(weight_string_temp.count > 0){
            for i in 0...(weight_string_temp.count - 1){
                if(weight_string_temp[i] != ""){
                    let temp21: [Substring] = weight_string_temp[i].split(separator: " ")
                    let temp22: [Substring] = String(temp21[1]).split(separator: ":")
                    weight_string.append(String(temp22[0]) + "." + temp22[1])
                    weight_time_string.append(String(temp21[0]))
                }
                
            }
        }

        
        var weight_time_dic:[String:String] = [:]
        
        if(weight_time_string.count > 0){
            for i in 0...(weight_time_string.count - 1){
                if(weight_time_string[i] == ""){
                    continue
                }
                else{
                    var temp_time_string: [Substring] = weight_time_string[i].split(separator: " ")
                    if(weight_string[i] != ""){
                        weight_time_dic.updateValue(weight_string[i], forKey: String(temp_time_string[0]))
                    }
                    
                }
                
            }
        }

        
        var weight_time_list: [DateInRegion] = []
        var weight_value_list: [Int] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
            var temp_value = Double(value) ?? 0
            if(temp_value.truncatingRemainder(dividingBy: 1) > 0.75){
                temp_value = (floor(temp_value) + 1) * 2
            }
            else if(temp_value.truncatingRemainder(dividingBy: 1) > 0.5){
                temp_value = (floor(temp_value) + 0.5) * 2
            }
            else if(temp_value.truncatingRemainder(dividingBy: 1) > 0.25){
                temp_value = (floor(temp_value) + 0.5) * 2
            }
            else{
                temp_value = floor(temp_value) * 2
            }
            weight_value_list.append(Int(temp_value))
        }
        
        
       
        
        
        var x_axis = "['0:00', '0:30','1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30'," +
            "'12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30']"
        var y_axis = "["
        for i in 0...47{
            var temp_y = 0
            
            if(weight_value_list.count > 0){
                for j in 0...(weight_value_list.count - 1){
                    if(weight_value_list[j] == i){
                        temp_y += 1
                    }
                }
            }

            y_axis += String(temp_y) + ","
        }
        
        y_axis += "]"
        
        var output_string = "setView3(" + x_axis + "," + y_axis + ")"
        
        return output_string

        
        
    }
    
    
    
    func getWeightFunction1() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let weight_string_temp: [String] = database.queryTableEndTime()
        var weight_string: [String] = []
        var weight_time_string: [String] = []
        
        if(weight_string_temp.count > 0){
            for i in 0...(weight_string_temp.count - 1){
                if(weight_string_temp[i] != ""){
                    let temp21: [Substring] = weight_string_temp[i].split(separator: " ")
                    let temp22: [Substring] = String(temp21[1]).split(separator: ":")
                    weight_string.append(String(temp22[0]) + "." + temp22[1])
                    weight_time_string.append(String(temp21[0]))
                }
                
            }
        }

        
        
        var weight_time_dic:[String:String] = [:]
        
        
        if(weight_time_string.count > 0){
            for i in 0...(weight_time_string.count - 1){
                if(weight_time_string[i] == ""){
                    continue
                }
                else{
                    var temp_time_string: [Substring] = weight_time_string[i].split(separator: " ")
                    if(weight_string[i] != ""){
                        weight_time_dic.updateValue(weight_string[i], forKey: String(temp_time_string[0]))
                    }
                    
                }
                
            }
        }

        
        var weight_time_list: [DateInRegion] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
        }
        
        weight_time_list = DateInRegion.sortedByOldest(list: weight_time_list)
        
        
        var x_axis = "["
        var y_axis = "["
        
        if(weight_time_dic.count > 0){
            for i in 0...(weight_time_dic.count - 1){
                x_axis += "'" + weight_time_list[i].toFormat("yyyy-MM-dd") + "',"
                y_axis += (weight_time_dic[weight_time_list[i].toFormat("yyyy-MM-dd")] ?? "0") + ","
            }
        }

        x_axis += "]"
        y_axis += "]"
        
        var output_string = "setView2(" + x_axis + "," + y_axis + ")"
        
        return output_string

        
        
    }
    
    
    func getWeightNumFunction1() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let weight_string_temp: [String] = database.queryTableEndTime()
        var weight_string: [String] = []
        var weight_time_string: [String] = []
        
        if(weight_string_temp.count > 0){
            for i in 0...(weight_string_temp.count - 1){
                if(weight_string_temp[i] != ""){
                    let temp21: [Substring] = weight_string_temp[i].split(separator: " ")
                    let temp22: [Substring] = String(temp21[1]).split(separator: ":")
                    weight_string.append(String(temp22[0]) + "." + temp22[1])
                    weight_time_string.append(String(temp21[0]))
                }
                
            }
        }

        
        var weight_time_dic:[String:String] = [:]
        
        
        if(weight_time_string.count > 0){
            for i in 0...(weight_time_string.count - 1){
                if(weight_time_string[i] == ""){
                    continue
                }
                else{
                    var temp_time_string: [Substring] = weight_time_string[i].split(separator: " ")
                    if(weight_string[i] != ""){
                        weight_time_dic.updateValue(weight_string[i], forKey: String(temp_time_string[0]))
                    }
                    
                }
                
            }
        }

        
        var weight_time_list: [DateInRegion] = []
        var weight_value_list: [Int] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
            var temp_value = Double(value) ?? 0
            if(temp_value.truncatingRemainder(dividingBy: 1) > 0.75){
                temp_value = (floor(temp_value) + 1) * 2
            }
            else if(temp_value.truncatingRemainder(dividingBy: 1) > 0.5){
                temp_value = (floor(temp_value) + 0.5) * 2
            }
            else if(temp_value.truncatingRemainder(dividingBy: 1) > 0.25){
                temp_value = (floor(temp_value) + 0.5) * 2
            }
            else{
                temp_value = floor(temp_value) * 2
            }
            weight_value_list.append(Int(temp_value))
        }
        
        
       
        
        
        var x_axis = "['0:00', '0:30','1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30'," +
            "'12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30']"
        var y_axis = "["
        for i in 0...47{
            var temp_y = 0
            
            if(weight_value_list.count > 0){
                for j in 0...(weight_value_list.count - 1){
                    if(weight_value_list[j] == i){
                        temp_y += 1
                    }
                }
            }

            y_axis += String(temp_y) + ","
        }
        
        y_axis += "]"
        
        var output_string = "setView4(" + x_axis + "," + y_axis + ")"
        
        return output_string

        
        
    }
    
    
    
    
//
//        let arraySubStrings: [Substring] = result.split(separator: "<")
//        var each_data: [String] = []
//        for item in arraySubStrings{
//            let temp_data: [Substring] = item.split(separator: ";")
//            if(temp_data.count < 7){
//                continue
//            }
//            let user_name: String = String(temp_data[0])
//            let user_time: String = String(temp_data[1])
//            let photo_url: String = "http://39.100.73.118/deeplearning_photo/uploads/" + String(temp_data[2])
//            let food_type: String = String(temp_data[3])
//            let photo_cal: String = String(temp_data[4])
//            let photo_kind: String = String(temp_data[5])
//            let workday: String = String(temp_data[6])
//            let weekend: String = String(temp_data[7])
//
//            let temp_date:String = String(user_time.split(separator: "-")[0])
//            let temp_time:String = String(user_time.split(separator: "-")[1])
//            let temp_hour:String = String(temp_time.split(separator: ":")[0])
//            let temp_minute:String = String(temp_time.split(separator: ":")[1])
//            let temp_second:String = String(temp_time.split(separator: ":")[2])
//
//            let new_user_time:String = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second
//            if(!user_time_list.contains(new_user_time)){
//                database.tableLampInsertItem(user_name: user_name, user_time: user_time, photo_type: food_type, photo_url: photo_url, photo_kind: photo_kind, photo_cal: photo_cal, photo_web_url: photo_url, workday: workday, weekend: workday)
//            }else{
//                database.tableLampUpdateItem(user_name: user_name, user_time: user_time, photo_type: food_type, photo_kind: photo_kind, photo_cal: photo_cal, photo_web_url: photo_url, workday: workday, weekend: workday)
//            }
//
//        }
//
//        database.queryTableLamp()
        
        
    
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

//
//        imagePositionButton1.frame = CGRectFlat(150, 400, 100,100)
//
//
//        fillButton1.frame = CGRectFlat(buttonMinX, 600, buttonSize.width, buttonSize.height)
//
//
//        separatorLayer1.frame = CGRect(x: 0, y: 600 - PixelOne, width: view.bounds.width, height: PixelOne)
//
//        var farme = separatorLayer1.frame
//
//
//        farme = separatorLayer1.frame

    }
    
    
    
    
    
    
    
    
}
