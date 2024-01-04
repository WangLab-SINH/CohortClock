import UIKit
import WebKit
import SwiftDate
import MMKV
import AVFoundation
class TrendingViewController: UIViewController, WKNavigationDelegate, WKScriptMessageHandler {
    
    var refreshControl = UIRefreshControl()
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)

    
    
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
        configuration.userContentController.add(self, name: "myApp")
        
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
//    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "echarts_test", ofType: "html")!, encoding: String.Encoding.utf8)
    
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "echarts_test", ofType: "html")!, encoding: String.Encoding.utf8)
    
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        

        
        refreshControl.addTarget(self, action: "refresh", for: .valueChanged)
        webView.scrollView.addSubview(refreshControl)
        
        
        view.addSubview(webView)
        

        
        
//
//
        
//
//
//
//        NotificationCenter.defaultCenter().addObserver(self, selector: "refreshPage", name: "homeRefresh", object: nil)
//
//
//
//    func refreshPage()
//
//            {
//
//                if self.navigationController != nil
//
//                {
//
//                    //刷新页面
//
//                    self.navigationController?.popToRootViewController(animated: false)
//
//                }
//
//            }

//        deinit
//
//            {
//
//            NotificationCenter.defaultCenter().removeObserver(self)
//
//            }
//
    
        
        
        
        
        
        
        
        
        
        
        
        
        let bundleStr = Bundle.main.url(forResource: "echarts_test", withExtension: "html")

       

        webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        
       
    //    NotificationCenter.default.addObserver(self, selector: "refreshPage", name: NSNotification.Name(rawValue: "homeRefresh"), object: nil)
        
        
        
        
