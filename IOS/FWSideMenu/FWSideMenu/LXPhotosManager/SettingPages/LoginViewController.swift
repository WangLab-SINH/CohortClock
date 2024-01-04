//
//  LoginController.swift
//  Login
//
//  Created by Dulio Denis on 10/12/16.
//  Copyright © 2016 Dulio Denis. All rights reserved.
//

import Foundation
import UIKit
import Alamofire


class LoginViewController: UIViewController{
    
    struct Login: Encodable {
        let user: String
        let password: String
        let androidid: String
    }
    
    
    let logoImageView: UIImageView = {
        let image = UIImage(named: "logo")
        let imageView = UIImageView(image: image)
        return imageView
    }()
    
    var logTextView: UITextView!
    
    
    let emailTextField: LeftPaddedTextField = {
        let textField = LeftPaddedTextField()
        textField.layer.cornerRadius = 4.0
        textField.layer.borderColor = UIColor.lightGray.cgColor
        textField.layer.borderWidth = 1
        textField.placeholder = "Please enter your account"
        textField.keyboardType = .emailAddress
        return textField
    }()
    
    let passwordTextField: LeftPaddedTextField = {
        let textField = LeftPaddedTextField()
        textField.layer.cornerRadius = 4.0
        textField.layer.borderColor = UIColor.lightGray.cgColor
        textField.layer.borderWidth = 1
        textField.placeholder = "Please enter your password"
        textField.isSecureTextEntry = true
        return textField
    }()
    
    
    let RegisterTextField: UIButton = {
        let button = UIButton()
        button.layer.cornerRadius = 4.0
       
        button.setTitleColor(.black, for: .normal)
        button.setTitle("Sign up", for: .normal)
        button.addTarget(self, action: #selector(handleRegister), for: .touchUpInside)
        return button
    }()
    
    lazy var loginButton: UIButton = {
        let button = UIButton()
        button.layer.cornerRadius = 4.0
        button.backgroundColor = UIColor.rgb(41, 128, 185)
        button.setTitleColor(.white, for: .normal)
        button.setTitle("Login", for: .normal)
        button.addTarget(self, action: #selector(handleLogin), for: .touchUpInside)
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.white
        
        logTextView = UITextView()
        logTextView.font = UIFont.systemFont(ofSize: 50)
        logTextView.text = "Login"

        self.title = "Login"
     view.backgroundColor = UIColor.white
        
        //view.addSubview(logoImageView)
        view.addSubview(emailTextField)
        view.addSubview(passwordTextField)
        view.addSubview(loginButton)
        view.addSubview(RegisterTextField)

        view.addSubview(logTextView)

    }
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        

//        logoImageView.frame = CGRect(x: 0,y: 0,width: 160,height: 160)
//        logoImageView.center.x = self.view.bounds.width / 2
        
        logTextView.frame = CGRect(x: 0,y: 100,width: 150,height: 60)
        logTextView.center.x = self.view.bounds.width / 2
        
        emailTextField.frame = CGRectFlat(buttonMinX, 200, view.bounds.width - 60, 50)
        emailTextField.center.x = self.view.bounds.width / 2

        passwordTextField.frame = CGRect(x: 0, y: 300, width: view.bounds.width - 60, height: 50)
        passwordTextField.center.x = self.view.bounds.width / 2
        RegisterTextField.frame = CGRectFlat(30, 360, self.view.bounds.width / 4, buttonSize.height)
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
        AF.request("http://39.100.73.118/deeplearning_photo/ios_login.php",
                   method: .post,
                   parameters: login,
                   encoder: URLEncodedFormParameterEncoder.default).responseString { response in
            debugPrint(response)
                    if(response.value == "no"){
                        QMUITips.showError(text: "Fail to login in")
                    }else{
                        QMUITips.showSucceed(text: "Login in successfully")
                   }
                }
        
        
        // Log in stuff
        // then call completion
       // delegate?.finishedLogIn()
    }
    
    @objc func handleRegister() {
        var viewController: UIViewController
        viewController = RegisterViewController()
        viewController.title = "Sign up"
        navigationController?.pushViewController(viewController, animated: true)
    }
}

class LeftPaddedTextField: UITextField {
    
    override func textRect(forBounds bounds: CGRect) -> CGRect {
        return CGRect(x: bounds.origin.x + 10, y: bounds.origin.y, width: bounds.width + 10, height: bounds.height)
    }
    
    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        return CGRect(x: bounds.origin.x + 10, y: bounds.origin.y, width: bounds.width + 10, height: bounds.height)
    }
    
}
extension UIView {
    
    func anchorToTop(top: NSLayoutYAxisAnchor? = nil, left: NSLayoutXAxisAnchor? = nil, bottom: NSLayoutYAxisAnchor? = nil, right: NSLayoutXAxisAnchor? = nil) {
        
        anchorWithConstantsToTop(top: top, left: left, bottom: bottom, right: right, topConstant: 0, leftConstant: 0, bottomConstant: 0, rightConstant: 0)
    }
    
    
    func anchorWithConstantsToTop(top: NSLayoutYAxisAnchor? = nil, left: NSLayoutXAxisAnchor? = nil, bottom: NSLayoutYAxisAnchor? = nil, right: NSLayoutXAxisAnchor? = nil, topConstant: CGFloat = 0, leftConstant: CGFloat = 0, bottomConstant: CGFloat = 0, rightConstant: CGFloat = 0) {
        
        translatesAutoresizingMaskIntoConstraints = false
        
        if let top = top {
            topAnchor.constraint(equalTo: top, constant: topConstant).isActive = true
        }
        
        if let bottom = bottom {
            bottomAnchor.constraint(equalTo: bottom, constant: -bottomConstant).isActive = true
        }
        
        if let left = left {
            leftAnchor.constraint(equalTo: left, constant: leftConstant).isActive = true
        }
        
        if let right = right {
            rightAnchor.constraint(equalTo: right, constant: -rightConstant).isActive = true
        }
    }
    
    
    func anchor(top: NSLayoutYAxisAnchor? = nil, left: NSLayoutXAxisAnchor? = nil, bottom: NSLayoutYAxisAnchor? = nil, right: NSLayoutXAxisAnchor? = nil, topConstant: CGFloat = 0, leftConstant: CGFloat = 0, bottomConstant: CGFloat = 0, rightConstant: CGFloat = 0, widthConstant: CGFloat = 0, heightConstant: CGFloat = 0) -> [NSLayoutConstraint] {
        translatesAutoresizingMaskIntoConstraints = false
        
        var anchors = [NSLayoutConstraint]()
        
        if let top = top {
            anchors.append(topAnchor.constraint(equalTo: top, constant: topConstant))
        }
        
        if let left = left {
            anchors.append(leftAnchor.constraint(equalTo: left, constant: leftConstant))
        }
        
        if let bottom = bottom {
            anchors.append(bottomAnchor.constraint(equalTo: bottom, constant: -bottomConstant))
        }
        
        if let right = right {
            anchors.append(rightAnchor.constraint(equalTo: right, constant: -rightConstant))
        }
        
        if widthConstant > 0 {
            anchors.append(widthAnchor.constraint(equalToConstant: widthConstant))
        }
        
        if heightConstant > 0 {
            anchors.append(heightAnchor.constraint(equalToConstant: heightConstant))
        }
        
        anchors.forEach({$0.isActive = true})
        
        return anchors
    }
    
}


extension UIColor {
    
    static func rgb(_ red: CGFloat, _ green: CGFloat, _ blue: CGFloat) -> UIColor {
        return UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1)
    }
    
}
