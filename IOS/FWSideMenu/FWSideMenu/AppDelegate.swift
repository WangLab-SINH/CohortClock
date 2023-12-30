//
//  AppDelegate.swift
//  FWSideMenu
//
//  Created by xfg on 2018/4/8.
//  Copyright © 2018年 xfg. All rights reserved.
//

import UIKit
import MMKV
import ZLaunchAd

/// 状态栏高度
let kStatusBarHeight: CGFloat = UIApplication.shared.statusBarFrame.size.height
/// 导航栏高度
let kNavBarHeight: CGFloat = 44.0
/// 状态栏+导航栏的高度
let kStatusAndNavBarHeight: CGFloat = (kStatusBarHeight + kNavBarHeight)
/// 底部菜单栏高度
let kTabBarHeight: CGFloat = (UIApplication.shared.statusBarFrame.size.height > 20.0 ? 83.0:49.0)

let kMenuWidth = UIScreen.main.bounds.width * 0.82


@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        
        //        let menuContrainer = FWSideMenuContainerViewController.container(centerViewController: FWTabBarController(), leftMenuViewController: SideMenuViewController(), rightMenuViewController: SideMenuViewController())
        
        
        
        
        
        
        
        let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)
        
        let first = mmkv?.bool(forKey: "first_time") ?? false
        if(first == false){
            let menuContrainer = FWSideMenuContainerViewController.container(centerViewController: FWTabBarController(), centerLeftPanViewWidth: 20, centerRightPanViewWidth: 20, leftMenuViewController: SideMenuViewController(), rightMenuViewController: SideMenuViewController())
            menuContrainer.leftMenuWidth = kMenuWidth
            menuContrainer.rightMenuWidth = kMenuWidth
            
            self.window?.rootViewController = menuContrainer
            self.window?.makeKeyAndVisible()
           // mmkv?.set(false, forKey: "first")
            launchExample04(menuContrainer)
            
        }
        else{
            let menuContrainer = FWSideMenuContainerViewController.container(centerViewController: FWTabBarController(), centerLeftPanViewWidth: 20, centerRightPanViewWidth: 20, leftMenuViewController: SideMenuViewController(), rightMenuViewController: SideMenuViewController())
            menuContrainer.leftMenuWidth = kMenuWidth
            menuContrainer.rightMenuWidth = kMenuWidth
            
            self.window?.rootViewController = menuContrainer
            self.window?.makeKeyAndVisible()
        }
        
        
        
    

            // you can turn off logging by passing MMKVLogNone
        MMKV.initialize(rootDir: "mmkv")
        
        
       

       
        
        return true
    }
    
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }
    
    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }
    
    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }
    
    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }
    
    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }
    
    
}

extension AppDelegate {
    
    class func resizableImage(imageName: String, edgeInsets: UIEdgeInsets) -> UIImage? {
        
        let image = UIImage(named: imageName)
        if image == nil {
            return nil
        }
        let imageW = image!.size.width
        let imageH = image!.size.height
        
        return image?.resizableImage(withCapInsets: UIEdgeInsets(top: imageH * edgeInsets.top, left: imageW * edgeInsets.left, bottom: imageH * edgeInsets.bottom, right: imageW * edgeInsets.right), resizingMode: .stretch)
    }
    
    /// 将颜色转换为图片
    ///
    /// - Parameter color: 颜色
    /// - Returns: UIImage
    class func getImageWithColor(color: UIColor) -> UIImage {
        
        let rect = CGRect(x: 0, y: 0, width: 1, height: 1)
        UIGraphicsBeginImageContext(rect.size)
        let context = UIGraphicsGetCurrentContext()
        context!.setFillColor(color.cgColor)
        context!.fill(rect)
        let image = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return image!
    }
    
    
    
    
    
    
    