//        webView.loadHTMLString(html, baseURL: nil)
    }
    
    
    
    override func viewWillAppear(_ animated: Bool) {
        
        super.viewWillAppear(animated)
        
        
        let first = mmkv?.bool(forKey: "first_time") ?? false
        if(first == false){
            
            var viewController: UIViewController
            viewController = LaunchViewController()
            self.navigationController?.pushViewController(viewController, animated: true)
        }
        else{
//            let bundleStr = Bundle.main.url(forResource: "index_uniapp", withExtension: "html")
//            let bundleStr = Bundle.main.path(forResource: "index_uniapp", ofType: "html", inDirectory: "")
//
//            let dicPath = Bundle.main.bundlePath
//
//            var path = Bundle.main.path(forResource: "index_uniapp.html", ofType: "html", inDirectory: "h5") ?? ""
//            path = path + ""
//            //带参数时只能使用这种请求方式，上面的几种不可以
//            webView.load(URLRequest.init(url: URL.init(string: path, relativeTo: URL.init(fileURLWithPath: dicPath))!))
            
//            let url = Bundle.main.url(forResource: "index_uniapp", withExtension: "html")!
//                   webView.loadFileURL(url, allowingReadAccessTo: url)
//                   let request = URLRequest(url: url)
//            webView.load(request)
            
            var path = Bundle.main.path(forResource: "index_uniapp", ofType: "html") ?? ""
            let dicPath = Bundle.main.bundlePath
            path = path + ""
            //带参数时只能使用这种请求方式，上面的几种不可以
            webView.load(URLRequest.init(url: URL.init(string: path, relativeTo: URL.init(fileURLWithPath: dicPath))!))

            
            
            
            
//            webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        }
        
        
        
        
    }
    
    
    
//
    @objc func refresh()

        {
        webView.reload()
        Thread.sleep(forTimeInterval: 1)
        refreshControl.endRefreshing()
//            if self.navigationController != nil
//
//            {
//
//                //刷新页面
//
//                self.navigationController?.popToRootViewController(animated: false)
//
//            }

        }
//
//    deinit
//
//        {
//
//        NotificationCenter.default.removeObserver(self)
//
//        }
    
    
    
    
    @objc func changeWebView(){
        var current_url_list = String(webView.url?.absoluteString ?? "html")
        let temp_1:[Substring] = current_url_list.split(separator: "/")
        var current_url = String(temp_1[temp_1.count - 1])
        if(current_url == "echarts_test.html"){
           
            let bundleStr = Bundle.main.url(forResource: "FoodPhoto", withExtension: "html")
            webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        }else{
           
            let bundleStr = Bundle.main.url(forResource: "echarts_test", withExtension: "html")
            webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        }
       
    }
    
    func createRandomMan(start: Int, end: Int) ->() -> Int?{
        var nums = [Int]()
        for i in start...end{
            nums.append(i)
        }
        
        func randomMan() -> Int!{
            if !nums.isEmpty{
                let index = Int(arc4random_uniform(UInt32(nums.count)))
                return nums.remove(at: index)
            }
            else{
                return nil
            }
        }
        
        return randomMan
    }
    
    
    func getRandomPhotoPath() -> String{
        var FoodPathNormal: [String] = []
        FoodPathNormal = ["l1.jpg","l2.jpg","l3.jpg","l4.jpg","l5.jpg","l6.jpg","l7.jpg","l8.jpg","l9.jpg","l10.jpg","l11.jpg","l12.jpg","l13.jpg","l14.jpg","l15.jpg","l16.jpg","l17.jpg","l18.jpg","l19.jpg","l20.jpg","l21.jpg","l22.jpg","l23.jpg","l24.jpg","l25.jpg","l26.jpg","l27.jpg","l28.jpg","m1.jpg","m2.jpg","m3.jpg","m4.jpg","m5.jpg","m6.jpg","m7.jpg","m8.jpg","m9.jpg","m10.jpg","m11.jpg","m12.jpg","m13.jpg","m14.jpg","m15.jpg","m16.jpg","m17.jpg","m18.jpg","m19.jpg","m20.jpg","m21.jpg","m22.jpg","m23.jpg","m24.jpg","m25.jpg"]
        
//        let temp_1:[Substring] = temp_string1.split(separator: ",")
//        for i in 0...temp_1.count{
//            FoodPathNormal.append(String(temp_1[i]))
//        }
        
        
        var FoodPathHigh: [String] = []
//        FoodPathHigh = ["img/m1.jpg","img/m2.jpg","img/m3.jpg","img/m4.jpg","img/m5.jpg","img/m6.jpg","img/m7.jpg","img/m8.jpg","img/m9.jpg","img/m10.jpg","img/m11.jpg","img/m12.jpg","img/m13.jpg","img/m14.jpg","img/m15.jpg","img/m16.jpg","img/m17.jpg","img/m18.jpg","img/m19.jpg","img/m20.jpg","img/m21.jpg","img/m22.jpg","img/m23.jpg","img/m24.jpg","img/m25.jpg","img/h1.jpg","img/h2.jpg","img/h3.jpg","img/h4.jpg","img/h5.jpg","img/h6.jpg","img/h7.jpg","img/h8.jpg","img/h9.jpg","img/h10.jpg","img/h11.jpg","img/h12.jpg","img/h13.jpg","img/h14.jpg","img/h15.jpg","img/h16.jpg","img/h17.jpg","img/h18.jpg","img/h19.jpg","img/h20.jpg","img/h21.jpg","img/h22.jpg","img/h23.jpg","img/h24.jpg","img/h25.jpg","img/h26.jpg"]
        
        FoodPathHigh = ["m1.jpg","m2.jpg","m3.jpg","m4.jpg","im5.jpg","m6.jpg","m7.jpg","m8.jpg","m9.jpg","m10.jpg","m11.jpg","m12.jpg","m13.jpg","m14.jpg","m15.jpg","m16.jpg","m17.jpg","m18.jpg","m19.jpg","m20.jpg","m21.jpg","m22.jpg","m23.jpg","m24.jpg","m25.jpg","h1.jpg","h2.jpg","h3.jpg","h4.jpg","h5.jpg","h6.jpg","h7.jpg","h8.jpg","h9.jpg","h10.jpg","h11.jpg","h12.jpg","h13.jpg","h14.jpg","h15.jpg","h16.jpg","h17.jpg","h18.jpg","h19.jpg","h20.jpg","h21.jpg","h22.jpg","h23.jpg","h24.jpg","h25.jpg","h26.jpg","h26.jpg","h26.jpg","h26.jpg","h26.jpg","h26.jpg","h26.jpg"]
        
        
        var temp_index_list = createRandomMan(start: 0, end: 52)
        var current_index: [Int] = []
        for i in 0...11{
            current_index.append(Int(temp_index_list() ?? 1))
        }
        
        var result_string: String = "setPhotoView("
        for i in 0...11{
            result_string += "'" + FoodPathNormal[current_index[i]] + "',"
        }
        result_string += ")"
        return result_string
        
        
        
        
        
    }
    
    
    


    
    
    func webView(_ webView: WKWebView,  didFinish navigation: WKNavigation!) {
        
        
        }
    
//    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!)) {
//        // 调用JS方法
//        let temp_string: String = "sayHello(['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00'])"
//        webView.evaluateJavaScript(temp_string) { (result, err) in
//            // result是JS返回的值
//            print(result, err)
//        }
//
//
//
//    }
//
    // 加载完毕以后执行
    
    
    // Swift方法，可以在JS中调用

    
    // Swift
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        if message.name == "myApp" {
            let data = message.body as! [String: Any]
            let action = data["action"] as! String
            
            if action == "showAlert" {
//                let message = data["message"] as! String
//                let alert = UIAlertController(title: "Alert", message: message, preferredStyle: .alert)
//                alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
//                self.present(alert, animated: true, completion: nil)
                
                var viewController: UIViewController?
                
                var firstText: String = data["message"] as! String
                viewController = BaseInfoViewController(text: firstText)
                
                

                if let viewController = viewController {
                    viewController.title = firstText
                   
                    navigationController?.pushViewController(viewController, animated: true)
                }
                
                
            }
        }
    }

    
    func getDisturbutionData() -> String{
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let user_distribution_temp_work: [String] = database.queryTableInDistributionWork()
        let user_distribution_temp_weekend: [String] = database.queryTableInDistributionWeekend()
        var string_work_data: String = ""
        var string_weekend_data: String = ""
        for i in 0...(user_distribution_temp_work.count - 1){
            if(user_distribution_temp_work[i] != ""){
                string_work_data = user_distribution_temp_work[i]
            }
        }
        
        for i in 0...(user_distribution_temp_weekend.count - 1){
            if(user_distribution_temp_weekend[i] != ""){
                string_weekend_data = user_distribution_temp_weekend[i]
            }
        }

        
        
        
        

        var new_string_work_data: String = ""
        var new_string_weekend_data: String = ""
        let temp_work_data:[Substring] = string_work_data.split(separator: ",")
        let total_count_work: Int = temp_work_data.count
        for j in 0..<total_count_work{
            if(String(temp_work_data[j]) == "nan"){
                if(j == (temp_work_data.count - 1)){
                    new_string_work_data += "0"
                }else{
                    new_string_work_data += "0,"
                }
                
            }
            else{
                if(j == (temp_work_data.count - 1)){
                    new_string_work_data += temp_work_data[j]
                }else{
                    new_string_work_data += temp_work_data[j] + ","
                }
            }
        }
        
        
        let temp_weekend_data:[Substring] = string_weekend_data.split(separator: ",")
        let total_count_weekend: Int = temp_weekend_data.count
        for j in 0..<total_count_weekend{
            if(String(temp_weekend_data[j]) == "nan"){
                if(j == (temp_weekend_data.count - 1)){
                    new_string_weekend_data += "0"
                }else{
                    new_string_weekend_data += "0,"
                }
                
            }
            else{
                if(j == (temp_weekend_data.count - 1)){
                    new_string_weekend_data += temp_weekend_data[j]
                }else{
                    new_string_weekend_data += temp_weekend_data[j] + ","
                }
            }
        }
        
        
        
        var output_distribution_string:String = "setView(['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00'],[" + new_string_work_data
        output_distribution_string += "],[" + new_string_weekend_data + "])"
        
        return output_distribution_string
        
        
        
        
        
        
        
        
    }
    
    
    
    func getHeatmapData() -> String{
        //***********code in LocalEcharsFrament start
        var time_list_array: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let user_time_list_temp: [String] = database.queryTableInHeatmap()
        var user_time_list_to_sort: [DateInRegion] = []
        var new_date_list: [String] = []
        //debugPrint(user_time_list)
        if(user_time_list_temp.count <= 0){
            return ""
        }else{
            for i in 0...(user_time_list_temp.count - 1){
                user_time_list_to_sort.append(user_time_list_temp[i].toDate("yyyy.MM.dd-HH:mm:ss")!)
            }
            
            user_time_list_to_sort = DateInRegion.sortedByOldest(list: user_time_list_to_sort)
            for i in 0...(user_time_list_to_sort.count - 1){
                new_date_list.append(user_time_list_to_sort[i].toFormat("yyyy.MM.dd-HH:mm:ss"))
            }
            
            //debugPrint(user_time_list)
            let date = Date()
            let timeFormatter = DateFormatter()
            timeFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
            var current_time = timeFormatter.string(from: date) as String
            //************code end
            
            //************code in PlotFunction getDataHeatmapType start
            var new_date_list1: [String] = []
            
            for h in 0...(new_date_list.count - 1){
                let temp_h = new_date_list[h]
                let temp_time111 = timeFormatter.date(from: temp_h)! - 3.hours
                let temp_day1 = timeFormatter.string(from: temp_time111) as String
                new_date_list1.append(temp_day1)
            }
            
            new_date_list = new_date_list1
            
            let temp_time112 = timeFormatter.date(from: current_time)! - 3.hours
            current_time = timeFormatter.string(from: temp_time112) as String
            var out_string = ""
            let first_input_current_time = new_date_list[new_date_list.count - 1]
            var fromDate2: Date
            var toDate2: Date
            let temp21: [Substring] = first_input_current_time.split(separator: "-")[1].split(separator: ":")
            let temp_hour: String = String(temp21[0])
            var temp_minute: String = String(temp21[1])
            if(Int(temp_minute)! < 30){
                temp_minute = "00"
            }else{
                temp_minute = "30"
            }
            let new_first_input_current_time: String = first_input_current_time.split(separator: "-")[0] + "-" + temp_hour + ":" + temp_minute + ":00"
            let temp22: [Substring] = current_time.split(separator: "-")[1].split(separator: ":")
            let temp_hour1: String = String(temp22[0])
            var temp_minute1 = String(temp22[1])
            if(Int(temp_minute1)! < 30){
                temp_minute1 = "00"
            }else{
                temp_minute1 = "30"
            }
            let new_current_time = current_time.split(separator: "-")[0] + "-" + temp_hour1 + ":" + temp_minute1 + ":00"
            
            fromDate2 = timeFormatter.date(from: new_first_input_current_time)!
            toDate2 = timeFormatter.date(from: new_current_time)!
            let tem = toDate2 - fromDate2
            let from_to_minutes: Int = Int(tem.minute!)
            let to_from_hour: Double = Double(Double(tem.hour!) + (Double(from_to_minutes) / 60))
            var start_index: Int = Int(to_from_hour * 2)
            while(start_index >= 96){
                start_index -= 48
            }
            let len: Int = new_date_list.count
            let temp_current_time: String = new_date_list[(len - 1)]
            var temp: [Substring] = temp_current_time.split(separator: "-")
            let current_day: String = String(temp[0])
            let current_hour: String = String(temp[1])
            temp = current_hour.split(separator: ":")
            let hour_list:String = String(temp[0])
            var current_day_list: [String] = []
            current_day_list.append(current_day)
            var time_list: [Double] = []
            for i in 0...(new_date_list.count - 1){
                let temp_current_day: String = String(new_date_list[i].split(separator: "-")[0])
                let temp_current_hour: String = String(new_date_list[i].split(separator: "-")[1])
                if(temp_current_day == current_day_list[0]){
                    let temp2: [Substring] = temp_current_hour.split(separator: ":")
                    let input_hour: Double = Double(temp2[0])! + (Double(String(temp2[1]))! / 60)
                    time_list.append(input_hour)
                }
            }
            
            var time_level: [Int] = []
            var first_time: Double = time_list[0]
            var input_current_time: Double = time_list[(time_list.count - 1)]
            var start_flag: Int = 0
            var start_hour: Int = Int(floor(input_current_time))
            if(start_hour < 21){
                start_hour += 3
            }else{
                start_hour = start_hour + 3 - 24
            }
            if(input_current_time.truncatingRemainder(dividingBy: 1) >= 0.5){
                input_current_time = floor(input_current_time) + 0.5
            }else{
                input_current_time = floor(input_current_time)
                start_flag = 1
            }
            
            
            if(Double(start_index).truncatingRemainder(dividingBy: 2) == 1){
                if(start_flag == 1){
                    start_flag = 0
                }else{
                    start_flag = 1
                }
            }
            
            
            if(Double(start_index).truncatingRemainder(dividingBy: 2) == 1){
                first_time = floor(first_time) + 0.5
            }else{
                first_time = floor(first_time)
            }
            
            var limit_no12: Double
            var limit_6: Double
            var limit_8: Double
            var limit_10: Double
            var limit_12: Double
            
            if (input_current_time >= 14) {
               if (first_time > 19) {
                   //limit_no12 = input_current_time + 12;
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               } else if (input_current_time >= 19) {
                   //limit_no12 = input_current_time + 12;
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               } else if (input_current_time >= first_time + 12) {
                   //limit_no12 = input_current_time + 12;
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               } else {
                   limit_6 = first_time + 6
                   limit_8 = first_time + 8
                   limit_10 = first_time + 10
                   limit_12 = first_time + 12
                   //limit_no12 = input_current_time + 12;
                   limit_no12 = input_current_time + 12
                   if (limit_12 >= limit_no12) {
                       limit_no12 = limit_12
                   }
               }
           } else {
               if (first_time > 19) {
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               } else if (input_current_time >= 19) {
                   //limit_no12 = input_current_time + 12;
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               } else if (input_current_time >= first_time + 12) {
                   limit_no12 = input_current_time + 12
                   limit_6 = limit_no12 + 6
                   limit_8 = limit_no12 + 8
                   limit_10 = limit_no12 + 10
                   limit_12 = limit_no12 + 12
               }else {
                   limit_6 = first_time + 6
                   limit_8 = first_time + 8
                   limit_10 = first_time + 10
                   limit_12 = first_time + 12
                let temp_limit_no12: Double = min(limit_12 + 12, 19 + 12)
                   limit_no12 = temp_limit_no12
               }


           }
            
            
            for i in 0...143 {
                var temp_input_current_time: Double = (input_current_time + (Double(i) / 2));
                if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
                    time_level.append(1);
                }
                else if (temp_input_current_time < limit_6 && temp_input_current_time < 19) {
                    time_level.append(5);
                }
                else if (temp_input_current_time < limit_8 && temp_input_current_time < 19) {
                    time_level.append(4);
                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19) {
                    time_level.append(3);
                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19) {
                    time_level.append(2);
                } else if (temp_input_current_time < limit_no12) {
                    time_level.append(1);
                } else if (temp_input_current_time >= limit_no12) {
                    if (temp_input_current_time < limit_6 && temp_input_current_time < 19 + 24) {
                        time_level.append(5);
                    }
                    else if (temp_input_current_time < limit_8 && temp_input_current_time < 19 + 24) {
                        time_level.append(4);
                    } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19 + 24) {
                        time_level.append(3);
                    } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19 + 24) {
                        time_level.append(2);
                    } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 19 + 24) {
                        time_level.append(5);
                    } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 19 + 24) {
                        time_level.append(4);
                    } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 19 + 24) {
                        time_level.append(3);
                    } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 19 + 24) {
                        time_level.append(2);
                    } else if (temp_input_current_time < min(limit_no12 + 24, 19 + 24 + 12)) {
                        time_level.append(1);
                    }else if(temp_input_current_time < min(limit_no12 + 24 + 6, 19 + 24 + 12 + 6)){
                        time_level.append(5);
                    }else if (temp_input_current_time < min(limit_no12 + 24 + 8, 19 + 24 + 12 + 8)) {
                        time_level.append(4);
                    } else if (temp_input_current_time < min(limit_no12 + 24 + 10, 19 + 24 + 12 + 10)) {
                        time_level.append(3);
                    } else if (temp_input_current_time < min(limit_no12 + 24 + 12, 19 + 24 + 12 + 12)) {
                        time_level.append(2);
                    } else if (temp_input_current_time < min(limit_no12 + 24 + 24, 19 + 24 + 12 + 24)) {
                        time_level.append(1);
                    }




                    else if (temp_input_current_time < min(limit_no12 + 54, 85)) {
                        time_level.append(5);
                    }else if (temp_input_current_time < min(limit_no12 + 56, 87)) {
                        time_level.append(4);
                    }else if (temp_input_current_time < min(limit_no12 + 58, 89)) {
                        time_level.append(3);
                    }else if (temp_input_current_time < min(limit_no12 + 60, 91)) {
                        time_level.append(2);
                    }else if (temp_input_current_time < min(limit_no12 + 72, 103)) {
                        time_level.append(1);
                    }

                } else {
                    time_level.append(1);
                }
            }
            
            for i in start_index...start_index + 47 {
                if (time_level[i] == 1) {
                    out_string += "[" + "0" + "," + String(i - start_index) + "," + "1" + "]" + ",";
                } else if (time_level[i] == 2) {
                    out_string += "[" + "1" + "," + String(i - start_index) + "," + "2" + "]" + ",";
                    out_string += "[" + "0" + "," + String(i - start_index) + "," + "2" + "]" + ",";
                } else if (time_level[i] == 3) {
                    out_string += "[" + "2" + "," + String(i - start_index) + "," + "3" + "]" + ",";
                    out_string += "[" + "1" + "," + String(i - start_index) + "," + "3" + "]" + ",";
                    out_string += "[" + "0" + "," + String(i - start_index) + "," + "3" + "]" + ",";
                } else if (time_level[i] == 4) {
                    out_string += "[" + "3" + "," + String(i - start_index) + "," + "4" + "]" + ",";
                    out_string += "[" + "2" + "," + String(i - start_index) + "," + "4" + "]" + ",";
                    out_string += "[" + "1" + "," + String(i - start_index) + "," + "4" + "]" + ",";
                    out_string += "[" + "0" + "," + String(i - start_index) + "," + "4" + "]" + ",";
                } else if (time_level[i] == 5) {
                    out_string += "[" + "4" + "," + String(i - start_index) + "," + "5" + "]" + ",";
                    out_string += "[" + "3" + "," + String(i - start_index) + "," + "5" + "]" + ",";
                    out_string += "[" + "2" + "," + String(i - start_index) + "," + "5" + "]" + ",";
                    out_string += "[" + "1" + "," + String(i - start_index) + "," + "5" + "]" + ",";
                    out_string += "[" + "0" + "," + String(i - start_index) + "," + "5" + "]" + ",";
                }
            }
            out_string += "&";
            if (start_flag == 1) {
                for i in 0...24 {
                    var temp_time: Int = start_hour + i + Int(ceil(Double(start_index) / 2)) - 1;
                    var temp_time1: Int = start_hour + i + Int(ceil(Double(start_index) / 2));
                    while(temp_time >= 24){
                        temp_time -= 24;
                    }
                    while(temp_time1 >= 24){
                        temp_time1 -= 24;
                    }
                    if (i == 0) {
                        out_string += "'现在'" + ",";
                    } else {
                        out_string += "'" + String(temp_time) + ":" + "30" + "',";
                        out_string += "'" + String(temp_time1) + ":" + "00" + "',";
                    }
                }
            } else {
                for i in 0...24 {
                    var temp_time: Int = start_hour + i + Int(floor(Double(start_index) / 2));
                    while(temp_time >= 24){
                        temp_time -= 24;
                    }
                    if (i == 0) {
                        out_string += "'现在',";
                    } else {
                        out_string += "'" + String(temp_time) + ":" + "00" + "',";
                        out_string += "'" + String(temp_time) + ":" + "30" + "',";
                    }
                }
            }
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            //************end
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            

            return out_string
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
        
        
    }
    
    
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
