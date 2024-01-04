//
//  RecentViewController.swift
//  FWSideMenu
//
//  Created by xfg on 2018/4/8.
//  Copyright © 2018年 xfg. All rights reserved.
//

import Foundation
import UIKit
import WebKit
import JavaScriptCore





//
@objc protocol SwiftJavaScriptDelegate: JSExport {

    // js调用App的微信支付功能 演示最基本的用法
    func wxPay(_ orderNo: String)

    // js调用App的微信分享功能 演示字典参数的使用
    func wxShare() -> String
    
    // js调用App方法时传递多个参数 并弹出对话框 注意js调用时的函数名
    func showDialog(_ title: String, message: String)

    // js调用App的功能后 App再调用js函数执行回调
    func callHandler(_ handleFuncName: String)

}

// 定义一个模型 该模型实现SwiftJavaScriptDelegate协议
@objc class SwiftJavaScriptModel: NSObject, SwiftJavaScriptDelegate {

    weak var controller: UIViewController?
    weak var jsContext: JSContext?

    func wxPay(_ orderNo: String) {

        print("订单号：", orderNo)

        // 调起微信支付逻辑
    }

    func wxShare() -> String {

        return "['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00']"

        // 调起微信分享逻辑
    }

    func showDialog(_ title: String, message: String) {

        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
        self.controller?.present(alert, animated: true, completion: nil)
    }

    func callHandler(_ handleFuncName: String) {

        let jsHandlerFunc = self.jsContext?.objectForKeyedSubscript("\(handleFuncName)")
        let dict = ["name": "sean", "age": 18] as [String : Any]
        let _ = jsHandlerFunc?.call(withArguments: [dict])
    }
}












class RecentViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, WKScriptMessageHandler, WKUIDelegate, WKNavigationDelegate{
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        print("JS发送到IOS的数据====\(message.body), name=\(message.name)")
    }
    
    
    
    
    
    
    
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
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "echarts_test", ofType: "html")!, encoding: String.Encoding.utf8)
    
    
    
    
    
    
    
    
    
    
    
    let imageArray = ["header_0", "header_1", "header_2", "header_3", "header_4", "header_5", "header_6", "header_7", "header_8", "header_9", "header_10"]
    let titleArray = ["服务号", "小甜", "怪蜀黍", "恶天使", "小鱼人", "我的其他QQ账号", "关联账号", "QQ直播", "QQ购物", "HI", "企鹅大叔"]
    let decArray = ["QQ天气：【鼓楼】多云15°/28°，08:05更新~", "[图片]", "或者由于。。。", "你们怕吗？怕你？不存在的。。。", "喂鱼咯", "关联QQ号，代收其他账号好友消息。", "关联账号就是方便，随心所欲，想收就收~", "恭喜你获得10个春暖花开红包~", "前不够花？手机可随借1000-3000元！", "HI咯", "元宵快乐，来给好友们送上祝福吧！"]
    var timeArray: [String] = []
    
    
    lazy var tableView: UITableView = {
        
        let tableview = UITableView(frame: self.view.bounds)
        tableview.separatorStyle = .none
        return tableview
    }()
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // 设置导航栏
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.resizableImage(imageName: "header_bg_message", edgeInsets: UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)), for: .default)
        
        self.setNeedsStatusBarAppearanceUpdate()
        
        // 本页面开启支持打开侧滑菜单
        self.menuContainerViewController.sideMenuPanMode = .defaults
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        // 设置导航栏
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
        
        // 本页面开启支持关闭侧滑菜单
        self.menuContainerViewController.sideMenuPanMode = .none
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    
    
 
    
    
    
    var wkWebView: WKWebView?
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        view.addSubview(webView)
        
        webView.loadHTMLString(html, baseURL: nil)
        
       
        
        
        
        
        
        
        

//        let webView = WKWebView(frame: CGRect(x: 0, y: 0, width: self.view.frame.size.width, height: self.view.frame.size.height+20))
//        // addSubview   大家都懂，我就不解释了
//        view.addSubview(webView)
//        // 禁止顶部下拉和底部上拉效果，适用在  不让webview 有多余的滚动   设置后，滚动范围跟网页内容的高度相同
//        webView.scrollView.bounces = false
//
//
//
//
//        /** 加载本地html文件 */
//        //从主Bundle获取 HTML 文件的 url
//        let bundleStr = Bundle.main.url(forResource: "echarts_test", withExtension: "html")
//
//        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        //webView.configuration.userContentController.add(self, name: "nativeMethod")
        
        
        
//        func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
//
//                print(message)//WKScriptMessage对象
//                print(message.name) //name : nativeMethod
//                print(message.body) //js回传参数
//            }


      //  webView.configuration.userContentController.removeScriptMessageHandler(forName:"jsToIOS")
        
        
        

        
        
        

        
        
        
