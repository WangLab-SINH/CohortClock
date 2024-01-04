import UIKit
import WebKit
import SwiftDate
import MMKV
import AVFoundation
class TestHtmlCode: UIViewController, WKNavigationDelegate, WKScriptMessageHandler {
    
    var refreshControl = UIRefreshControl()
    var time_redu = 0
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("显示推荐食物111", for: .normal)
        fillButton.addTarget(self, action:#selector(changeWebView), for: .touchUpInside)
        return fillButton
    }()
    
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
//    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "echarts_test", ofType: "html")!, encoding: String.Encoding.utf8)
    
    let html = try! String(contentsOfFile: Bundle.main.path(forResource: "echarts_test", ofType: "html")!, encoding: String.Encoding.utf8)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        

        
        refreshControl.addTarget(self, action: "refresh", for: .valueChanged)
        webView.scrollView.addSubview(refreshControl)
        
        
        view.addSubview(webView)
        
        let food = mmkv?.bool(forKey: "food") ?? false
        if(food == false){
            
        }
        else{
            
        }
        
        
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
//        if(first == false){
//            
//            var viewController: UIViewController
//            viewController = LaunchViewController()
//            self.navigationController?.pushViewController(viewController, animated: true)
//        }
//        else{
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
            let currentWeight = mmkv?.string(forKey: "latest_weight") ?? "80"
            let currentHeight = mmkv?.string(forKey: "height") ?? "170"

            let temp_string_distribution: String = getDisturbutionData()
            var workday_list = temp_string_distribution.split(separator: ";")[0]
            var weekend_list = temp_string_distribution.split(separator: ";")[1]
            

            
            
            
            
            var current_diet_z = getHeatmapDataNew()
        var canEatFlag:String = "FALSE"
        var percentage:Int = 16*60*60
        var timeTotal:Int = 0
        
        if(current_diet_z == ""){
            let eatingStartTime = "8:00"
            let eatingEndTime = "16:00"
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "HH:mm"
            var dateBegin: Date?
            var dateEnd: Date?
            do{
                dateBegin = try dateFormatter.date(from: eatingStartTime)
                dateEnd = try dateFormatter.date(from: eatingEndTime)
            }catch{
                print(error)
            }
            
            let calendar = Calendar.current
            let hour = calendar.component(.hour, from: Date())
            let minute = calendar.component(.minute, from: Date())
            let current_time = dateFormatter.date(from: String(hour)+":"+String(minute))

            //var current_time = calendar.dateComponents([.hour, .minute], from: Date());
            canEatFlag = "FALSE"
            timeTotal = 0
            let timeString1 = "8:00"
            let timeString2 = "16:00"

            // 创建一个 DateFormatter 来解析时间字符串
            let dateFormatter1 = DateFormatter()
            // 设置日期格式器的格式为时:分:秒
            dateFormatter1.dateFormat = "HH:mm"

            // 使用 `DateFormatter` 转换字符串为 `Date` 对象
            if let begin = dateFormatter1.date(from: timeString1),
               let end = dateFormatter1.date(from: timeString2),
               let dateC = dateFormatter1.date(from: String(hour)+":"+String(minute)) {
                // 在此处已确定 date1 和 date2 不是 nil，可以进行比较
                if dateC > begin && dateC < end{
                    canEatFlag = "TRUE"
                    timeTotal = Int((end.timeIntervalSinceReferenceDate - dateC.timeIntervalSinceReferenceDate).rounded())
                    //percentage = 8*60*60
                } else{
                    canEatFlag = "FALSE"
                    if dateC < begin{
                        timeTotal = Int((begin.timeIntervalSinceReferenceDate - dateC.timeIntervalSinceReferenceDate).rounded())
                        //percentage = 16*60*60
                    }else{
                        timeTotal = Int((dateC.timeIntervalSinceReferenceDate - end.timeIntervalSinceReferenceDate).rounded())
                        //percentage = 16*60*60
                    }
                    
                }
            } else {
                
                    }
         }
        else{
            var indexNum = 0

            let dietLevel = current_diet_z.split(separator: ",")
            canEatFlag = dietLevel[0] == "1" || dietLevel[0] == "2" || dietLevel[0] == "3" ? "FALSE" : "TRUE"

            for indexI in 0..<48 {
                if canEatFlag == "FALSE" {
                    if !(dietLevel[indexI] == "1" || dietLevel[indexI] == "2" || dietLevel[indexI] == "3") {
                        break
                    } else {
                        indexNum += 1
                    }
                } else {
                    if dietLevel[indexI] == "1" || dietLevel[indexI] == "2" || dietLevel[indexI] == "3" {
                        break
                    } else {
                        indexNum += 1
                    }
                }
            }

            timeTotal = indexNum * 30 * 60
            let calendar = Calendar.current
            let minute = calendar.component(.minute, from: Date())
            let second = calendar.component(.second, from: Date())
            var minuteS = ""

            if minute < 30 {
                timeTotal -= minute * 60 + second
                minuteS = "0" + String(minute)
            } else {
                timeTotal -= (minute - 30) * 60 + second
            }

            // Assuming `time_redu` is defined previously
            timeTotal += time_redu

            if canEatFlag == "FALSE" && timeTotal > 16 * 60 * 60 {
                canEatFlag = "TRUE"
                timeTotal -= 16 * 60 * 60
            } else if canEatFlag == "TRUE" && timeTotal > 8 * 60 * 60 {
                canEatFlag = "FALSE"
                timeTotal -= 8 * 60 * 60
            }
        }
            
            
            
            
        var current_color = 2
        if canEatFlag == "FALSE"{
            current_color = 1
        }else{
            current_color = 2
        }
            
            
            
