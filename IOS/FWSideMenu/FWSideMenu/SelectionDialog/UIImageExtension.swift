//
//  UIImageExtension.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/26.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit

extension UIImage {
    class func createImageWithColor(_ color: UIColor) -> UIImage {
        let rect = CGRect(x: 0.0, y: 0.0, width: 1.0, height: 1.0)
        UIGraphicsBeginImageContext(rect.size);
        let context = UIGraphicsGetCurrentContext()
        
        context?.setFillColor(color.cgColor)
        context?.fill(rect)
        
        let image = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return image!
    }
}
