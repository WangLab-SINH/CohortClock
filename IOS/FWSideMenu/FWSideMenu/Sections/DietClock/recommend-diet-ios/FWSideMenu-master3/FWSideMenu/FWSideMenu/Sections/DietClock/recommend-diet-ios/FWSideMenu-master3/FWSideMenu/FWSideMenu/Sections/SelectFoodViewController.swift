//
//  SelectFoodViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/27.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import AYPopupPickerView
import Alamofire
class SelectFoodViewController: UIViewController {
    
    struct WordFood: Encodable {
        let androidid: String
        let food_type: String
        let time: String
    }
    
    var food: String = ""
    var time: String = ""
    
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillColor: UIColor.orange, titleTextColor: .white)
        fillButton.titleLabel?.font = UIFontMake(20)
        fillButton.setTitle("食物种类选择", for: .normal)
        fillButton.addTarget(self, action:#selector(openDialog), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var fillButton2: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .green)
        fillButton.titleLabel?.font = UIFontMake(20)
        fillButton.setTitle("选择当天时间", for: .normal)
        fillButton.addTarget(self, action:#selector(openDialog1), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var fillButton3: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(20)
        fillButton.setTitle("上传", for: .normal)
        fillButton.addTarget(self, action:#selector(openDialog2), for: .touchUpInside)
        return fillButton
    }()
    
    let multipleAssociatedData: [[[String: [String]?]]] = [// 数组
        
        [   ["低热量食物": [" "]],//字典
            ["中热量食物": ["  "]],
            ["高热量食物": ["   "]]
            
        ],// 数组
        
                [   [" ": ["炒卷心菜","烧茄子","水煮青菜","豆腐汤","味增汤","烧土豆","麻婆豆腐","豆腐","拌凉菜","炒藕片","土豆泥","蔬菜沙拉","蔬菜汤","咸菜","苦瓜炒蛋","白菜炒木耳","冬瓜汤","土豆萝卜汤","炒土豆丝","什锦炒蔬","鱼香茄子"]],
                    ["  ": ["米饭","鳗鱼","寿司","韩式拌饭","面条","豚骨拉面","煎鱼片","生鱼片","三文鱼刺身","荷包蛋","青椒肉丝","意大利面","肥牛饭","饭团","炒饭","韩式部队锅","海鲜砂锅","炒面","海鲜饭","咖喱饭","包菜炒肉","河粉","白米粥","饺子","黑米粥","地三鲜","包子","米粉","海鲜面","酸辣鱼蔬菜烩","馄饨汤","肠粉","什锦饭","宫爆鸡丁"]],
                    ["   ": ["炸鱼","面包片","牛角包","热狗","汉堡","披萨","芝士焗饭","煎饺","炸鸡排","炸鸡块","炸猪排","猪肉饼","烤牛排","炸肉串","春卷","红烧肉","菠萝古老肉","烧鸡","烤面包","炸薯条","巧克力蛋糕","布丁","奶油蛋糕","冰淇淋","锅包肉","羊肉串","月饼","蛋挞","猪肉沙嗲","红烧猪蹄","香酥鸡翅","炸油条"]]
        
                ]
                     
    ]
    
    
    
    
//    let multipleAssociatedData: [[[String: [String]?]]] = [// 数组
//
//        [   ["低热量食物": ["陆地", "空中", "水上"]],//字典
//            ["中热量食物": ["健康食品", "垃圾食品"]],
//            ["高热量食物": ["益智游戏", "角色游戏"]]
//
//        ],// 数组
//
//        [   ["陆地": ["公交车", "小轿车", "自行车"]],
//            ["空中": ["飞机"]],
//            ["水上": ["轮船"]],
//            ["健康食品": ["蔬菜", "水果"]],
//            ["垃圾食品": ["辣条", "不健康小吃"]],
//            ["益智游戏": ["消消乐", "消灭星星"]],
//            ["角色游戏": ["lol", "cf"]]
//
//        ]
//    ]
    
    
    
    
    
    
    
    
    
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        
       
        
        
        view.addSubview(fillButton1)
        view.addSubview(fillButton2)
        view.addSubview(fillButton3)

        // Do any additional setup after loading the view.
    }
    
    
    
    @objc func openDialog() {
       
        ConvenientPickerView.showMultipleAssociatedColsPicker("多列关联数据", data: multipleAssociatedData, defaultSelectedValues: ["低热量食物"," ","炒卷心菜"]) {[unowned self] (selectedIndexs, selectedValues) in
            
            self.fillButton1.setTitle("\(selectedValues[0]) - \(selectedValues[2])", for: .normal)
            print( "选中了第\(selectedIndexs)行----选中的数据为\(selectedValues)")
            if(selectedValues[0] == "低热量食物"){
                food = "1"
            }
            else if(selectedValues[0] == "中热量食物"){
                food = "2"
            }
            else if(selectedValues[0] == "高热量食物"){
                food = "3"
            }
        }

     
    }
    
    
    @objc func openDialog1() {
        RPicker.selectDate(title: "Select Time", cancelText: "Cancel", datePickerMode: .time, didSelectDate: { [weak self](selectedDate) in
            // TODO: Your implementation for date
            
            let df = DateFormatter()
            df.dateStyle = .none
            df.timeStyle = .long
            df.dateFormat = "HH:mm"
            
            self?.fillButton2.setTitle(df.string(from: selectedDate), for: .normal)
            
            let date = Date()
            let timeFormatter = DateFormatter()
            timeFormatter.dateFormat = "yyyy.MM.dd"
            let strNowTime = timeFormatter.string(from: date) as String
            self?.time = strNowTime + "-" + df.string(from: selectedDate) + ":00"
        })
        

     
    }
    
    
    @objc func openDialog2() {
        if(food != "" && time != ""){
            let uuid = UIDevice.current.identifierForVendor?.uuidString
            let uuidName: String = uuid ?? "default value"
            let login = WordFood(androidid: uuidName, food_type: food, time: time)
            AF.request("http://39.100.73.118/deeplearning_photo/ios_word_food_type.php",
                       method: .post,
                       parameters: login,
                       encoder: URLEncodedFormParameterEncoder.default).responseString { response in
                debugPrint(response)
                        if(response.value == "yes"){
                            QMUITips.showSucceed(text: "记录成功")
                        }else{
                            QMUITips.showError(text: "记录失败")
                       }
                        self.tabBarController?.tabBarController?.selectedIndex = 0
                    }
        }
        

     
    }
    
    

    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        

        
        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 320, height: 80)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        
      
        
        
        fillButton1.frame = CGRectFlat(buttonMinX, 50, buttonSize.width, buttonSize.height)
        fillButton2.frame = CGRectFlat(buttonMinX, 160, buttonSize.width, buttonSize.height)
        fillButton3.frame = CGRectFlat(buttonMinX, 270, buttonSize.width, buttonSize.height)
        
        
        
    }
   
    
    
        
}

extension SelectFoodViewController: UIPickerViewDataSource & UIPickerViewDelegate {
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return 5
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return "\(row)"
    }
}

