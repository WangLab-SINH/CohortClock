//
//  BaseInfo.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/5.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import Photos
import Alamofire
import SQLite
import MMKV
import DateTimePicker

class BaseInfoViewController: UIViewController, UIScrollViewDelegate, UITextViewDelegate, DateTimePickerDelegate{
    
    var secondText: String = ""
    var current_start_time: String = ""
    var current_end_time: String = ""
    
    init(text: String) {
        secondText = text
        print(secondText)
        super.init(nibName: nil, bundle: nil)
        
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    
    
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)

    let items1 = ["Male", "Female"]
    let items2 = ["Yes", "No"]
    let items3 = ["Yes", "No"]
    let items4 = ["Yes", "No"]
    let items5 = ["Day", "Night"]
    var label1: UILabel!
    
    var segments1: UISegmentedControl!
    var switch1: UISwitch!
    
    var sex_textview: UITextView!
    var weight_textview: UITextView!
    var height_textview: UITextView!
    var textview1: UITextView!
    var textview2: UITextView!
    var textview3: UITextView!
    var textview4: UITextView!
    
    var segments2: UISegmentedControl!
    var segments3: UISegmentedControl!
    var segments4: UISegmentedControl!
    var segments5: UISegmentedControl!
    
    lazy var lazyNoneZeroRullerView:DYScrollRulerView = { [unowned self] in
        
        
        if(secondText=="Blood Pressure"){
            let unitStr = "mmHg"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

            var weight_default_value:Float = 80
            weight_default_value = mmkv?.float(forKey: "dia_pre", defaultValue: 80) ?? 80
           
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 200, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }else if(secondText=="Heart Rate"){
            let unitStr = "BPM"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

            var weight_default_value:Float = 60
            weight_default_value = mmkv?.float(forKey: "rate", defaultValue: 60) ?? 60
           
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 200, tstep: 1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }else if(secondText=="Waist Circumference"){
            let unitStr = "cm"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

            var weight_default_value:Float = 80
            weight_default_value = mmkv?.float(forKey: "rate", defaultValue: 80) ?? 80
           
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 200, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }else if(secondText=="Height"){
            let unitStr = "cm"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

            var weight_default_value:Float = 167.1
            weight_default_value = mmkv?.float(forKey: "height", defaultValue: 167.1) ?? 167.1
           
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 220, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }else if(secondText=="Temperature"){
            let unitStr = "degree"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

            var weight_default_value:Float = 36.5
            weight_default_value = mmkv?.float(forKey: "degree", defaultValue: 36.5) ?? 36.5
           
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 30, tmaxValue: 42, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }
        else{
            let unitStr = ""
            let rulersHeight = DYScrollRulerView.rulerViewHeight
           
            
            var sex:String = mmkv?.string(forKey: "sex") ?? "nil"
            
            var weight_default_value:Float = 57.3
            if(sex == "Male"){
                weight_default_value = mmkv?.float(forKey: "weight") ?? 66.2
            }else{
                weight_default_value = mmkv?.float(forKey: "weight") ?? 57.3
            }
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: 130, width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 150, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }
        

        }()
    
    
    lazy var lazyNoneZeroRullerView2:DYScrollRulerView = { [unowned self] in
        
        if(secondText=="Blood Pressure"){
            let unitStr = "mmHg"
            let rulersHeight = DYScrollRulerView.rulerViewHeight

           
            
            var weight_default_value:Float = 100
            weight_default_value = mmkv?.float(forKey: "sys_pre", defaultValue: 100) ?? 100.0
          
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: Int(CGFloat(160+DYScrollRulerView.rulerViewHeight())), width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 0, tmaxValue: 200, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }else{
            let unitStr = ""
            let rulersHeight = DYScrollRulerView.rulerViewHeight
           
            
            var sex:String = mmkv?.string(forKey: "sex") ?? "nil"
            
            var weight_default_value:Float = 57.3
            if(sex == "Male"){
                weight_default_value = mmkv?.float(forKey: "height") ?? 167.1
            }else{
                weight_default_value = mmkv?.float(forKey: "height") ?? 155.8
            }
            
            var zeroRulerView1 = DYScrollRulerView.init(frame: CGRect.init(x: 5, y: Int(CGFloat(160+DYScrollRulerView.rulerViewHeight())), width: Int(ScreenWidth)-20, height: rulersHeight()), tminValue: 50, tmaxValue: 230, tstep: 0.1, tunit: unitStr, tNum: 10, viewcontroller:self)
            zeroRulerView1.setDefaultValueAndAnimated(defaultValue: weight_default_value, animated: true)
    //        zeroRulerView.bgColor       = UIColor.orange
    //        zeroRulerView.triangleColor = UIColor.white
            zeroRulerView1.delegate      = self
            zeroRulerView1.scrollByHand  = true
            return zeroRulerView1
        }
        
        
        }()
    
