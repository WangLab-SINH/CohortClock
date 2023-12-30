//
//  RegisterViewController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/12/13.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
import UIKit
import Alamofire
import LXFitManager
import ZLPhotoBrowser
import Photos
import Alamofire
import SQLite


class RegisterViewController: UIViewController{
    
    
    struct Login: Encodable {
        let user: String
        let password: String
        let androidid: String
    }
    
    var logTextView: UITextView!
    
    let logoImageView: UIImageView = {
        let image = UIImage(named: "logo")
        let imageView = UIImageView(image: image)
        return imageView
    }()

    let emailTextField: LeftPaddedTextField = {
        let textField = LeftPaddedTextField()
        textField.layer.cornerRadius = 4.0
        textField.layer.borderColor = UIColor.lightGray.cgColor
        textField.layer.borderWidth = 1
        textField.placeholder = "请输入账号"
        textField.keyboardType = .emailAddress
        return textField
    }()

    let passwordTextField: LeftPaddedTextField = {
        let textField = LeftPaddedTextField()
        textField.layer.cornerRadius = 4.0
        textField.layer.borderColor = UIColor.lightGray.cgColor
        textField.layer.borderWidth = 1
        textField.placeholder = "请输入密码"
        textField.isSecureTextEntry = true
        return textField
    }()



    lazy var loginButton: UIButton = {
        let button = UIButton()
        button.layer.cornerRadius = 4.0
        button.backgroundColor = UIColor.rgb(41, 128, 185)
        button.setTitleColor(.white, for: .normal)
        button.setTitle("确认", for: .normal)
        button.addTarget(self, action: #selector(handleLogin), for: .touchUpInside)
        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.white

        
        logTextView = UITextView()
        logTextView.font = UIFont.systemFont(ofSize: 50)
        logTextView.text = "注  册"
                     
     view.backgroundColor = UIColor.white
        
        //view.addSubview(logoImageView)
        view.addSubview(emailTextField)
        view.addSubview(passwordTextField)
        view.addSubview(loginButton)
        view.addSubview(logTextView)

        

    }


    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        
        logTextView.frame = CGRect(x: 0,y: 100,width: 150,height: 60)
        logTextView.center.x = self.view.bounds.width / 2
//        logoImageView.frame = CGRect(x: 0,y: 0,width: 160,height: 160)
//        logoImageView.center.x = self.view.bounds.width / 2
        
        emailTextField.frame = CGRectFlat(buttonMinX, 200, view.bounds.width - 60, 50)
        emailTextField.center.x = self.view.bounds.width / 2

        passwordTextField.frame = CGRect(x: 0, y: 300, width: view.bounds.width - 60, height: 50)
        passwordTextField.center.x = self.view.bounds.width / 2
        
        loginButton.frame = CGRectFlat(0, 400, buttonSize.width, buttonSize.height)
        loginButton.center.x = self.view.bounds.width / 2
        
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
        var user_name: String! = emailTextField.text ?? ""
        var user_passwd: String! = passwordTextField.text ?? ""
        var androidid: String! = UIDevice.current.identifierForVendor?.uuidString ?? ""
        let login = Login(user: user_name, password: user_passwd, androidid: androidid)
        AF.request("http://39.100.73.118/deeplearning_photo/ios_register.php",
                   method: .post,
                   parameters: login,
                   encoder: URLEncodedFormParameterEncoder.default).responseString { response in
            debugPrint(response)
                    if(response.value == "no"){
                        QMUITips.showError(text: "注册失败")
                    }else{
                        var viewController: UIViewController
                        viewController = LoginViewController()
                        viewController.title = "注册"
                        self.navigationController?.pushViewController(viewController, animated: true)
                   }
        // Log in stuff
        // then call completion
       // delegate?.finishedLogIn()
    }
    }

    @objc func handleRegister() {
        // Log in stuff
        // then call completion
       // delegate?.finishedLogIn()"
    }
}
