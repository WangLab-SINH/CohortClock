//
//  SuggestionViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/8.
//  Copyright © 2021 xfg. All rights reserved.
//

//


import Foundation
import UIKit
import Photos
import Alamofire
import SQLite
import MMKV

class SuggestionViewController: UIViewController, UIScrollViewDelegate, UITextViewDelegate{
    
    
    
    
    
    private lazy var textView: QMUITextView = {
        let textView = QMUITextView()
        textView.delegate = self
        textView.placeholder = "说点什么吧。。。"
        textView.placeholderColor = UIColorPlaceholder
        textView.autoResizable = true
        textView.font = UIFont.systemFont(ofSize: 20)
        textView.textContainerInset = UIEdgeInsets(top: 10, left: 7, bottom: 10, right: 7)
        textView.maximumTextLength = 5000
        textView.layer.borderWidth = 2
        textView.layer.borderColor = UIColorSeparator.cgColor
        return textView
    }()
    
    
    private lazy var textView1: QMUITextView = {
        let textView = QMUITextView()
        textView.delegate = self
        textView.placeholder = "请输入您的邮箱以便及时接受反馈消息"
        textView.placeholderColor = UIColorPlaceholder
        textView.autoResizable = true
        textView.font = UIFont.systemFont(ofSize: 20)
        textView.textContainerInset = UIEdgeInsets(top: 10, left: 7, bottom: 10, right: 7)
        textView.maximumTextLength = 5000
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
        
        
        
        
        
//        switch1 = UISwitch()
//        switch1.isOn = true
        
      
        
        view.addSubview(textView)
        view.addSubview(fillButton1)
        view.addSubview(textView1)

//        }
    }
    
    func scrollViewDidScroll(scrollView: UIScrollView ) {
            
         }
        
         override func didReceiveMemoryWarning() {
             super .didReceiveMemoryWarning()
         }
    
    
   
    
    
    @objc func uploadInfo() {
        
        
        
//        AF.request("http://39.100.73.118/deeplearning_photo/ios_user_info1.php?androidid="+uuidName+"&weight="+current_weight+"&height="+current_height+"&sex="+current_sex+"&diabete="+current_diabetes+"&workdate="+current_workdate+"&disease="+current_disease+"").responseString {response in
//                debugPrint("Response: \(response)")
           
   //     }
      
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
       
        textView.frame = CGRectFlat(0, 0, view.bounds.width, view.bounds.height - 200)
        
        textView1.frame = CGRectFlat(0, view.bounds.height - 200, view.bounds.width, 50)
        
        fillButton1.frame = CGRectFlat((view.bounds.width - 200) / 2, view.bounds.height - 100, 200, 50)

    }

}



