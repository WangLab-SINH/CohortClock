//
//  AdModel.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/29.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit
import ZLaunchAd

struct AdModel {
    
    var imgUrl: String!
    var duration: Int!
    var width: CGFloat!
    var height: CGFloat!
    var animationType: ZLaunchAnimationType!
    var skipBtnType: ZLaunchSkipButtonType!
    
    
    init(_ dic: Dictionary<String, Any>) {
        imgUrl = dic["imgUrl"] as? String
        duration = dic["duration"] as? Int
        width = dic["width"] as? CGFloat
        height = dic["height"] as? CGFloat
        
        let btnType = dic["skipBtnType"] as! Int
        skipBtnType = ZLaunchSkipButtonType(rawValue: btnType)!
        
        let animationType = dic["animationType"] as! Int
        self.animationType = ZLaunchAnimationType(rawValue: animationType)!
    }
    
}
