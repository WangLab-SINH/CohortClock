//
//  SelectionDialogItem.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/26.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit

open class SelectionDialogItem: NSObject {
    var icon: UIImage?
    var itemTitle: String
    var handler: (() -> Void)?
    var font: UIFont?
    
    public typealias CompletionBlock = () -> Void
    
    public init(item itemTitle: String) {
        self.itemTitle = itemTitle
    }
    
    public init(item itemTitle: String, icon: UIImage) {
        self.itemTitle = itemTitle
        self.icon = icon
    }
    
    public init(item itemTitle: String, didTapHandler: @escaping CompletionBlock) {
        self.itemTitle = itemTitle
        self.handler = didTapHandler
    }
    
    public init(item itemTitle: String, icon: UIImage, didTapHandler: @escaping CompletionBlock) {
        self.itemTitle = itemTitle
        self.icon = icon
        self.handler = didTapHandler
    }
    
    public init(item itemTitle: String, icon: UIImage, font: UIFont, didTapHandler: @escaping CompletionBlock) {
        self.itemTitle = itemTitle
        self.icon = icon
        self.handler = didTapHandler
        self.font = font
    }
    
    @objc func handlerTap() {
        handler?()
    }
}
