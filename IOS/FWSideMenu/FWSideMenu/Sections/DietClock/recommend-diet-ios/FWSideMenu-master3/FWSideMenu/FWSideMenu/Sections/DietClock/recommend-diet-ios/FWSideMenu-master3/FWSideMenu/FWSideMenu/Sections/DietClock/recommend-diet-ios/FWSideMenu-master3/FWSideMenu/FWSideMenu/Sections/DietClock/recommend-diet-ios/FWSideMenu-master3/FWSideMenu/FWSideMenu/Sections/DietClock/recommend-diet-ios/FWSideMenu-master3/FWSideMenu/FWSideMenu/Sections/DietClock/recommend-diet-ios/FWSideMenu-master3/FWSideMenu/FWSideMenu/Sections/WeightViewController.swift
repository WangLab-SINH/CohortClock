//
//  WeightViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/21.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import WebKit
import SwiftDate
class WeightViewController: UIViewController, WKNavigationDelegate, WKScriptMessageHandler {
    

    
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
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "weight_echart", ofType: "html")!, encoding: String.Encoding.utf8)
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
        view.addSubview(webView)
        

        
        

        
        let bundleStr = Bundle.main.url(forResource: "weight_echart", withExtension: "html")

       

        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        
       
    //    NotificationCenter.default.addObserver(self, selector: "refreshPage", name: NSNotification.Name(rawValue: "homeRefresh"), object: nil)
        
        
        
        
//        webView.loadHTMLString(html, baseURL: nil)
    }
    
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let bundleStr = Bundle.main.url(forResource: "weight_echart", withExtension: "html")
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
        let weight_string: [String] = database.queryTableWeight()
        let weight_time_string: [String] = database.queryTableWeightTime()
        
        var weight_time_dic:[String:String] = [:]
        
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
        
        var weight_time_list: [DateInRegion] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
        }
        
        weight_time_list = DateInRegion.sortedByOldest(list: weight_time_list)
        
        
        var x_axis = "["
        var y_axis = "["
        for i in 0...(weight_time_dic.count - 1){
            x_axis += "'" + weight_time_list[i].toFormat("yyyy-MM-dd") + "',"
            y_axis += (weight_time_dic[weight_time_list[i].toFormat("yyyy-MM-dd")] ?? "0") + ","
        }
        x_axis += "]"
        y_axis += "]"
        
        var output_string = "setView(" + x_axis + "," + y_axis + ")"
        
        return output_string

        
        
    }
    
    
    func getWeightNumFunction() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let weight_string: [String] = database.queryTableWeight()
        let weight_time_string: [String] = database.queryTableWeightTime()
        
        var weight_time_dic:[String:String] = [:]
        
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
        
        var weight_time_list: [DateInRegion] = []
        var weight_value_list: [Int] = []
        
        for (key, value) in weight_time_dic{
            weight_time_list.append(key.toDate("yyyy.MM.dd")!)
            weight_value_list.append(Int(Double(value) ?? 0))
        }
        
        
        var value_min = weight_value_list.min() ?? 0 - 5
        var value_max = weight_value_list.max() ?? 0 + 5
        
        
        var x_axis = "["
        var y_axis = "["
        for i in value_min...value_max{
            x_axis += "'" + String(i) + "',"
            var temp_y = 0
            for j in 0...(weight_value_list.count - 1){
                if(weight_value_list[j] == i){
                    temp_y += 1
                }
            }
            y_axis += String(temp_y) + ","
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
//        weight_time_list = DateInRegion.sortedByOldest(list: weight_time_list)
//
//
//        var x_axis = "["
//        var y_axis = "["
//        for i in 0...(weight_time_dic.count - 1){
//            x_axis += "'" + weight_time_list[i].toFormat("yyyy-MM-dd") + "',"
//            y_axis += weight_time_dic[weight_time_list[i].toFormat("yyyy-MM-dd")] ?? "0" + ","
//        }
        x_axis += "]"
        y_axis += "]"
        
        var output_string = "setView1(" + x_axis + "," + y_axis + ")"
        
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
