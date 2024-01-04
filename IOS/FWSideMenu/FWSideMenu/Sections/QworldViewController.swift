//
//  QworldViewController.swift
//  FWSideMenu
//
//  Created by xfg on 2018/4/10.
//  Copyright © 2018年 xfg. All rights reserved.
//

import Foundation
import UIKit

class QworldViewController: QDCommonListViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = nil
        //tableView.separatorStyle = UITableViewCellSeparatorStyleNone

        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .done, target: self, action: nil)
        navigationItem.title = nil
        tableView.reloadData()
        self.tableView.tableFooterView = UIView()


        // 背景色设为白色

    }

    override func initDataSource() {
        dataSource = ["Account",
                      "FAQs",
        "Basic Information",
        "Tutorials",
        "Settings",
        "Contact Us",
        "About"]
    }



    override func didSelectCell(_ title: String) {
        //tableView.qmui_clearsSelection()
        var viewController: UIViewController?
        if title == "Account" {
            viewController = LoginViewController()
        } else if title == "FAQs" {
           viewController = QuestionViewController()
        }else if title == "Basic Information"{
           viewController = BaseInfoViewController(text: "hello")
        }else if title == "Settings"{
            viewController = SettingViewController()
        }else if title == "Tutorials"{
            viewController = HowtouseViewController()
        }else if title == "Contact Us"{
            viewController = SuggestionViewController()
        }else if title == "About"{
            viewController = AboutViewController()
        }
//        else if title == "体重统计"{
//            viewController = WeightViewController()
//        }else if title == "睡眠统计"{
//            viewController = SleepViewController()
//        }

        if let viewController = viewController {
            viewController.title = title
            navigationController?.pushViewController(viewController, animated: true)
        }

    }

    override func numberOfSections(in _: UITableView) -> Int {
        return 1
    }

    override func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        return 7
    }




    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        //view.frame = CGRectFlat(0, 300, view.bounds.width, 300)
        view.layer.zPosition = CGFloat(Float.leastNormalMagnitude)
//        tableView.frame = CGRectFlat(0, 300, view.bounds.width, view.bounds.height-500)


//        imagePositionButton1.frame = CGRectFlat(150, 400, 100,100)
//
//
//        fillButton1.frame = CGRectFlat(buttonMinX, 600, buttonSize.width, buttonSize.height)
//
//
//        separatorLayer1.frame = CGRect(x: 0, y: 600 - PixelOne, width: view.bounds.width, height: PixelOne)
//
//        var farme = separatorLayer1.frame
//
//
//        farme = separatorLayer1.frame

    }


//    @objc override func layoutTableView() {
//
//        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
//        let buttonSpacingHeight = QDButtonSpacingHeight
//        let buttonSize = CGSize(width: 260, height: 40)
//        let buttonMinX = view.bounds.width.center(buttonSize.width)
//        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
//        let shouldChangeTableViewFrame = view.bounds != tableView.frame
//        if shouldChangeTableViewFrame {
//            tableView.frame = CGRectFlat(0, 100, view.bounds.width, view.bounds.height-200)
//        }
//    }
//




