//
//  FWTabBarController.swift
//  FanweApps
//
//  Created by xfg on 2018/1/23.
//  Copyright © 2018年 xfg. All rights reserved.
//

import Foundation
import UIKit
import SwipeableTabBarController
import Alamofire
import MMKV

//class FWTabBarController: UITabBarController, UITabBarControllerDelegate {
//
//    private var currentSelectedIndex: NSInteger = 0
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        // 背景色设为白色
//        self.view.backgroundColor = UIColor.white
//
//        self.delegate = self
//
//        let tabBar = UITabBarItem.appearance()
//        let attrsNormal = [NSAttributedStringKey.font: UIFont.systemFont(ofSize: 10.0), NSAttributedStringKey.foregroundColor: UIColor.gray]
//        let attrsSelected = [NSAttributedStringKey.font: UIFont.systemFont(ofSize: 10.0), NSAttributedStringKey.foregroundColor: UIColor(red: (71/255), green: (186/255), blue: (254/255), alpha: 1.0)]
//        tabBar.setTitleTextAttributes(attrsNormal, for: .normal)
//        tabBar.setTitleTextAttributes(attrsSelected, for: .selected)
//
//        setupUI()
//
//        self.setNeedsStatusBarAppearanceUpdate()
//    }
//
//    //    override var childViewControllerForStatusBarStyle: UIViewController? {
//    //        return self.tabBarController!.viewControllers![self.currentSelectedIndex]
//    //    }
//    //
//    //    override var childViewControllerForStatusBarHidden: UIViewController? {
//    //        return self.tabBarController!.viewControllers![self.currentSelectedIndex]
//    //    }
//}
//
//
//extension FWTabBarController {
//
//    fileprivate func setupUI() {
//
//        setValue(FWTabBar(), forKey: "tabBar")
//
//        let vcArray:[UIViewController] = [RecentViewController(), ViewController(), SeeViewController(), QworldViewController()]
//        let titleArray = [("饮食习惯", "recent"), ("拍食物", "buddy"), ("更新", "see"), ("我的", "qworld")]
//        for (index, vc) in vcArray.enumerated() {
//            // 需要title的情况
//            vc.tabBarItem.title = titleArray[index].0
//            // 不需要title的情况，需要调整image位置
//            // vc.tabBarItem.imageInsets = UIEdgeInsetsMake(5, 0, -5, 0)
//            vc.tabBarItem.image = UIImage(named: "tab_\(titleArray[index].1)_nor")
//            vc.tabBarItem.selectedImage = UIImage(named: "tab_\(titleArray[index].1)_press")
//            let nav = FWNavigationController(rootViewController: vc)
//            addChildViewController(nav)
//        }
//    }
//
//    func tabBarController(_ tabBarController: UITabBarController, shouldSelect viewController: UIViewController) -> Bool {
//        //        if tabBarController.viewControllers != nil {
//        //            self.currentSelectedIndex = tabBarController.viewControllers!.firstIndex(of: viewController) ?? 0
//        //        }
//        //        self.setNeedsStatusBarAppearanceUpdate()
//        return true
//    }
//}
//
class FWTabBarController: SwipeableTabBarController {

    private var currentSelectedIndex: NSInteger = 0
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)

    override func viewDidLoad() {
        super.viewDidLoad()

        // 背景色设为白色
        self.view.backgroundColor = UIColor.white

        self.delegate = self

        let tabBar = UITabBarItem.appearance()
        let attrsNormal = [NSAttributedString.Key.font: UIFont.systemFont(ofSize: 10.0), NSAttributedString.Key.foregroundColor: UIColor.gray]
        let attrsSelected = [NSAttributedString.Key.font: UIFont.systemFont(ofSize: 10.0), NSAttributedString.Key.foregroundColor: UIColor(red: (71/255), green: (186/255), blue: (254/255), alpha: 1.0)]
        tabBar.setTitleTextAttributes(attrsNormal, for: .normal)
        tabBar.setTitleTextAttributes(attrsSelected, for: .selected)
        setupUI()

        self.setNeedsStatusBarAppearanceUpdate()
        syndata()
        
        
        
        
        
    }

    override var childForStatusBarStyle: UIViewController? {
            return self.tabBarController!.viewControllers![self.currentSelectedIndex]
        }
    
    override var childForStatusBarHidden: UIViewController? {
            return self.tabBarController!.viewControllers![self.currentSelectedIndex]
        }
    
    
    
    
    
    
    
