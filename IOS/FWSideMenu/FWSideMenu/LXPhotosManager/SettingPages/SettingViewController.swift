////
////  SettingViewController.swift
////  FWSideMenu
////
////  Created by wanglab on 2020/12/13.
////  Copyright © 2020 xfg. All rights reserved.
////
//
//import Foundation
//import UIKit
//class SettingViewController: UIViewController{
//
//
//    var uiswitch: UISwitch!
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        self.view.backgroundColor = UIColor.white
//
//       uiswitch = UISwitch()
//        uiswitch.frame = CGRect(x: 10,y: 230,width: 200,height: 30)
//        uiswitch.isOn = false
//        uiswitch.addTarget(self, action: #selector(self.siwtchDidChange), for: UIControl.Event.valueChanged)
//     view.backgroundColor = UIColor.white
//        view.addSubview(uiswitch)
//
//
//
//
//
//
//    }
//
//
//    override func viewDidLayoutSubviews() {
//        super.viewDidLayoutSubviews()
//
//        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
//        let buttonSpacingHeight = QDButtonSpacingHeight
//        let buttonSize = CGSize(width: 260, height: 40)
//        let buttonMinX = view.bounds.width.center(buttonSize.width)
//        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
//
//
//
//
//
//    }
//
//
//    //weak var delegate: LoginControllerDelegate?
//
//    //    override func viewDidLayoutSubviews() {
//    //
//    //        logoImageView.frame = logoImageView.anchor(top: centerYAnchor, left: nil, bottom: nil, right: nil, topConstant: -200, leftConstant: 0, bottomConstant: 0, rightConstant: 0, widthConstant: 160, heightConstant: 160)
//    //
//    //    }
//    //
//    //    override init(frame: CGRect) {
//    //        super.init(frame: frame)
//    //
//    //
//    //
//    //        _ = logoImageView.anchor(top: centerYAnchor, left: nil, bottom: nil, right: nil, topConstant: -200, leftConstant: 0, bottomConstant: 0, rightConstant: 0, widthConstant: 160, heightConstant: 160)
//    //        logoImageView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
//    //
//    //        _ = emailTextField.anchor(top: logoImageView.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 10, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//    //
//    //        _ = passwordTextField.anchor(top: emailTextField.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 15, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//    //
//    //        _ = loginButton.anchor(top: passwordTextField.bottomAnchor, left: leftAnchor, bottom: nil, right: rightAnchor, topConstant: 15, leftConstant: 30, bottomConstant: 0, rightConstant: 30, widthConstant: 0, heightConstant: 50)
//    //    }
//    //
//    //
//    //    required init?(coder aDecoder: NSCoder) {
//    //        fatalError("init(coder:) has not been implemented")
//    //    }
//    //
//    //
//    @objc func siwtchDidChange() {
//        // Log in stuff
//        // then call completion
//       // delegate?.finishedLogIn()
//    }
//
//    @objc func handleRegister() {
//        // Log in stuff
//        // then call completion
//       // delegate?.finishedLogIn()
//    }
//}
import UIKit
import MMKV

private let kIdentifierForDoneCell = 999