//    override func viewWillAppear(_ animated: Bool) {
//        super.viewWillAppear(animated)
//
//        // 设置导航栏
//        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
//        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.resizableImage(imageName: "header_bg_message", edgeInsets: UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)), for: .default)
//    }
//
//    override func viewWillDisappear(_ animated: Bool) {
//        super.viewWillDisappear(animated)
//
//        // 设置导航栏
//        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.systemFont(ofSize: navTitleFont)]
//        self.navigationController?.navigationBar.setBackgroundImage(AppDelegate.getImageWithColor(color: UIColor.white), for: .default)
//    }
//
//    override var preferredStatusBarStyle: UIStatusBarStyle {
//        return .lightContent
//    }
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        self.navigationItem.title = "我的"
//    }





}
//
//class QworldViewController: QDCommonListViewController {
//
//    override var dataSource: [String] {
//        get {
//            return ["All System Fonts",
//                    "Default Line Height",
//                    "Theme",
//                    "Animation",
//                    "Log Manager"]
//        }
//        set {
//
//        }
//    }
//
//    override func setNavigationItems(_ isInEditMode: Bool, animated: Bool) {
//        super.setNavigationItems(isInEditMode, animated: animated)
//        title = "Lab"
//        navigationItem.rightBarButtonItem = UIBarButtonItem.item(image: UIImageMake("icon_nav_about"), target: self, action: #selector(handleAboutItemEvent))
//    }
//
//    @objc private func handleAboutItemEvent() {
//        //let viewController = QDAboutViewController()
//        //navigationController?.pushViewController(viewController, animated: true)
//    }
//
//    override func didSelectCell(_ title: String) {
//        var viewController: UIViewController?
//        if title == "All System Fonts" {
//            //viewController = QDAllSystemFontsViewController()
//        } else if title == "Default Line Height" {
//           // viewController = QDFontPointSizeAndLineHeightViewController()
//        } else if title == "Theme" {
//           // viewController = QDThemeViewController()
//        } else if title == "Animation" {
//          //  viewController = QDAnimationViewController()
//        } else if title == "Log Manager" {
//           // viewController = QDAllSystemFontsViewController()
//        }
//
//        if let viewController = viewController {
//            viewController.title = title
//            navigationController?.pushViewController(viewController, animated: true)
//        }
//    }
//}
//
//
//
//
//
//import Foundation
//import UIKit
//
//struct UserTable {
//    let name: String
//}
//
//extension UserTable {
//    static func createRecipes() -> [UserTable] {
//
//
//        let name_lists = ["账号","常见问题","基本信息","体重与睡眠","帮助","设置","用户留言","关于"]
////
//        var sampleElements: [UserTable] = []
//
//        for i in 0...7{
//            sampleElements.append(UserTable(name: name_lists[i]))
//        }
//
//
//
//
////        sampleElements.append(Recipe(photoName: "/Users/wanglab/Library/Developer/CoreSimulator/Devices/A8E42CDA-A1A6-46BD-A1A3-A188FE4C7C83/data/Containers/Data/Application/35522890-8E5E-4F6F-B80D-CD5F3F986AB8/Documents/DietClock/20201130003124jgtqji.jpg", startTime: "2020.11.17 8:55"))
////        sampleElements.append(Recipe(photoName: "mushroom_risotto.jpg", startTime: "2020.11.17 9:55"))
////        sampleElements.append(Recipe(photoName: "full_breakfast.jpg", startTime: "2020.11.17 10:55"))
////        sampleElements.append(Recipe(photoName: "hamburger.jpg", startTime: "2020.11.17 11:55"))
////        sampleElements.append(Recipe(photoName: "ham_and_egg_sandwich.jpg", startTime: "2020.11.17 12:55"))
//
//
//        return sampleElements
//
//}
//
//
//
//
//}
//
//






//
//class QworldViewController: UITableViewController {
//    let num = 5
//    var inputName:String = "thai_shrimp_cake.jpg"
//
//    var recipes = Recipe.createRecipes()
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        self.title = nil
//        //tableView.separatorStyle = UITableViewCellSeparatorStyleNone
//
//        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .done, target: self, action: nil)
//        navigationItem.title = nil
//        tableView.reloadData()
//        self.tableView.tableFooterView = UIView()
//
//
//        // 背景色设为白色
//
//    }
//
//
//   // static let shared = QDTableViewCellInsetsViewController()
//
//
////
////    func callBackBlock(_ block: @escaping swiftBlock) {
////        block(_:"闭包传值1")
////    }
////
////    //闭包：变量
////    var callBack : swiftBlock?
////    func useBlock(){
////        if callBack != nil {
////            callBack!("hamburger.jpg")
////        }
////    }
//
//
//
//
//
//    override func numberOfSections(in _: UITableView) -> Int {
//        return recipes.count
//    }
//
//    override func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
//        return 1
//    }
//
//
//
////    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat{
////        return 0.001
////    }
//
//    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
////        if section == 0 {
////            return "普通 cell"
////        } else if section == 1 {
////            return "使用 imageEdgeInsets"
////        } else if section == 2 {
////            return "使用 textLabelEdgeInsets"
////        } else if section == 3 {
////            return "使用 detailTextLabelEdgeInsets"
////        } else if section == 4 {
////            return "使用 accessoryEdgeInsets"
////        }
//        return nil
//    }
//
//    func qmui_tableView1(_ tableView: UITableView, cellWithIdentifier identifier: String) -> UITableViewCell {
//        var cell = tableView.dequeueReusableCell(withIdentifier: identifier)
//
//        if cell == nil {
 //           cell = QMUITableViewCell(tableView: self.tableView, style: .value1, reuseIdentifier: identifier)