//        wkWebView = {
//                    let config = WKWebViewConfiguration.init()
//                    let webView = WKWebView(frame: CGRect(x: 0, y: 44, width: self.view.frame.size.width, height: self.view.frame.size.height-44), configuration: config)
//                    return webView
//                }()
//                // 创建URL和请求，加载本地html文件
//                createUrlAndRequest()
//                // 创建按钮用于测试
//                createBtn()
//                // 向网页注入JS
//                addJSFromIOS()
        
        
//        self.view.backgroundColor = UIColor.white
//        self.navigationItem.title = "饮食习惯"
//        self.view.frame.size.height = UIScreen.main.bounds.height - kStatusAndNavBarHeight - kTabBarHeight
//
//        for index in 0...self.imageArray.count {
//            self.timeArray.append(self.obtainRandomTime(index: index))
//        }
//
//        let buttonItem: UIBarButtonItem = UIBarButtonItem(image: UIImage(named: "header"), style: .plain, target: self, action: #selector(leftBtnAction))
//        buttonItem.imageInsets = UIEdgeInsetsMake(0, -6, 0, 0)
//        self.navigationItem.leftBarButtonItem = buttonItem
//
//        let buttonItem2: UIBarButtonItem = UIBarButtonItem(image: UIImage(named: "mqz_nav_add"), style: .plain, target: self, action: #selector(rightBtnAction))
//        buttonItem2.imageInsets = UIEdgeInsetsMake(0, 0, 0, -6)
//        self.navigationItem.rightBarButtonItem = buttonItem2
//
//
//        self.view.addSubview(self.tableView)
//        self.tableView.register(UINib(nibName: "RecentTableViewCell", bundle: nil), forCellReuseIdentifier: "cellId")
//        self.tableView.delegate = self
//        self.tableView.dataSource = self
    }
    
    
    
    
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        // 调用JS方法
        let temp_string: String = "sayHello('['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00']')"
        webView.evaluateJavaScript(temp_string) { (result, err) in
            // result是JS返回的值
            print(result, err)
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @objc func leftBtnAction() {
        self.menuContainerViewController.toggleLeftSideMenu(completeBolck: nil)
    }
    
    @objc func rightBtnAction() {
        self.menuContainerViewController.toggleRightSideMenu(completeBolck: nil)
    }
    
    func obtainRandomTime(index: Int) -> String {
        return "\(arc4random()%2 == 0 ? "上午" : "下午")" + "\(arc4random()%12):\(arc4random()%5)\(arc4random()%9)"
    }
    
    
    
    
    //***********************************************
    func addJSFromIOS() {
        // ios 向网页注入 js，注入js分为在网页加载完毕注入(.atDocumentStart)和加载之前注入(.atDocumentEnd)
        let js = "document.getElementsByTagName('h2')[0].innerText='我是ios原生为h5注入的方法'"
        let script = WKUserScript.init(source: js, injectionTime: .atDocumentEnd, forMainFrameOnly: true)
        wkWebView!.configuration.userContentController.addUserScript(script)
        // jsToIOS 是JavaScript向IOS发送数据时，使用的函数名
        self.wkWebView?.configuration.userContentController.add(self, name: "jsToIOS")
     }

    func createUrlAndRequest() {
        // 使用本地文件测试
        let url = URL(string: "test_html")
        let request = URLRequest(url: url!)
        wkWebView!.navigationDelegate = self
        wkWebView!.uiDelegate = self
        wkWebView!.load(request)
        self.view.addSubview(wkWebView!)
    }

    func createBtn() {
        let leftBtn = UIButton(type: .custom)
        // 设置按钮的标题
        leftBtn.setTitle("点击调用h5方法", for: .normal)
        // 设置按钮的标题颜色
        leftBtn.setTitleColor(UIColor.blue, for: .normal)
        // 设置按钮标题文字大小
        leftBtn.titleLabel?.font = UIFont.systemFont(ofSize: 16)
        // 将按钮放入左侧导航中
        let leftBarItem = UIBarButtonItem(customView: leftBtn)
        self.navigationItem.leftBarButtonItem = leftBarItem
        // 为按钮添加事件
        leftBtn.addTarget(self, action: #selector(btnClick(_:)), for: .touchUpInside)
     }

    @objc func btnClick(_ button: UIButton) {
         // 重点：IOS 调用 JavaScript 的 navButtonAction 方法，并传递参数
         // 使用匿名回调函数接收方法返回值
         self.wkWebView!.evaluateJavaScript("navButtonAction('test1',18)") {
             (response, error) in
             print("message: \(response!)")
         }
     }
    //**********************************************

}











extension RecentViewController {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.imageArray.count
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 66.0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! RecentTableViewCell
        cell.headerImgView.image = UIImage(named: self.imageArray[indexPath.row])
        cell.nameLabel.text = self.titleArray[indexPath.row]
        cell.decLabel.text = self.decArray[indexPath.row]
        cell.timeLabel.text = self.timeArray[indexPath.row]
        if cell.decLabel.text!.count > 15 {
            cell.tipLabel.isHidden = false
            cell.tipLabel.text = "\(cell.decLabel.text!.count % 10)"
        } else {
            cell.tipLabel.isHidden = true
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("您当前点击了第 \(indexPath.row + 1) 行")
        self.navigationController?.pushViewController(SubViewController(), animated: true)
    }
}