            var path = Bundle.main.path(forResource: "index_uniapp", ofType: "html") ?? ""
            let dicPath = Bundle.main.bundlePath
            path = path + "#/pages/index/second?abc=35,36,37&categories='2019','2020'&weight=80.6&height=178.6&timetotal="+String(timeTotal)+"&eatflag="+String(current_color)+"&sleep_x=2023-09-12,2023-09-13,2023-09-14,2023-09-15,2023-09-16,2023-09-17,2023-09-18,2023-09-19,2023-09-20,2023-09-21,2023-09-22,2023-09-23,2023-09-24,2023-09-25,2023-09-26,2023-09-27,2023-09-28,2023-09-29,2023-09-30,2023-10-01,2023-10-02,2023-10-03,2023-10-04,2023-10-05,2023-10-06,2023-10-07,2023-10-08,2023-10-09,2023-10-10,2023-10-11,2023-10-12,&sleep_start_time=null,null,20.09,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,23.11,22.08,null,null,22.08,22.24,null,&sleep_end_time=null,null,null,6.01,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,8.01,7.04,null,null,7.04,7.04,&sleep_dis_x=0:00,0:30,1:00,1:30,2:00,2:30,3:00,3:30,4:00,4:30,5:00,5:30,6:00,6:30,7:00,7:30,8:00,8:30,9:00,9:30,10:00,10:30,11:00,11:30,12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30,19:00,19:30,20:00,20:30,21:00,21:30,22:00,22:30,23:00,23:30&sleep_dis_y1=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,4,0,1,0,]&sleep_dis_y2=[0,0,0,0,0,0,0,0,0,0,0,0,1,0,4,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,]&workday_list=0.002033822872883332,0.0027042692422231725,0.0030810664464870057,0.0029605686726475173,0.0023710103899365273,0.0017148588523520796,0.0014513937434545686,0.0016438204514367875,0.0020002886899236926,0.0023271626971149138,0.0027905450558776395,0.0038884420918353767,0.006459739094041886,0.011382475850841933,0.018676140027743322,0.02721783535736534,0.0357038685361958,0.0429531092519459,0.046921626423510866,0.0452404024481672,0.037861951188110865,0.028072375052145007,0.020202139107159987,0.01694066643222648,0.018740812845864888,0.02447126518200416,0.03213563001095789,0.04000606514440831,0.04735888204412274,0.053279866529731844,0.05540592912157328,0.051527064408652776,0.04224286699173205,0.030837518246756384,0.0208837952972562,0.014791570358878651,0.013661329297561626,0.016782229691989708,0.02146288973383555,0.024856191499680297,0.02607043394821482,0.025598655438739095,0.023402107338568623,0.01909197470833538,0.013403390744032375,0.008014433474946838,0.004121263394890612,0.00185076195465903&weekend_list=0.00043153738598615944,0.0010104023878057598,0.001960609645732038,0.003158659881428744,0.0042461597096429896,0.004827469518657199,0.00480445152467625,0.00451199933585426,0.004457445814676474,0.004889134535183828,0.005611182537889923,0.006200201479760238,0.006404763402412198,0.006417926268517064,0.006892863229678426,0.008719866576784705,0.012586699808544653,0.01843219167225377,0.025161456764581317,0.031003477120442262,0.03437560026509414,0.034642426552009595,0.03235941270987683,0.029060114000951313,0.02668611570640158,0.0266802314406867,0.02912084524151814,0.03261544877398007,0.03521648657666086,0.035652374093928074,0.03385909966550874,0.0306302568881787,0.027094687516714584,0.024510714011140404,0.024062754602581338,0.02630107844024571,0.030606196548096598,0.03538141028080049,0.03900622375780459,0.040724604481315575,0.04066677687277315,0.03913024363663698,0.03602102391262663,0.031075364835049413,0.024511661856845977,0.01730014378880361,0.01074390530578744,0.005795046980545829&start_time=8:00&end_time=16:00&canedittime=false&percentage=57600"
            //带参数时只能使用这种请求方式，上面的几种不可以
            webView.load(URLRequest.init(url: URL.init(string: path, relativeTo: URL.init(fileURLWithPath: dicPath))!))

            
            
            
            
//            webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        
        
        
        
        
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
            fillButton1.setTitle("显示推荐食物", for: .normal)
            let bundleStr = Bundle.main.url(forResource: "FoodPhoto", withExtension: "html")
            webView.loadFileURL(bundleStr!, allowingReadAccessTo: Bundle.main.bundleURL)
        }else{
            fillButton1.setTitle("显示推荐饮食时间", for: .normal)
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
        
        var flag = 0
        
        var current_url_list = String(webView.url?.absoluteString ?? "html")
        let temp_1:[Substring] = current_url_list.split(separator: "/")
        var current_url = String(temp_1[temp_1.count - 1])
        if(current_url == "echarts_test.html"){
            flag = 0
        }else{
            flag = 1
        }
        
        
        if(flag == 1){
            let heapmap_result: String = getHeatmapData()
            let photo_path_result: String = getRandomPhotoPath()
            webView.evaluateJavaScript(photo_path_result) { (result, err) in
                // result是JS返回的值
                print(result, err)
            }
        }
        else{
            let heapmap_result: String = getHeatmapData()
            if(heapmap_result == ""){
               
            }else{
                let temp:[Substring] = heapmap_result.split(separator: "&")
                let plot_data = "[" + String(temp[0]) + "]"
                let x_axis = "[" + temp[1] + "]"
                
                let temp_string1: String = "setView1(" + plot_data + "," + x_axis + ")"
                
                let temp_string: String = getDisturbutionData()
                //print(temp_string)
            
                webView.evaluateJavaScript(temp_string) { (result, err) in
                    // result是JS返回的值
                    print(result, err)
                }
                
                

                webView.evaluateJavaScript(temp_string1) { (result, err) in
                    // result是JS返回的值
                    print(result, err)
                }
                //刷新页面
                self.navigationController?.popToRootViewController(animated: false)
            }
        }
        
        
        
//        
        
        

        
        
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
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        print(message.body)
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
        if(user_distribution_temp_work.count > 0){
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
        }else{
            string_work_data = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"
            string_weekend_data = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"
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
        
        
        
//        var output_distribution_string:String = "setView(['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00'],[" + new_string_work_data
//        output_distribution_string += "],[" + new_string_weekend_data + "])"
        
        
        var output_distribution_string:String = new_string_work_data + ";" + new_string_weekend_data
        return output_distribution_string
        
        
        
        
        
        
        
        
    }
    
    
//    func  getHeatmapDataNew() -> String{
//        var dietType = MMKV.default()?.string(forKey: "diet_type") ?? "8:16 diet"
//            dietType = "Your eating window"
//            
//        let firstDietTime = MMKV.default()?.string(forKey: "first_diet_time") ?? "8"
//        let isNowFlag = MMKV.default()?.int32(forKey: "is_now_flag")
//        let isNextFlag = MMKV.default()?.int32(forKey: "is_next_flag")
//
//        let isDrawDebug = MMKV.default()?.int32(forKey: "is_draw_debug")
//            
//        if isDrawDebug == -1 {
//            // Database code will be specific to your iOS database solution
//            // Fetch data here and populate the `timeList` array with fetched `user_time`
//            
//            var timeList = [String]()
//            // Replace the following with database fetching and parsing logic:
//            // let dbResults = fetchFromDB()
//            // for result in dbResults where result.photoType == "food" {
//            //     timeList.append(result.userTime)
//            // }
//            timeList.sort()
//            
//            // The following is iOS specific date formatting code equivalent to the provided Java code
//            let dateFormatter = DateFormatter()
//            dateFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
//            
//            let now = Date()
//            let timeString = dateFormatter.string(from: now)
//            
//            if timeList.isEmpty {
//                return "4,4,4,...,1,1,1" // Fixed values from the Java code
//            } else {
//                // You need to implement `getDataHeatmapType` with the equivalent Swift logic
//                let heatmapResult = getDataHeatmapType(timeList: timeList, dietType: dietType, firstDietTime: firstDietTime, isNowFlag: isNowFlag, isNextFlag: isNextFlag, timeString: timeString)
//                let temp = heatmapResult.components(separatedBy: "&")
//                let plotData = "[\(temp[0])]"
//                let xAxis = "[\(temp[1])]"
//                let timeLevel = temp[2]
//                
//                // Your logic related to `ss` goes here
//            }
//        } else {
//            let currentTimeInput = MMKV.default().int32(forKey: "current_flag")
//            let inputTimeList = timeListArray[isDrawDebug]
//            var timeList = [String]()
//            
//            if inputTimeList.contains(";") {
//                timeList.append(contentsOf: inputTimeList.components(separatedBy: ";"))
//            } else {
//                timeList.append(inputTimeList)
//            }
//            
//            // Adjust the last time based on `currentTimeInput`
//            let lastTime = timeList.last!
//            if let lastDateTime = dateFormatter.date(from: lastTime) {
//                var calendar = Calendar.current
//                calendar.timeZone = TimeZone.current
//                let updatedLastTime = calendar.date(byAdding: .hour, value: Int(currentTimeInput), to: lastDateTime)
//                let updatedLastTimeString = dateFormatter.string(from: updatedLastTime!)
//                // Update `last_time` with `updatedLastTimeString`
//                
//                // Further logic goes here
//            }
//        }
//        
//        // Default return if none of the conditions are met
//        return ""
//    }
    
    
    
    func getHeatmapDataNew() -> String{
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
            var first_time_redu = 0
            if(Int(temp_minute)! < 30){
                first_time_redu = (Int(temp_minute) ?? 0) * 60 + (Int(temp21[2]) ?? 0)
                temp_minute = "00"
            }else{
                first_time_redu = ((Int(temp_minute) ?? 0) - 30) * 60 + (Int(temp21[2]) ?? 0)
                temp_minute = "30"
            }
            time_redu = first_time_redu
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
            var time_level_new:String = ""
            for i in 0...(time_level.count-1){
                time_level_new +=  String(time_level[i]) + ","
            }
                        
            
            
            return time_level_new
        }
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
        
        fillButton1.frame = CGRectFlat(0, view.bounds.height - 50, view.bounds.width,50)

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
