//
//  WeightSleepController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/10/18.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
import UIKit
import DateTimePicker
import Alamofire


let ScreenWidth = UIScreen.main.bounds.width
// 屏幕高度
let ScreenHeight = UIScreen.main.bounds.height
var selectA = false
var selectB = false
var selectC = false
class WeightSleepController: UIViewController, DateTimePickerDelegate{
    
    struct WeightSleep: Encodable {
        let androidid: String
        let start_time: String
        let end_time: String
        let weight: String
        let upload_time: String
    }
    
    
    var current_weight_date: String = ""
    var current_weight: String = ""
    var current_start_time: String = ""
    var current_end_time: String = ""
    
    
//    lazy var lazyRulerView:DYScrollRulerView = { [unowned self] in
//        let unitStr     = "¥"
//        let rulerHeight = DYScrollRulerView.rulerViewHeight
//        print(rulerHeight)
//        var rulerView   = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: Int(ScreenHeight/5*0.5), width: Int(ScreenWidth)-20, height: rulerHeight()), tminValue: 0, tmaxValue: 1000, tstep: 10, tunit: unitStr, tNum:10, viewcontroller:self)
//        rulerView.setDefaultValueAndAnimated(defaultValue: 500, animated: true)
//        rulerView.bgColor       = UIColor.cyan
//        rulerView.triangleColor = UIColor.red
//        rulerView.delegate      = self
//        rulerView.scrollByHand  = true
//       return rulerView
//    }()
//
//    lazy var lazyTimeRullerView:DYScrollRulerView = { [unowned self] in
//        let unitStr = ""
//        let rulersHeight = DYScrollRulerView.rulerViewHeight
//        print(rulersHeight)
//
//        var timerView = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: Int(ScreenHeight/5*2), width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 23, tstep: 0.2, tunit: unitStr, tNum: 5, viewcontroller:self)
//        timerView.setDefaultValueAndAnimated(defaultValue: 2, animated: true)
//        timerView.bgColor       = UIColor.orange
//        timerView.triangleColor = UIColor.blue
//        timerView.delegate      = self
//        timerView.scrollByHand  = true
//        return timerView
//    }()
    
    lazy var lazyNoneZeroRullerView:DYScrollRulerView = { [unowned self] in
        let unitStr = ""
        let rulersHeight = DYScrollRulerView.rulerViewHeight
        print(rulersHeight)
        
        var zeroRulerView = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 90, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 150, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
        zeroRulerView.setDefaultValueAndAnimated(defaultValue: 61.7, animated: true)
//        zeroRulerView.bgColor       = UIColor.orange
//        zeroRulerView.triangleColor = UIColor.white
        zeroRulerView.delegate      = self
        zeroRulerView.scrollByHand  = true
        return zeroRulerView
        }()
    
    
    
    
    
    
    

    
    

    private lazy var normalButton: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("Select date", for: .normal)
        normalButton.addTarget(self, action:#selector(showDatePicker), for: .touchUpInside)
        return normalButton
    }()
    
    
    private lazy var normalButton1: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("Choose your bedtime", for: .normal)
        normalButton.addTarget(self, action:#selector(showTimePicker1), for: .touchUpInside)
        return normalButton
    }()
    
    private lazy var normalButton2: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("Choose your wake up time", for: .normal)
        normalButton.addTarget(self, action:#selector(showTimePicker2), for: .touchUpInside)
        return normalButton
    }()
    
    
    
    private lazy var normalButtonOK: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("Save", for: .normal)
        normalButton.addTarget(self, action:#selector(upload), for: .touchUpInside)
        return normalButton
    }()
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // 设置导航栏
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        // 设置导航栏
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return.default
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = "更新"

        self.setNeedsStatusBarAppearanceUpdate()