    private lazy var textView: QMUITextView = {
        let textView = QMUITextView()
        textView.delegate = self
        textView.placeholder = "Please enter the medication you have taken"
        textView.placeholderColor = UIColorPlaceholder
        textView.autoResizable = true
        textView.textContainerInset = UIEdgeInsets(top: 10, left: 7, bottom: 10, right: 7)
        textView.maximumTextLength = 50
        textView.layer.borderWidth = 2
        textView.layer.borderColor = UIColorSeparator.cgColor
        return textView
    }()
    
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Save", for: .normal)
        fillButton.addTarget(self, action:#selector(uploadInfo), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var fillButton2: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Cancel", for: .normal)
        fillButton.addTarget(self, action:#selector(cancelInfo), for: .touchUpInside)
        return fillButton
    }()
    
    
    
    private lazy var normalButton1: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("入睡时间选择", for: .normal)
        normalButton.addTarget(self, action:#selector(showTimePicker1), for: .touchUpInside)
        return normalButton
    }()
    
    private lazy var normalButton2: QMUIButton = {
        let normalButton = QDUIHelper.generateDarkFilledButton()
        normalButton.setTitle("起床时间选择", for: .normal)
        normalButton.addTarget(self, action:#selector(showTimePicker2), for: .touchUpInside)
        return normalButton
    }()
    
    
    
    var scrollView: UIScrollView!
    
    
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    
    @objc private func dismissKeyboard() {
        view.endEditing(true)
    }


    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.white
        self.hideKeyboardWhenTappedAround()
        
        scrollView = UIScrollView()
                 //设置代理
                 scrollView.delegate = self
                 scrollView.frame = self.view.bounds
                 
        scrollView.contentSize = CGSize(width: 600,height: 20000);
                 
                 self .view.addSubview(scrollView)
        
        
        
//        let scrollView = UIScrollView(frame: CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height * 1.5 ))
//        scrollView.contentSize = .zero
//        scrollView.delegate = self
//
//        scrollView.isPagingEnabled = true
//        scrollView.showsHorizontalScrollIndicator = false
//        scrollView.showsVerticalScrollIndicator = false
//        scrollView.scrollsToTop = false

        var textView3 = UITextView(frame:CGRect(x:0,y:300,width:view.bounds.width,height:200))


        view.backgroundColor = UIColor.white
        
        label1 = UILabel()

        segments1 = UISegmentedControl(items: items1)
        segments1.addTarget(self, action: #selector(updateView), for: .valueChanged)
        
        segments2 = UISegmentedControl(items: items2)
        segments2.addTarget(self, action: #selector(updateView1), for: .valueChanged)
        
        segments3 = UISegmentedControl(items: items3)
        segments3.addTarget(self, action: #selector(updateView2), for: .valueChanged)
        
        segments4 = UISegmentedControl(items: items4)
        segments4.addTarget(self, action: #selector(updateView3), for: .valueChanged)
        
        segments5 = UISegmentedControl(items: items5)
        segments5.addTarget(self, action: #selector(updateView4), for: .valueChanged)
        
        
        
        var sex:String = mmkv?.string(forKey: "sex") ?? "nil"
        if(sex == "Male"){
            segments1.selectedSegmentIndex = 0
        }else{
            segments1.selectedSegmentIndex = 1
        }
        
        var flag1:String = mmkv?.string(forKey: "diabetes") ?? "nil"
        if(flag1 == "Yes"){
            segments2.selectedSegmentIndex = 0
        }else{
            segments2.selectedSegmentIndex = 1
        }
        
        var flag2:String = mmkv?.string(forKey: "bloodpressure") ?? "nil"
        if(flag2 == "Yes"){
            segments3.selectedSegmentIndex = 0
        }else{
            segments3.selectedSegmentIndex = 1
        }
        
        
        var flag3:String = mmkv?.string(forKey: "obesity") ?? "nil"
        if(flag3 == "Yes"){
            segments4.selectedSegmentIndex = 0
        }else{
            segments4.selectedSegmentIndex = 1
        }
        
        
        
        var flag4:String = mmkv?.string(forKey: "worktime") ?? "nil"
        if(flag4 == "Night"){
            segments5.selectedSegmentIndex = 1
        }else{
            segments5.selectedSegmentIndex = 0
        }
        
        
        
//        switch1 = UISwitch()
//        switch1.isOn = true
        
        sex_textview = UITextView()
        sex_textview.text = "Gender："
        sex_textview.font = UIFont.systemFont(ofSize: 20)
        
        
        weight_textview = UITextView()
        weight_textview.text = "Weight："
        weight_textview.font = UIFont.systemFont(ofSize: 20)
        
        height_textview = UITextView()
        height_textview.text = "Height："
        height_textview.font = UIFont.systemFont(ofSize: 20)
        
        textview1 = UITextView()
        textview1.text = "Diabetes："
        textview1.font = UIFont.systemFont(ofSize: 20)
        
        textview2 = UITextView()
        textview2.text = "Hypertension："
        textview2.font = UIFont.systemFont(ofSize: 20)
        
        textview3 = UITextView()
        textview3.text = "Obesity："
        textview3.font = UIFont.systemFont(ofSize: 20)
        
        textview4 = UITextView()
        textview4.text = "Worktime："
        textview4.font = UIFont.systemFont(ofSize: 20)
        
        
//        view.addSubview(scrollView)
        
        if(secondText=="Blood Pressure"){
            view.addSubview(lazyNoneZeroRullerView)
            view.addSubview(lazyNoneZeroRullerView2)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Heart Rate"){
            
            view.addSubview(lazyNoneZeroRullerView)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Waist Circumference"){
            
            view.addSubview(lazyNoneZeroRullerView)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Exercise Duration"){
            
            view.addSubview(normalButton1)
            view.addSubview(normalButton2)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Height"){
            
            view.addSubview(lazyNoneZeroRullerView)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Temperature"){
            
            view.addSubview(lazyNoneZeroRullerView)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else if(secondText=="Medicine"){
            
            view.addSubview(textView)

            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }else{
            view.addSubview(segments1)
            //view.addSubview(switch1)
            view.addSubview(lazyNoneZeroRullerView)
            view.addSubview(sex_textview)
            view.addSubview(weight_textview)
            view.addSubview(height_textview)
            view.addSubview(textview1)
            view.addSubview(textview2)
            view.addSubview(textview3)
            view.addSubview(textview4)
            view.addSubview(lazyNoneZeroRullerView2)
            
            view.addSubview(segments2)
            view.addSubview(segments3)
            view.addSubview(segments4)
            view.addSubview(segments5)
            
            view.addSubview(textView)
            view.addSubview(fillButton1)
            view.addSubview(fillButton2)
        }
        
       


//        }
    }
    
    func scrollViewDidScroll(scrollView: UIScrollView ) {
            
         }
        
         override func didReceiveMemoryWarning() {
             super .didReceiveMemoryWarning()
         }
    
    
    @objc func updateView() {
        let idx = segments1.selectedSegmentIndex
        if(idx == 0){
            mmkv?.set("男", forKey: "sex")
        }else{
            mmkv?.set("女", forKey: "sex")
        }
        let current = (idx == UISegmentedControl.noSegment) ? "none" : items1[idx]
            label1.text = "Current segment: \(current)"
        }
    
    @objc func updateView1() {
        let idx = segments2.selectedSegmentIndex
        if(idx == 0){
            mmkv?.set("是", forKey: "diabetes")
        }else{
            mmkv?.set("否", forKey: "diabetes")
        }
        
    }
    
    
    @objc func updateView2() {
        let idx = segments3.selectedSegmentIndex
        if(idx == 0){
            mmkv?.set("是", forKey: "bloodpressure")
        }else{
            mmkv?.set("否", forKey: "bloodpressure")
        }
        
    }
    
    
    
    @objc func updateView3() {
        let idx = segments4.selectedSegmentIndex
        if(idx == 0){
            mmkv?.set("是", forKey: "obesity")
        }else{
            mmkv?.set("否", forKey: "obesity")
        }
        
    }
    
    
    
    @objc func updateView4() {
        let idx = segments5.selectedSegmentIndex
        if(idx == 0){
            mmkv?.set("白天", forKey: "worktime")
        }else{
            mmkv?.set("晚上", forKey: "worktime")
        }
      
    }
    
    
    @objc func uploadInfo() {
        let uuid = UIDevice.current.identifierForVendor?.uuidString
        let uuidName: String = uuid ?? "default value"
        var current_sex = "null"
        var current_weight = "null"
        var current_height = "null"
        var current_diabetes = "null"
        var current_workdate = "null"
        var current_disease = "null"
        
        
        current_height = String(mmkv?.float(forKey: "height") ?? 0)
        current_weight = String(mmkv?.float(forKey: "height") ?? 0)
        if(current_height == "0"){
            current_height = "null"
        }
        if(current_weight == "0"){
            current_weight = "null"
        }
        if(segments1.selectedSegmentIndex == 0){
            current_sex = "male"
        }
        else{
            current_sex = "female"
        }
        
        if(segments2.selectedSegmentIndex == 0){
            current_diabetes = "yes"
        }
        else{
            current_diabetes = "no"
        }
        
        if(segments4.selectedSegmentIndex == 0){
            current_workdate = "day"
        }
        else{
            current_workdate = "night"
        }
        current_disease = textView.text
        
        
        AF.request("http://39.100.73.118/deeplearning_photo/ios_user_info1.php?androidid="+uuidName+"&weight="+current_weight+"&height="+current_height+"&sex="+current_sex+"&diabete="+current_diabetes+"&workdate="+current_workdate+"&disease="+current_disease+"").responseString {response in
                debugPrint("Response: \(response)")
           
        }
        navigationController?.popViewController(animated: true)
      
    }
    
    
    @objc func cancelInfo() {
        navigationController?.popViewController(animated: true)
      
    }
    
    func dateTimePicker(_ picker: DateTimePicker, didSelectDate: Date) {
        let df = DateFormatter()
        df.dateStyle = .none
        df.timeStyle = .long
        df.dateFormat = "yyyy-MM-dd"
        if(selectB == true){
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

    


    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

//        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
//        let buttonSpacingHeight = QDButtonSpacingHeight
//        let buttonSize = CGSize(width: 260, height: 40)
//        let buttonMinX = view.bounds.width.center(buttonSize.width)
//        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
//
//
//        textView1.frame = CGRectFlat(0, 0, view.bounds.width,200)
//
//        textView2.frame = CGRectFlat(0, 100, view.bounds.width,200)
        sex_textview.frame = CGRectFlat(0, 0, view.bounds.width, 50)
        segments1.frame = CGRectFlat(0, 50, 100,50)
        weight_textview.frame = CGRectFlat(0,100,view.bounds.width, 50)
        height_textview.frame = CGRectFlat(0, CGFloat(130+DYScrollRulerView.rulerViewHeight()), view.bounds.width, 50)
//        switch1.frame = CGRectFlat(0, 200, 50,50)
        textview1.frame = CGRectFlat(0, CGFloat(160+2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width / 2, 50)
        segments2.frame = CGRectFlat(0, CGFloat(210+2 * DYScrollRulerView.rulerViewHeight()), 100, 50)
        
        textview2.frame = CGRectFlat(view.bounds.width / 2, CGFloat(160+2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width, 50)
        segments3.frame = CGRectFlat(view.bounds.width / 2, CGFloat(210+2 * DYScrollRulerView.rulerViewHeight()), 100, 50)
        
        textview3.frame = CGRectFlat(0, CGFloat(260+2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width / 2, 50)
        segments4.frame = CGRectFlat(0, CGFloat(310+2 * DYScrollRulerView.rulerViewHeight()), 100, 50)
        
        textview4.frame = CGRectFlat(view.bounds.width / 2, CGFloat(260+2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width, 50)
        segments5.frame = CGRectFlat(view.bounds.width / 2, CGFloat(310+2 * DYScrollRulerView.rulerViewHeight()), 100, 50)
        
        textView.frame = CGRectFlat(0, CGFloat(2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width, 100)
        let buttonSize = CGSize(width: 400, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        normalButton1.frame = CGRectFlat(buttonMinX, 250, buttonSize.width, buttonSize.height)
        normalButton2.frame = CGRectFlat(buttonMinX, 320, buttonSize.width, buttonSize.height)
        fillButton1.frame = CGRectFlat((view.bounds.width / 2)+25, CGFloat(430+2 * DYScrollRulerView.rulerViewHeight()), 150, 50)
        
        fillButton2.frame = CGRectFlat(50, CGFloat(430+2 * DYScrollRulerView.rulerViewHeight()), 150, 50)

    }

}


extension BaseInfoViewController:DYScrollRulerDelegate {
    func dyScrollRulerViewValueChange(rulerView: DYScrollRulerView, value: Float) {
        //print("滑动的值-------》"+"\(value)")
        if(rulerView.maxValue == 150){
            mmkv?.set(value, forKey: "weight")
        }else{
            mmkv?.set(value, forKey: "height")
        }
        
        
    }
}
