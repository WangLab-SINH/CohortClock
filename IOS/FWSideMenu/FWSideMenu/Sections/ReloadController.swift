////
////  SeeViewController.swift
////  FWSideMenu
////
////  Created by xfg on 2018/4/10.
////  Copyright © 2018年 xfg. All rights reserved.
////
//
//import Foundation
//import UIKit
//
//class SeeViewController: UIViewController {
//
//    override func viewWillAppear(_ animated: Bool) {
//        super.viewWillAppear(animated)
//
//        // 设置导航栏
//        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor: UIColor.black, NSAttributedStringKey.font: UIFont.systemFont(ofSize: navTitleFont)]
//        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
//    }
//
//    override func viewWillDisappear(_ animated: Bool) {
//        super.viewWillDisappear(animated)
//
//        // 设置导航栏
//        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor: UIColor.black, NSAttributedStringKey.font: UIFont.systemFont(ofSize: navTitleFont)]
//        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
//    }
//
//    override var preferredStatusBarStyle: UIStatusBarStyle {
//        return.default
//    }
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        self.navigationItem.title = "更新"
//
//        self.setNeedsStatusBarAppearanceUpdate()
//    }
//}



//
//  SeeViewController.swift
//  FWSideMenu
//
//  Created by xfg on 2018/4/10.
//  Copyright © 2018年 xfg. All rights reserved.
//

import Foundation
import UIKit
import SwipeableTabBarController

class ReloadController: SwipeableTabBarController {

    private var currentSelectedIndex: NSInteger = 0

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
    }

    override var childForStatusBarStyle: UIViewController? {
            return self.tabBarController!.viewControllers![self.currentSelectedIndex]
        }
    
    override var childForStatusBarHidden: UIViewController? {
            return self.tabBarController!.viewControllers![self.currentSelectedIndex]
        }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 420, height: 100)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
        
        tabBar.frame = CGRectFlat(0, 0, buttonSize.width, buttonSize.height)
       
        
       
        
        var farme = tabBar.frame
      
        
        farme = tabBar.frame
        
    }
    
}


extension ReloadController {

    fileprivate func setupUI() {

        setValue(FWTabBar(), forKey: "tabBar")

//        let vcArray:[UIViewController] = [ReloadPhotoController(), WeightSleepController(), DatabaseController()]
        let vcArray:[UIViewController] = [ReloadPhotoController(), WeightSleepController(), QDTableViewCellInsetsViewController()]
    //    let vcArray:[UIViewController] = [ReloadPhotoController(), WeightSleepController(), RecipesTableViewController()]
        
        let titleArray = [("Food&Bev", "reload_ios_new"), ("Weight/Sleep", "weight_ios_new"), ("Modification", "change_ios_new")]
        for (index, vc) in vcArray.enumerated() {
            // 需要title的情况
            vc.tabBarItem.title = titleArray[index].0
            // 不需要title的情况，需要调整image位置
            //vc.tabBarItem.imageInsets = UIEdgeInsets(top: 5, left: 0, bottom: -5, right: 0)
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