//    override func tabBar(_ tabBar: UITabBar, didSelect item: UITabBarItem) {
//
//            print(item.tag)
//
////            if item.tag == 0 {
////
////                NotificationCenter.default.post(name: NSNotification.Name(rawValue: "homeRefresh"), object: nil, userInfo: nil)
////
////
////
////            }
////
////            else if item.tag == 3
////
////            {
////
////                NotificationCenter.defaultCenter().postNotificationName("profileRefresh", object: nil, userInfo: nil)
////
////            }
////
////            else
////
////            {
////
////                NotificationCenter.defaultCenter().postNotificationName("discoverRefresh", object: nil, userInfo: nil)
////
////
////
////            }
//
//        }
    

    
    
    
    
    
    
    
    func syndata(){
//        let parameters: [String: [String]] = [
//            "foo": ["bar"],
//            "baz": ["a", "b"],
//            "qux": ["x", "y", "z"]
//        ]
        let uuid = UIDevice.current.identifierForVendor?.uuidString
        
        
        let uuidName: String = uuid ?? "default value"
        
        let parameters: [String: String] = [
            "androidid": uuidName
        ]

//        AF.request("https://httpbin.org/post", method: .post, parameters: parameters, encoder: JSONParameterEncoder.default)
//        AF.request("https://httpbin.org/post", method: .post, parameters: parameters, encoder: JSONParameterEncoder.prettyPrinted)
//        struct HTTPBinResponse: Decodable { let androidid: String }
//        AF.request("http://39.100.73.118/deeplearning_photo/ios_syn_database.php").responseDecodable(of: HTTPBinResponse.self) { response in
//            debugPrint("Response: \(response)")
//        }
//        AF.request("http://39.100.73.118/deeplearning_photo/ios_syn_database.php?androidid="+uuidName+"").responseJSON  {
//            (response) in
//            debugPrint(response)
//        }

        AF.request("http://39.100.73.118/deeplearning_photo/ios_syn_database.php?androidid="+uuidName+"").responseString {response in
                //debugPrint("Response: \(response)")
            let result = response.value ?? "default"
            
            
            var database: Database!
            database = Database()
            database.tableLampCreate()
            let user_time_list:[String] = database.queryTableTime()
            //debugPrint(user_time_list)
            
            
            
            
            let arraySubStrings: [Substring] = result.split(separator: "<")
            var each_data: [String] = []
            for item in arraySubStrings{
                let temp_data: [Substring] = item.split(separator: ";")
                if(temp_data.count < 7){
                    continue
                }
                let user_name: String = String(temp_data[0])
                let user_time: String = String(temp_data[1])
                let photo_url: String = "http://39.100.73.118/deeplearning_photo/uploads/" + String(temp_data[2])
                let food_type: String = String(temp_data[3])
                let photo_cal: String = String(temp_data[4])
                let photo_kind: String = String(temp_data[5])
                let workday: String = String(temp_data[6])
                let weekend: String = String(temp_data[7])
                
                let temp_date:String = String(user_time.split(separator: "-")[0])
                let temp_time:String = String(user_time.split(separator: "-")[1])
                let temp_hour:String = String(temp_time.split(separator: ":")[0])
                let temp_minute:String = String(temp_time.split(separator: ":")[1])
                let temp_second:String = String(temp_time.split(separator: ":")[2])
                
                let new_user_time:String = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second
                if(!user_time_list.contains(new_user_time)){
                    database.tableLampInsertItem(user_name: user_name, user_time: user_time, photo_type: food_type, photo_url: photo_url, photo_kind: photo_kind, photo_cal: photo_cal, photo_web_url: photo_url, workday: workday, weekend: weekend)
                }else{
                    database.tableLampUpdateItem(user_name: user_name, user_time: user_time, photo_type: food_type, photo_kind: photo_kind, photo_cal: photo_cal, photo_web_url: photo_url, workday: workday, weekend: weekend)
                }
                
            }
            
            database.queryTableLamp()
            
            
        }
            
//        func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
//            // Handle didSelect viewController method here
//            debugPrint("asda")
//        }
        
        
        
//            {
//            debugPrint(response)
//
//                if let jsonData = response.result.value,
//                            let values = try? JSONDecoder().decode(APIResponse.self, from: jsonData).data {
//                            //process success
//                            print(values)
//                        } else {
//                            // handle error
//                        }
//            }
        
//        AF.request("http://39.100.73.118/deeplearning_photo/ios_syn_database.php", method: .post, parameters: parameters).responseJSON { response in
//            debugPrint(response)
//        }
    }
}


extension FWTabBarController {

    fileprivate func setupUI() {

        setValue(FWTabBar(), forKey: "tabBar")
        
        
        


//        let vcArray:[UIViewController] = [TestHtmlCode(), LXAddPhotoViewController(), ReloadController(), QworldViewController()]
        let vcArray:[UIViewController] = [TestHtmlCode(), ReloadController(), TrendingViewController(), QworldViewController()]
        
        
        
        //let vcArray:[UIViewController] = [TestHtmlCode(), ViewController(), ReloadController(), QworldViewController()]
//        let titleArray = [("饮食习惯", "recent"), ("拍食物", "buddy"), ("更新", "see"), ("我的", "qworld")]
        let titleArray = [("饮食习惯", "trending_photo_ios_new"), ("拍食物", "take_photo_plot_ios_new"), ("更新", "reupload_plot_ios_new"), ("我的", "tab_buddy_nor")]
        
        for (index, vc) in vcArray.enumerated() {
            // 需要title的情况
            vc.hidesBottomBarWhenPushed = false
            vc.tabBarItem.title = titleArray[index].0
            // 不需要title的情况，需要调整image位置
            //vc.tabBarItem.imageInsets = UIEdgeInsetsMake(5, 0, -5, 0)
//            vc.tabBarItem.image = UIImage(named: "tab_\(titleArray[index].1)_nor")
//            vc.tabBarItem.selectedImage = UIImage(named: "tab_\(titleArray[index].1)_press")
            vc.tabBarItem.image = UIImage(named: titleArray[index].1)
            vc.tabBarItem.selectedImage = UIImage(named: titleArray[index].1)
            
            let nav = FWNavigationController(rootViewController: vc)
            addChild(nav)
        }
    }

    override func tabBarController(_ tabBarController: UITabBarController, shouldSelect viewController: UIViewController) -> Bool {
                if tabBarController.viewControllers != nil {
                    self.currentSelectedIndex = tabBarController.viewControllers!.firstIndex(of: viewController) ?? 0
                }
                self.setNeedsStatusBarAppearanceUpdate()
        return true
    }
    

}