    /// 本地图片
    func launchExample01(_ homeVC: UIViewController) {
        let adView = ZLaunchAd.create(showEnterForeground: true)
        let imageResource = ZLaunchAdImageResourceConfigure()
        imageResource.imageNameOrImageURL = "163yun"
        imageResource.imageDuration = 5
        imageResource.imageFrame = UIScreen.main.bounds
        adView.setImageResource(imageResource, action: {
            let vc = UIViewController()
            vc.view.backgroundColor = UIColor.yellow
            homeVC.navigationController?.pushViewController(vc, animated: true)
        })
    }
    /// 加载网络图片
    func launchExample02(_ homeVC: UIViewController) {
        let adView = ZLaunchAd.create(waitTime: 5)
        request { model in
            let buttonConfig = ZLaunchSkipButtonConfig()
            buttonConfig.skipBtnType = model.skipBtnType
            let imageResource = ZLaunchAdImageResourceConfigure()
            imageResource.imageNameOrImageURL = model.imgUrl
            imageResource.animationType = model.animationType
            imageResource.imageDuration = model.duration
            imageResource.imageFrame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.width*model.height/model.width)
            /// 设置图片、跳过按钮
            adView.setImageResource(imageResource, buttonConfig: buttonConfig, action: {
                let vc = UIViewController()
                vc.view.backgroundColor = UIColor.yellow
                homeVC.navigationController?.pushViewController(vc, animated: true)
            })
        }
    }
    
    /// 自定义通知控制启动页广告出现
    /// 如果通知控制显示不同的广告图片，网络请求需要写在`adNetRequest`闭包中
    /// 如果显示的是同一张图片，网络请求不需要写在闭包中，避免重复请求
    func launchExample03(_ homeVC: UIViewController) {
        ZLaunchAd.create(customNotificationName: "myNotification") { (adView) in
            self.request { model in
                let buttonConfig = ZLaunchSkipButtonConfig()
                buttonConfig.skipBtnType = model.skipBtnType
                let imageResource = ZLaunchAdImageResourceConfigure()
                imageResource.imageNameOrImageURL = model.imgUrl
                imageResource.animationType = model.animationType
                imageResource.imageDuration = model.duration
                imageResource.imageFrame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.width*model.height/model.width)
                
                adView.setImageResource(imageResource, buttonConfig: buttonConfig, action: {
                    let vc = UIViewController()
                    vc.view.backgroundColor = UIColor.yellow
                    homeVC.navigationController?.pushViewController(vc, animated: true)
                })
            }
        }
    }
    
    /// 进入前台时显示
    /// `showEnterForeground`需要设置为`true`，`timeForWillEnterForeground`为app进入后台到再次进入前台的时间
    /// 如果进入前台时加载不同的广告图片，网络请求需要写在`adNetRequest`闭包中
    /// 如果显示的是同一张图片，网络请求不需要写在闭包中，避免重复请求
    func launchExample04(_ homeVC: UIViewController) {
        ZLaunchAd.create(showEnterForeground: true, timeForWillEnterForeground: 10, adNetRequest: { adView in
            self.request { model in
                let buttonConfig = ZLaunchSkipButtonConfig()
                buttonConfig.skipBtnType = model.skipBtnType
                let imageResource = ZLaunchAdImageResourceConfigure()
                imageResource.imageNameOrImageURL = model.imgUrl
                imageResource.animationType = model.animationType
                imageResource.imageDuration = model.duration
                imageResource.imageFrame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.height-75)
                
                adView.setImageResource(imageResource, buttonConfig: buttonConfig, action: {
                    let vc = UIViewController()
                    vc.view.backgroundColor = UIColor.red
                    homeVC.navigationController?.pushViewController(vc, animated: true)
                })
            }
        }).endOfCountDown {
          
        }
    }
    
    func request(_ completion: @escaping (AdModel)->()) -> Void {
        DispatchQueue.main.asyncAfter(deadline: .now()+1) {
            if let path = Bundle.main.path(forResource: "data", ofType: "json") {
                let url = URL(fileURLWithPath: path)
                do {
                    let data = try Data(contentsOf: url)
                    let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments)
                    if let dict = json as? [String: Any],
                        let dataArray = dict["data"] as? [[String: Any]] {
                        /// 随机显示
                        let idx = Int(arc4random()) % dataArray.count
                        let model = AdModel(dataArray[idx])
                        completion(model)
                    }
                } catch  {
                    print(error)
                }
            }
        }
    }
    
    
    
    
}
