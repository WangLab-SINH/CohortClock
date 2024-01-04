//
//  AboutViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/8.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import Foundation
import UIKit
import Alamofire


class AboutViewController: UIViewController{
    // number of rows in table view
    
    let logoImageView: UIImageView = {
        let image = UIImage(named: "a6")
        let imageView = UIImageView(image: image)
        return imageView
    }()
    
    var tableView: UITableView!
    
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.white
        
      
        self.title = "关于"
     view.backgroundColor = UIColor.white
        
        //view.addSubview(logoImageView)
        

        view.addSubview(logoImageView)
       
    }
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        

        logoImageView.frame = CGRect(x: 0,y: 100,width: 160,height: 160)
        logoImageView.center.x = self.view.bounds.width / 2
        
        
        
    }

    
    //weak var delegate: LoginControllerDelegate?
    
//    override func viewDidLayoutSubviews() {
//
//        logoImageView.frame = logoImageView.anchor(top: centerYAnchor, left: nil, bottom: nil, right: nil, topConstant: -200, leftConstant: 0, bottomConstant: 0, rightConstant: 0, widthConstant: 160, heightConstant: 160)
//
//    }
//
//    override init(frame: CGRect) {
//        super.init(frame: frame)
//
//
//
//        _ = logoImageView.anchor(top: centerYAnchor, left: nil, bottom: nil, right: nil, topConstant: -200, leftConstant: 0, bottomConstant: 0, rightConstant: 0, widthConstant: 160, heightConstant: 160)
//        logoImageView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
//
//        _ = emailTextField.anchor(top: logoImageView.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 10, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//
//        _ = passwordTextField.anchor(top: emailTextField.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 15, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//
//        _ = loginButton.anchor(top: passwordTextField.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 15, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//    }
//
//
//    required init?(coder aDecoder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//
//
    @objc func handleLogin() {
      
        
        // Log in stuff
        // then call completion
       // delegate?.finishedLogIn()
    }
    
    @objc func handleRegister() {
        var viewController: UIViewController
        viewController = RegisterViewController()
        viewController.title = "注册"
        navigationController?.pushViewController(viewController, animated: true)
    }
}


