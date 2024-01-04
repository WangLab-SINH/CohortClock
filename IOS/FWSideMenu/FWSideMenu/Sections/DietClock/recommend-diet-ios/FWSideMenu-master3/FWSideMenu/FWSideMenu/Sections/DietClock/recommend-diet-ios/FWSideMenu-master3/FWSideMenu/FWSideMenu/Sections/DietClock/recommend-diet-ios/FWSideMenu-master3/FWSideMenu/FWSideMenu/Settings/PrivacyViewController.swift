//
//  PrivacyViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/8.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import WebKit
import SwiftDate
class PrivacyViewController: UIViewController, WKNavigationDelegate, WKScriptMessageHandler {
    
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
        var webView = WKWebView(frame: self.view.frame, configuration: configuration)
        // 让webview翻动有回弹效果
        webView.scrollView.bounces = true
        // 只允许webview上下滚动
        webView.scrollView.alwaysBounceVertical = true
        
        // 此句要求实现WKNavigationDelegate协议
        webView.navigationDelegate = self
        
        return webView
    }()
    
    // 加载html
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "privacy", ofType: "html")!, encoding: String.Encoding.utf8)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        view.addSubview(webView)

        
        
        
        
        
        let bundleStr = Bundle.main.url(forResource: "privacy", withExtension: "html")

       

        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        
       
    //    NotificationCenter.default.addObserver(self, selector: "refreshPage", name: NSNotification.Name(rawValue: "homeRefresh"), object: nil)
        
        
        
        
//        webView.loadHTMLString(html, baseURL: nil)
    }
    
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let bundleStr = Bundle.main.url(forResource: "privacy", withExtension: "html")
        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
    }
    
    
    
//
//    func refreshPage()
//
//        {
//
//            if self.navigationController != nil
//
//            {
//
//                //刷新页面
//
//                self.navigationController?.popToRootViewController(animated: false)
//
//            }
//
//        }
//
//    deinit
//
//        {
//
//        NotificationCenter.default.removeObserver(self)
//
//        }
    
    
    
    
    
    
    
    
    
    
    
    
    func webView(_ webView: WKWebView,  didFinish navigation: WKNavigation!) {
        
       
        
        }
    

    
    // Swift方法，可以在JS中调用
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        print(message.body)
    }
    
    
    
 
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
        
        
    }
    
    
    
    
    
    
    
    
    
    
    

