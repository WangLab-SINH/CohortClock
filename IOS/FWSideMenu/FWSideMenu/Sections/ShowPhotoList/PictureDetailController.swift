//
//  PictureDetailController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/11/15.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
//
//  DetailViewController.swift
//  Swift-TableView-Example
//
//  Created by Bilal ARSLAN on 12/10/14.
//  Copyright (c) 2014 Bilal ARSLAN. All rights reserved.
//

import Foundation 
import UIKit


private let TestImageSize = CGSize(width: 160, height: 160)

class PictureDetailController: QDCommonViewController {

    
    
    var imageView: UIImageView!
    var nameLabel: UILabel!
    var prepTime: UILabel!
    var imageName: String?
    var recipe: Recipe?
    private var testImageView: UIImageView!
    
    func load(fileName: String) -> UIImage? {
       let fileURL = documentsUrl.appendingPathComponent(fileName)
       do {
           let imageData = try Data(contentsOf: fileURL)
           return UIImage(data: imageData)
       } catch {
           print("Error loading image : \(error)")
       }
       return nil
   }
    
    var documentsUrl: URL {
        return FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
    }


    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = nil
        navigationItem.title = nil
//        let obj = QDTableViewCellInsetsViewController()
//
//        //函数
//        obj.callBackBlock { (str) in
//            print(str)
//            self.imageName = str
//        }
//
//        //参数
//        obj.callBack = { (str: String)->Void in
//            print(str)
//            return
//        }
//        obj.useBlock()
        
        
        
//        let shared = QDTableViewCellInsetsViewController.shared
        //imageName = shared.inputName
        super.initSubviews()
        testImageView = UIImageView()
        //cell.imageView?.image = load(fileName: "20201130161741bdrghj.jpg")
        //testImageView.image = UIImage(named: self.imageName ?? "vegetable_curry.jpg")
        //testImageView.image = load(fileName: "20201130161741bdrghj.jpg")
        testImageView.image = load(fileName: self.imageName ?? "vegetable_curry.jpg")
        view.addSubview(testImageView)
//        let shared = QDTableViewCellInsetsViewController.shared
//        imageName = shared.inputName
//        if let recipe = recipe {
//            navigationItem.title = recipe.name
//            imageView.image = UIImage(named: recipe.thumbnails)
//            nameLabel.text = recipe.name
//            prepTime.text = "Prep Time: " + recipe.prepTime
//        }
    }
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func initSubviews() {
        
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        testImageView.frame = CGRect(x: view.bounds.width.center(TestImageSize.width), y: contentMinY + 60, width: TestImageSize.width, height: TestImageSize.height)
       
    }

    
}