////            cell!.imageView?.image = UIImage.qmui_image(shape: .oval, size: CGSize(width: 16, height: 16), lineWidth: 2, tintColor: QDCommonUI.randomThemeColor())
//
//
//            cell!.textLabel?.text = String(describing: QMUITableViewCell.self)
//            cell!.accessoryType = .disclosureIndicator
//        }
//        return cell ?? UITableViewCell()
//    }
//
//    override func tableView(_: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        guard let cell = qmui_tableView1(tableView, cellWithIdentifier: "cell") as? QMUITableViewCell else {
//            return UITableViewCell()
//        }
//
//
//         func load(fileName: String) -> UIImage? {
//            let fileURL = documentsUrl.appendingPathComponent(fileName)
//            do {
//                let imageData = try Data(contentsOf: fileURL)
//                return UIImage(data: imageData)
//            } catch {
//                print("Error loading image : \(error)")
//            }
//            return nil
//        }
//
//
//
//        // reset
//        cell.imageEdgeInsets = .zero
//        cell.textLabelEdgeInsets = .zero
//        cell.detailTextLabelEdgeInsets = .zero
//        cell.accessoryEdgeInsets = .zero
//
//
//        var documentsUrl: URL {
//            return FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
//        }
//
//
//
//        for i in 0...recipes.count{
//            if(indexPath.section == i){
//                //cell.imageView?.image = UIImage(named: recipes[i].photoName)
//
//                //var nsd = NSData(contentsOf:NSURL(string: "http://39.100.73.118/deeplearning_photo/uploads/0BxZ49nA200505.jpg") as! URL)
//
//              //  let imageData = Data(contentsOf: temp_string)
//
//
//                cell.imageView?.image = load(fileName: "20201130161741bdrghj.jpg")
//                //var img = UIImage(data: nsd! as Data);
//                //cell.imageView?.image = UIImage(data: NSData(contentsOf:NSURL(string: recipes[i].photoName) as! URL) as! Data)
//                //cell.imageView?.image = UIImage(contentsOfFile: recipes[i].photoName)
//                //cell.imageView?.kf.setImage(with: URL(string: recipes[i].photoName)!)
//                cell.textLabel?.text = recipes[i].startTime
//            }
//        }
//
//
//
//
////        if indexPath.section == 0 {
////            //cell.detailTextLabel?.text = "2020.11.16 6:30:00"
////            cell.imageView?.image = UIImage(named: "egg_benedict.jpg")
////            cell.textLabel?.text = "2020.11.16 6:30:00"
////        } else if indexPath.section == 1 {
////            cell.textLabel?.text = "2020.11.16 7:00:00"
////            //cell.detailTextLabel?.text = "2020.11.16 7:00:00"
////            cell.imageView?.image = UIImage(named: "green_tea.jpg")
////            //cell.imageEdgeInsets = UIEdgeInsets.init(top: 0, left: 30, bottom: 0, right: 0)
////        } else if indexPath.section == 2 {
////            cell.textLabel?.text = "2020.11.16 8:11:00"
////            //cell.detailTextLabel?.text = "2020.11.16 8:11:00"
////            //cell.textLabelEdgeInsets = UIEdgeInsets.init(top: -6, left: 30, bottom: 0, right: 0)
////            cell.imageView?.image = UIImage(named: "mushroom_risotto.jpg")
////        } else if indexPath.section == 3 {
////            cell.textLabel?.text = "2020.11.16 8:50:00"
////            //cell.detailTextLabel?.text = "2020.11.16 8:50:00"
////            cell.imageView?.image = UIImage(named: "creme_brelee.jpg")
////           // cell.detailTextLabelEdgeInsets = UIEdgeInsets.init(top: 6, left: 30, bottom: 0, right: 0);
////        } else if indexPath.section == 4 {
////            cell.textLabel?.text = "2020.11.16 8:55:00"
////            //cell.detailTextLabel?.text = "2020.11.16 8:55:00"
////            cell.imageView?.image = UIImage(named: "full_breakfast.jpg")
////            //cell.accessoryEdgeInsets = UIEdgeInsets.init(top: 0, left: 0, bottom: 0, right: 32);
////        }
//
//        return cell
//    }
//
//    override func tableView(_: UITableView, heightForRowAt _: IndexPath) -> CGFloat {
//        return TableViewCellNormalHeight + 10
//    }
//
//
//
////    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
////            return 0.1
////
////        }
//
//
//
//
//
//
//    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//
//
//        var viewController: UIViewController?
//        viewController = PictureDetailController()
//
//
//        let vc = PictureDetailController()
//
//        vc.imageName = recipes[indexPath.section].photoName
//        //vc.imageName = "green_tea.jpg"
//        navigationController?.pushViewController(vc, animated: true)
//
////        if let viewController = viewController {
////            viewController.title = title
////
////            inputName = "green_tea.jpg"
////
//////            func useBlock(){
//////                if callBack != nil {
//////                    callBack!("green_tea.jpg")
//////                }
//////            }
////
////            navigationController?.pushViewController(viewController, animated: true)
////        }
//        //tableView.deselectRow(at: indexPath, animated: true)
//    }
//
//
//
//    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if editingStyle == .delete {
//
//            recipes.remove(at: indexPath.row)
//            tableView.deleteRows(at: [indexPath], with: .bottom)
//        }
//    }
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
//        tableView.frame = CGRectFlat(0, 100, view.bounds.width, view.bounds.height)
//
//
////        imagePositionButton1.frame = CGRectFlat(150, 400, 100,100)
////
////
////        fillButton1.frame = CGRectFlat(buttonMinX, 600, buttonSize.width, buttonSize.height)
////
////
////        separatorLayer1.frame = CGRect(x: 0, y: 600 - PixelOne, width: view.bounds.width, height: PixelOne)
////
////        var farme = separatorLayer1.frame
////
////
////        farme = separatorLayer1.frame
//
//    }
//
//
//}