class SettingViewController: QDCommonTableViewController {
    
    
    
    
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)
    
    
    
    private var currentAnimationStyle: QMUIModalPresentationAnimationStyle = .fade
    
    private var modalViewControllerForAddSubview: QMUIModalPresentationViewController?

    private var orientationLabel: QMUILabel!
    
    
    
   
                
    
    
    init() {
        super.init(style: .grouped)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func initTableView() {
        super.initTableView()
        
        let food = mmkv?.bool(forKey: "food") ?? false
        let hand = mmkv?.bool(forKey: "hand") ?? false
        let time = mmkv?.bool(forKey: "time") ?? false
        
        
        
        let data2 = QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.landscapeLeft.rawValue), text: "是否允许手动上传", didSelectTarget: nil,
                                                didSelectAction: nil,
                                                accessoryType: .switch,
                                                accessoryValueObject: hand as AnyObject, // switch 类型的，可以通过传一个 NSNumber 对象给 accessoryValueObject 来指定这个 switch.on 的值
            accessoryTarget: self,
            accessoryAction: #selector(handleSwitchCellEvent(_:)))
        
        tableView.qmui_staticCellDataSource = QMUIStaticTableViewCellDataSource(
            cellDataSections: [
                // section 0
                
                [QMUIStaticTableViewCellData(identifier: kIdentifierForDoneCell, text: "隐私权限说明", didSelectTarget: self, didSelectAction: #selector(handleDoneCellEvent1(_:))),
                ],
                
                
//                [QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.portrait.rawValue), text: "是否推荐食物", didSelectTarget: self, didSelectAction: #selector(handleCheckmarkEvent(_:)), accessoryType: .switch),
//                 QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.landscapeLeft.rawValue), text: "是否允许手动上传", didSelectTarget: self, didSelectAction: #selector(handleCheckmarkEvent(_:)),accessoryType: .switch),
//                 QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.landscapeRight.rawValue), text: "选择时间模式", didSelectTarget: self, didSelectAction: #selector(handleCheckmarkEvent(_:)), accessoryType: .switch),
////                 QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.portraitUpsideDown.rawValue), text: "UIInterfaceOrientationMaskPortraitUpsideDown", didSelectTarget: self, didSelectAction: #selector(handleCheckmarkEvent(_:)), accessoryType: .checkmark),
//                ],
                
                
                [QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.portrait.rawValue), text: "是否推荐食物", didSelectTarget: nil,
                                             didSelectAction: nil,
                                             accessoryType: .switch,
                                             accessoryValueObject: food as AnyObject, // switch 类型的，可以通过传一个 NSNumber 对象给 accessoryValueObject 来指定这个 switch.on 的值
         accessoryTarget: self,
         accessoryAction: #selector(handleSwitchCellEvent0(_:))),
                 data2,
                 QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.landscapeRight.rawValue), text: "选择时间模式", didSelectTarget: nil,
                                             didSelectAction: nil,
                                             accessoryType: .switch,
                                             accessoryValueObject: time as AnyObject, // switch 类型的，可以通过传一个 NSNumber 对象给 accessoryValueObject 来指定这个 switch.on 的值
         accessoryTarget: self,
         accessoryAction: #selector(handleSwitchCellEvent1(_:)))
//                 QMUIStaticTableViewCellData(identifier: Int(UIInterfaceOrientationMask.portraitUpsideDown.rawValue), text: "UIInterfaceOrientationMaskPortraitUpsideDown", didSelectTarget: self, didSelectAction: #selector(handleCheckmarkEvent(_:)), accessoryType: .checkmark),
                ],
                
                
                
                
                // section 1
                [QMUIStaticTableViewCellData(identifier: kIdentifierForDoneCell, text: "同步数据", didSelectTarget: self, didSelectAction: #selector(handleDoneCellEvent2(_:))),
                ],
                [QMUIStaticTableViewCellData(identifier: kIdentifierForDoneCell, text: "切换账号", didSelectTarget: self, didSelectAction: #selector(handleDoneCellEvent3(_:))),
                ],
                [QMUIStaticTableViewCellData(identifier: kIdentifierForDoneCell, text: "退出登录", didSelectTarget: self, didSelectAction: #selector(handleDoneCellEvent4(_:))),
                ]])
        
        orientationLabel = QMUILabel(with: UIFontMake(14), textColor: UIColorGray7)
        let attributes: [NSAttributedString.Key: Any] = [NSAttributedString.Key.font : UIFontMake(14), NSAttributedString.Key.foregroundColor: UIColorGray7, NSAttributedString.Key.paragraphStyle: NSMutableParagraphStyle(lineHeight: 22, lineBreakMode: .byWordWrapping, textAlignment: .center)]
        orientationLabel.attributedText = NSAttributedString(string: "当前界面支持的方向：\n\(descriptionString(supportedOrientationMask))", attributes: attributes)
        orientationLabel.numberOfLines = 2
        orientationLabel.contentEdgeInsets = UIEdgeInsets(top: 24, left: 24, bottom: 24, right: 24)
        orientationLabel.sizeToFit()
        tableView.tableFooterView = orientationLabel
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.qmui_staticCellDataSource?.cellForRow(at: indexPath)
        cell?.textLabel?.adjustsFontSizeToFitWidth = true
        
        if let data = tableView.qmui_staticCellDataSource?.cellData(at: indexPath) {
            if data.identifier == kIdentifierForDoneCell {
                cell?.textLabel?.textAlignment = .center
                cell?.textLabel?.textColor = QDThemeManager.shared.currentTheme?.themeTintColor
            }
        }
        
        return cell ?? UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0 {
            return ""
        }
        return nil
    }
    
    
    @objc func handleSwitchCellEvent0(_ cellData: QMUIStaticTableViewCellData) {
        let hand = mmkv?.bool(forKey: "food") ?? false
        if(hand == false){
            mmkv?.set(true, forKey: "food")
        }
        else{
            mmkv?.set(false, forKey: "food")
        }
    }
    
    
    
    @objc func handleSwitchCellEvent(_ cellData: QMUIStaticTableViewCellData) {
        let hand = mmkv?.bool(forKey: "hand") ?? false
        if(hand == false){
            mmkv?.set(true, forKey: "hand")
        }
        else{
            mmkv?.set(false, forKey: "hand")
        }
    }
    
    
    @objc func handleSwitchCellEvent1(_ cellData: QMUIStaticTableViewCellData) {
        let hand = mmkv?.bool(forKey: "time") ?? false
        if(hand == false){
            mmkv?.set(true, forKey: "time")
        }
        else{
            mmkv?.set(false, forKey: "time")
        }
    }
    
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return tableView.qmui_staticCellDataSource?.cellDataSections.count ?? 0
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tableView.qmui_staticCellDataSource?.cellDataSections[section].count ?? 0
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableView.qmui_staticCellDataSource?.heightForRow(at: indexPath) ?? 0
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.qmui_staticCellDataSource?.didSelectRow(at: indexPath)
    }
    
    func tableView(_ tableView: UITableView, accessoryButtonTappedForRowWith indexPath: IndexPath) {
        tableView.qmui_staticCellDataSource?.accessoryButtonTappedForRow(with: indexPath)
    }
    
    @objc func handleCheckmarkEvent(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
//            guard let indexPath = data.indexPath, let cell = self.tableView.cellForRow(at: indexPath) else {
//                return
//            }
//            if data.accessoryType == .checkmark {
////                cell.accessoryType = .none
////                data.accessoryType = .none
//            } else {
////                cell.accessoryType = .checkmark
////                data.accessoryType = .checkmark
//            }
//            self.tableView.deselectRow(at: indexPath, animated: true)
            print("here")
        }
    }
    
   
    
    @objc func handleDoneCellEvent1(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
        var mask = UIInterfaceOrientationMask()
            for data in self.tableView.qmui_staticCellDataSource?.cellDataSections.first ?? [] {
                if data.accessoryType == .checkmark {
                    mask = mask.union(UIInterfaceOrientationMask(rawValue: UInt(data.identifier)))
                }
            }
            
            let viewController = PrivacyViewController()
            // QMUICommonViewController 提供属性 supportedOrientationMask 用于控制界面所支持的显示方向，在 UIViewController (QMUI) 里会自动根据下一个要显示的界面去旋转设备的方向
           
            
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
    @objc func handleDoneCellEvent2(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
        var mask = UIInterfaceOrientationMask()
            for data in self.tableView.qmui_staticCellDataSource?.cellDataSections.first ?? [] {
                if data.accessoryType == .checkmark {
                    mask = mask.union(UIInterfaceOrientationMask(rawValue: UInt(data.identifier)))
                }
            }
            
            let viewController = TestHtmlCode()
            // QMUICommonViewController 提供属性 supportedOrientationMask 用于控制界面所支持的显示方向，在 UIViewController (QMUI) 里会自动根据下一个要显示的界面去旋转设备的方向
           
            
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
    
    @objc func handleDoneCellEvent3(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
        var mask = UIInterfaceOrientationMask()
            for data in self.tableView.qmui_staticCellDataSource?.cellDataSections.first ?? [] {
                if data.accessoryType == .checkmark {
                    mask = mask.union(UIInterfaceOrientationMask(rawValue: UInt(data.identifier)))
                }
            }
            
            let viewController = LoginViewController()
            // QMUICommonViewController 提供属性 supportedOrientationMask 用于控制界面所支持的显示方向，在 UIViewController (QMUI) 里会自动根据下一个要显示的界面去旋转设备的方向
           
            
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
    @objc func handleDoneCellEvent4(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
        var mask = UIInterfaceOrientationMask()
            for data in self.tableView.qmui_staticCellDataSource?.cellDataSections.first ?? [] {
                if data.accessoryType == .checkmark {
                    mask = mask.union(UIInterfaceOrientationMask(rawValue: UInt(data.identifier)))
                }
            }
            
            let viewController = LoginViewController()
            // QMUICommonViewController 提供属性 supportedOrientationMask 用于控制界面所支持的显示方向，在 UIViewController (QMUI) 里会自动根据下一个要显示的界面去旋转设备的方向
           
            
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
    
    
    @objc func handleDoneCellEvent(_ data: QMUIStaticTableViewCellData) {
        DispatchQueue.main.async {
        var mask = UIInterfaceOrientationMask()
            for data in self.tableView.qmui_staticCellDataSource?.cellDataSections.first ?? [] {
                if data.accessoryType == .checkmark {
                    mask = mask.union(UIInterfaceOrientationMask(rawValue: UInt(data.identifier)))
                }
            }
            
            let viewController = SettingViewController()
            // QMUICommonViewController 提供属性 supportedOrientationMask 用于控制界面所支持的显示方向，在 UIViewController (QMUI) 里会自动根据下一个要显示的界面去旋转设备的方向
            viewController.supportedOrientationMask = mask
            
            self.navigationController?.pushViewController(viewController, animated: true)
        }
    }
    
    private func descriptionString(_ mask: UIInterfaceOrientationMask) -> String {
        var string = ""
        if mask.contains(.portrait) {
            string += "Portrait"
        }
        if mask.contains(.landscapeLeft) {
            if !string.isEmpty {
                string += " | "
            }
            string += "Left"
        }
        if mask.contains(.landscapeRight) {
            if !string.isEmpty {
                string += " | "
            }
            string += "Right"
        }
        if mask.contains(.portraitUpsideDown) {
            if !string.isEmpty {
                string += " | "
            }
            string += "PortraitUpsideDown"
        }
        return string
    }
    
    
    private func handleWindowShowing() {
        let action1 = QMUIAlertAction(title: "不同意", style: .cancel) { (_) in
            
        }
        let action2 = QMUIAlertAction(title: "同意", style: .destructive) { (_) in
            
        }
        
        
        
        
        let alertController = QMUIAlertController(title: "温馨提示", message: "欢迎来到饮食时钟！\n我们甚至个人信息对你的重要性，也感谢你对我们的信任。为了更好地保护你的权益，同时遵守相关监管的要求，我们将通过《饮食时钟隐私权政策》向你说明我们会如何收集、存储、保护、使用及对外提供你的信息，并说明你享有的权利\n更多详情，敬请查阅《饮食时钟隐私权政策》全文", preferredStyle: .alert)
        alertController.add(action: action1)
        alertController.add(action: action2)
        alertController.show(true)
        return
    }
    
    
    
}