        view.addSubview(normalButton)
        view.addSubview(normalButton1)
        view.addSubview(normalButton2)
        view.addSubview(normalButtonOK)
     
        
//        self.view.addSubview(lazyRulerView)
//        self.view.addSubview(lazyTimeRullerView)
        self.view.addSubview(lazyNoneZeroRullerView)
      
    }
    
    

    @objc func showDatePicker(){
        selectA = true
        selectB = false
        selectC = false
        let min = Date().addingTimeInterval(-60 * 60 * 24 * 30)
        let max = Date().addingTimeInterval(0)
        //let picker = DateTimePicker.create(minimumDate: min, maximumDate: max)
        let picker = DateTimePicker.create(minimumDate: min, maximumDate: max)
        picker.isDatePickerOnly = true
        picker.frame = CGRect(x: 0, y: 100, width: picker.frame.size.width, height: picker.frame.size.height)
        picker.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
        picker.includesMonth = true
        picker.includesSecond = true
        picker.highlightColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.doneButtonTitle = "确认"
        picker.doneBackgroundColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.customFontSetting = DateTimePicker.CustomFontSetting(selectedDateLabelFont: .boldSystemFont(ofSize: 20))
//        picker.completionHandler = { date in
//            let formatter = DateFormatter()
//            formatter.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
//            self.title = formatter.string(from: date)
//        }
        picker.delegate = self
        picker.show()
    }
    
    
    
    
    @objc func showTimePicker1(){
        selectA = false
        selectB = true
        selectC = false
        let min = Date().addingTimeInterval(-60 * 60 * 24 * 4)
        let max = Date().addingTimeInterval(60 * 60 * 24 * 4)
        //let picker = DateTimePicker.create(minimumDate: min, maximumDate: max)
        let picker = DateTimePicker.create()
        picker.frame = CGRect(x: 0, y: 100, width: picker.frame.size.width, height: picker.frame.size.height)
        picker.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
        picker.includesMonth = true
        picker.includesSecond = true
        picker.highlightColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.doneButtonTitle = "确认"
        picker.doneBackgroundColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.customFontSetting = DateTimePicker.CustomFontSetting(selectedDateLabelFont: .boldSystemFont(ofSize: 20))
//        picker.completionHandler = { date in
//            let formatter = DateFormatter()
//            formatter.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
//            self.title = formatter.string(from: date)
//        }
        picker.delegate = self
        picker.show()
    }
    
    
    
    @objc func showTimePicker2(){
        selectA = false
        selectB = false
        selectC = true
        let min = Date().addingTimeInterval(-60 * 60 * 24 * 4)
        let max = Date().addingTimeInterval(60 * 60 * 24 * 4)
        //let picker = DateTimePicker.create(minimumDate: min, maximumDate: max)
        let picker = DateTimePicker.create()

        picker.frame = CGRect(x: 0, y: 100, width: picker.frame.size.width, height: picker.frame.size.height)
        picker.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
        picker.includesMonth = true
        picker.includesSecond = true
        picker.highlightColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.doneButtonTitle = "确认"
        picker.doneBackgroundColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.customFontSetting = DateTimePicker.CustomFontSetting(selectedDateLabelFont: .boldSystemFont(ofSize: 20))
//        picker.completionHandler = { date in
//            let formatter = DateFormatter()
//            formatter.dateFormat = "hh:mm:ss aa dd/MM/YYYY"
//            self.title = formatter.string(from: date)
//        }
        picker.delegate = self
        picker.show()
    }
    
    
    
    
    @objc func upload(){
        
        
        
        if(current_weight_date != "" || current_start_time != "" || current_end_time != ""){
            
            
            var input_current_weight_date: String = current_weight_date
            var input_current_start_time: String = current_start_time
            var input_current_end_time: String = current_end_time
            
            let temp_weight_date:[Substring] = input_current_weight_date.split(separator: "：")
            if(temp_weight_date.count > 1){
                let temp_weight_date1 = String(temp_weight_date[1])
                let timeFormatter = DateFormatter()
                //日期显示格式，可按自己需求显示
                let date = Date()
                timeFormatter.dateFormat = "HH:mm:ss"
                let strNowTime = timeFormatter.string(from: date) as String
                
                input_current_weight_date = temp_weight_date1 + " " + strNowTime
            }
            
            
            let temp_start_date:[Substring] = input_current_start_time.split(separator: "：")
            if(temp_start_date.count > 1){
                input_current_start_time = String(temp_start_date[1])
            }
            
            
            let temp_end_date:[Substring] = input_current_end_time.split(separator: "：")
            if(temp_end_date.count > 1){
                input_current_end_time = String(temp_end_date[1])
            }
            
            
            
   
            
            
            
            
            
            
            
            let uuid = UIDevice.current.identifierForVendor?.uuidString
            let uuidName: String = uuid ?? "default value"
            var database: Database!
            database = Database()
            database.tableLampCreateWeight()
            database.tableLampInsertItemWeight(user_name: uuidName, weight: current_weight, start_time: input_current_start_time, end_time: input_current_end_time, upload_time: input_current_weight_date)
            
            
//            AF.request("http://39.100.73.118/deeplearning_photo/ios_weight_sleep.php?androidid="+uuidName+"&weight="+current_weight+"&start_time="+input_current_start_time+"&end_time="+input_current_end_time+"&upload_time="+input_current_weight_date+"").responseString {response in
//                    debugPrint("Response: \(response)")
//            }
            
//            AF.request("http://39.100.73.118/deeplearning_photo/ios_weight_sleep.php?androidid="+uuidName+"&weight="+current_weight+"&start_time="+input_current_start_time+"&end_time="+input_current_end_time+"&upload_time="+input_current_weight_date+"").responseString {response in
//                    debugPrint("Response: \(response)")
//
//            }
            
//            AF.request("http://39.100.73.118/deeplearning_photo/ios_weight_sleep.php?androidid=a6&weight=&a&end_time=a&upload_time=a", method: .post).responseString {response in
//                    debugPrint("Response: \(response)")
//               
//            }
            let login = WeightSleep(androidid: uuidName, start_time: input_current_start_time, end_time: input_current_end_time, weight: current_weight, upload_time: input_current_weight_date)
            AF.request("http://39.100.73.118/deeplearning_photo/ios_weight_sleep.php",
                       method: .post,
                       parameters: login,
                       encoder: URLEncodedFormParameterEncoder.default).responseString { response in
                debugPrint(response)
                        if(response.value == "yes"){
                            QMUITips.showSucceed(text: "记录成功")
                        }else{
                            QMUITips.showError(text: "记录失败")
                       }
                    }
            
            
            
        }
        
      
    }
    
    
    
    
    func dateTimePicker(_ picker: DateTimePicker, didSelectDate: Date) {
        let df = DateFormatter()
        df.dateStyle = .none
        df.timeStyle = .long
        df.dateFormat = "yyyy-MM-dd"
        if(selectA == true){
            let dataInputString:String = "您选择了：" + df.string(from: picker.selectedDate)
            current_weight_date = dataInputString
            normalButton.setTitle(dataInputString, for: .normal)
        }else if(selectB == true){
            df.dateFormat = "yyyy-MM-dd HH:mm:ss"
            let dataInputString:String = "您选择了：" + df.string(from: picker.selectedDate)
            current_start_time = dataInputString
            normalButton1.setTitle(dataInputString, for: .normal)
        }else{
            df.dateFormat = "yyyy-MM-dd HH:mm:ss"
            let dataInputString:String = "您选择了：" + df.string(from: picker.selectedDate)
            current_end_time = dataInputString
            normalButton2.setTitle(dataInputString, for: .normal)
        }
        
    }
    
    
    
   
    
    
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 400, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
        let buttonMinXOK = view.bounds.width.center(100)

        


        normalButton.frame = CGRectFlat(buttonMinX, 50, buttonSize.width, buttonSize.height)
        normalButton1.frame = CGRectFlat(buttonMinX, 250, buttonSize.width, buttonSize.height)
        normalButton2.frame = CGRectFlat(buttonMinX, 320, buttonSize.width, buttonSize.height)
        normalButtonOK.frame = CGRectFlat(buttonMinXOK, 390, 100, buttonSize.height)




    
    }
}
extension WeightSleepController:DYScrollRulerDelegate {
    func dyScrollRulerViewValueChange(rulerView: DYScrollRulerView, value: Float) {
        print("滑动的值-------》"+"\(value)")
        current_weight = String(value)
    }
}