//class QworldViewController: UITableViewController {
//
//
//
//
//    var dataSource: Array<String> = []
//
//    var dataSourceWithDetailText: QMUIOrderedDictionary<String, String>?
//
//    override init(style: UITableView.Style) {
//        super.init(style: style)
//        initDataSource()
//    }
//
//    required init?(coder aDecoder: NSCoder) {
//        super.init(coder: aDecoder)
//        initDataSource()
//    }
//
//    /// 子类继承，可以不调super
//    open func initDataSource() {
//    }
//
//    open func didSelectCell(_ title: String) {
//    }
//
//    // MARK:  - UITableView Delegate & DataSource
//    override func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
//        if let dataSourceWithDetailText = dataSourceWithDetailText {
//            return dataSourceWithDetailText.count
//        }
//        return dataSource.count
//    }
//
//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let identifierNormal = "cellNormal"
//        var cell = tableView.dequeueReusableCell(withIdentifier: identifierNormal)
//        if cell == nil {
//            if dataSourceWithDetailText != nil {
//                cell = QMUITableViewCell(tableView: self.tableView, style: .subtitle, reuseIdentifier: identifierNormal)
//            } else {
//                cell = QMUITableViewCell(tableView: self.tableView, style: .value1, reuseIdentifier: identifierNormal)
//            }
//            cell?.accessoryType = .disclosureIndicator
//        }
//        if let dataSourceWithDetailText = dataSourceWithDetailText {
//            let keyName = dataSourceWithDetailText.allKeys[indexPath.row]
//            cell?.textLabel?.text = keyName
//            cell?.detailTextLabel?.text = dataSourceWithDetailText[keyName]
//        } else {
//            cell?.textLabel?.text = dataSource[indexPath.row]
//        }
//        cell?.textLabel?.font = UIFontMake(15)
//        cell?.detailTextLabel?.font = UIFontMake(13)
//        if let cell = cell as? QMUITableViewCell {
//            cell.updateCellAppearance(indexPath)
//        }
//        return cell ?? UITableViewCell()
//    }
//
//    override func tableView(_: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
//        if let dataSourceWithDetailText = dataSourceWithDetailText {
//            let keyName = dataSourceWithDetailText.allKeys[indexPath.row]
//            if let value = dataSourceWithDetailText[keyName], value.count > 0 {
//                return 64
//            }
//        }
//        return TableViewCellNormalHeight
//    }
//
//    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        var title: String?
//        if let dataSourceWithDetailText = dataSourceWithDetailText {
//            title = dataSourceWithDetailText.allKeys[indexPath.row]
//        } else {
//            title = dataSource[indexPath.row]
//        }
//        if let title = title{
//            didSelectCell(title)
//        }
//    }
//}
