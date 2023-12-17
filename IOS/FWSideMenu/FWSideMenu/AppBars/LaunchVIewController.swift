//
//  LaunchVIewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/29.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import LXFitManager
import ZLPhotoBrowser
import Photos
import Alamofire
import SQLite
import MMKV
import Foundation
import UIKit
import DateTimePicker
import Alamofire



class LaunchViewController: UIViewController, UIScrollViewDelegate, UITextViewDelegate{
    
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)

    let items1 = ["男", "女"]
    let items2 = ["是", "否"]
    let items3 = ["是", "否"]
    let items4 = ["是", "否"]
    let items5 = ["白天", "晚上"]
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
        let unitStr = ""
        let rulersHeight = DYScrollRulerView.rulerViewHeight
        print(rulersHeight)
        
        var sex:String = mmkv?.string(forKey: "sex") ?? "nil"
        
        var weight_default_value:Float = 57.3
        if(sex == "男"){
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
        }()
    
    
    lazy var lazyNoneZeroRullerView2:DYScrollRulerView = { [unowned self] in
        let unitStr = ""
        let rulersHeight = DYScrollRulerView.rulerViewHeight
        print(rulersHeight)
        
        var sex:String = mmkv?.string(forKey: "sex") ?? "nil"
        
        var weight_default_value:Float = 57.3
        if(sex == "男"){
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
        }()
    
    private lazy var textView: QMUITextView = {
        let textView = QMUITextView()
        textView.delegate = self
        textView.placeholder = "请输入其他疾病"
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
        fillButton.setTitle("确认", for: .normal)
        fillButton.addTarget(self, action:#selector(uploadInfo), for: .touchUpInside)
        return fillButton
    }()
    
    
    var scrollView: UIScrollView!

    override func viewDidLoad() {
        super.viewDidLoad()
           self.view.backgroundColor = UIColor.white
        
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
        if(sex == "男"){
            segments1.selectedSegmentIndex = 0
        }else{
            segments1.selectedSegmentIndex = 1
        }
        
        var flag1:String = mmkv?.string(forKey: "diabetes") ?? "nil"
        if(flag1 == "是"){
            segments2.selectedSegmentIndex = 0
        }else{
            segments2.selectedSegmentIndex = 1
        }
        
        var flag2:String = mmkv?.string(forKey: "bloodpressure") ?? "nil"
        if(flag2 == "是"){
            segments3.selectedSegmentIndex = 0
        }else{
            segments3.selectedSegmentIndex = 1
        }
        
        
        var flag3:String = mmkv?.string(forKey: "obesity") ?? "nil"
        if(flag3 == "是"){
            segments4.selectedSegmentIndex = 0
        }else{
            segments4.selectedSegmentIndex = 1
        }
        
        
        
        var flag4:String = mmkv?.string(forKey: "worktime") ?? "nil"
        if(flag4 == "晚上"){
            segments5.selectedSegmentIndex = 1
        }else{
            segments5.selectedSegmentIndex = 0
        }
        
        
        
//        switch1 = UISwitch()
//        switch1.isOn = true
        
        sex_textview = UITextView()
        sex_textview.text = "性别："
        sex_textview.font = UIFont.systemFont(ofSize: 20)
        
        
        weight_textview = UITextView()
        weight_textview.text = "体重："
        weight_textview.font = UIFont.systemFont(ofSize: 20)
        
        height_textview = UITextView()
        height_textview.text = "身高："
        height_textview.font = UIFont.systemFont(ofSize: 20)
        
        textview1 = UITextView()
        textview1.text = "糖尿病："
        textview1.font = UIFont.systemFont(ofSize: 20)
        
        textview2 = UITextView()
        textview2.text = "高血压："
        textview2.font = UIFont.systemFont(ofSize: 20)
        
        textview3 = UITextView()
        textview3.text = "肥胖："
        textview3.font = UIFont.systemFont(ofSize: 20)
        
        textview4 = UITextView()
        textview4.text = "工作时间："
        textview4.font = UIFont.systemFont(ofSize: 20)
        
        
//        view.addSubview(scrollView)
        view.addSubview(segments1)
        //view.addSubview(switch1)
        view.addSubview(lazyNoneZeroRullerView)
        view.addSubview(sex_textview)
        view.addSubview(weight_textview)
        view.addSubview(height_textview)
//        view.addSubview(textview1)
//        view.addSubview(textview2)
//        view.addSubview(textview3)
//        view.addSubview(textview4)
        view.addSubview(lazyNoneZeroRullerView2)
//
//        view.addSubview(segments2)
//        view.addSubview(segments3)
//        view.addSubview(segments4)
//        view.addSubview(segments5)
//
//        view.addSubview(textView)
        view.addSubview(fillButton1)


//        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
//        self.navigationItem.setHidesBackButton(true, animated: true)
//        self.navigationItem.hidesBackButton = true
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
        mmkv?.set(true, forKey: "first_time")
        var viewController: UIViewController
        viewController = TestHtmlCode()
        navigationController?.popViewController(animated: true)
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
        
        textView.frame = CGRectFlat(0, CGFloat(360+2 * DYScrollRulerView.rulerViewHeight()), view.bounds.width, 50)
        
        fillButton1.frame = CGRectFlat((view.bounds.width - 200) / 2, CGFloat(430+2 * DYScrollRulerView.rulerViewHeight()), 200, 50)

    }

}


extension LaunchViewController:DYScrollRulerDelegate {
    func dyScrollRulerViewValueChange(rulerView: DYScrollRulerView, value: Float) {
        //print("滑动的值-------》"+"\(value)")
        if(rulerView.maxValue == 150){
            mmkv?.set(value, forKey: "weight")
        }else{
            mmkv?.set(value, forKey: "height")
        }
        
        
    }
}
