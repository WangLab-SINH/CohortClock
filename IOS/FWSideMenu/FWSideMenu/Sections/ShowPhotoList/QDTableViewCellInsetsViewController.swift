////
////  TableViewController.swift
////  FWSideMenu
////
////  Created by wanglab on 2020/11/13.
////  Copyright © 2020 xfg. All rights reserved.
////
//
//import Foundation
//import UIKit
//
//
//
//class TableViewController: UIViewController,UITableViewDelegate,UITableViewDataSource{
//
//    var dataArr:Array<Goods>?
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        // Do any additional setup after loading the view, typically from a nib.
//
//        let goods1 = Goods()
//
//        goods1.name = "西凤酒－华山论剑"
//
//        goods1.price = "300.00"
//
//        goods1.desTitle = "西凤酒是中国四大名酒之一，曾荣获过万国博览会金奖。华山论剑西凤酒，中国峰峻品格的首倡者。"
//
//        goods1.coverIamge = "mushroom_risotto.jpg"
//
//
//
//        let goods2 = Goods()
//
//        goods2.name = "西凤酒－华山论剑"
//
//        goods2.price = "300.00"
//
//        goods2.desTitle = "西凤酒是中国四大名酒之一，曾荣获过万国博览会金奖。华山论剑西凤酒，中国峰峻品格的首倡者。"
//
//        goods2.coverIamge = "image"
//
//
//
//        let goods3 = Goods()
//
//        goods3.name = "西凤酒－华山论剑"
//
//        goods3.price = "300.00"
//
//        goods3.desTitle = "西凤酒是中国四大名酒之一，曾荣获过万国博览会金奖。华山论剑西凤酒，中国峰峻品格的首倡者。"
//
//        goods3.coverIamge = "image"
//
//
//
//        dataArr = [goods1,goods2,goods3]
//
//
//
//        let tableView = UITableView(frame:self.view.bounds,style:.plain)
//
//        self.view.addSubview(tableView)
//
//     tableView.register(NSClassFromString("GoodsTableViewCell"), forCellReuseIdentifier: "goodsCell")
//
//        tableView.delegate = self
//
//        tableView.dataSource = self
//
//    }
//
//
//
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        return dataArr!.count
//
//    }
//
//
//
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell = GoodsTableViewCell(style:UITableViewCell.CellStyle.default, reuseIdentifier: "goodsCell")
//
//
//
//        cell.setValueForCell(model: dataArr![indexPath.row])
//
//        return cell
//
//    }
//
//
//
//    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
//        return 100.00
//
//    }
//
//
//
//
//    override func didReceiveMemoryWarning() {
//        super.didReceiveMemoryWarning()
//
//        // Dispose of any resources that can be recreated.
//
//    }
//
//
//    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if editingStyle == .delete {
//            dataArr?.remove(at: indexPath.row)
//            tableView.deleteRows(at: [indexPath], with: .bottom)
//        }
//    }
//
//
//
//
//
//}
//
//
//  QDTableViewCellInsetsViewController.swift
//  QMUI.swift
//
//  Created by qd-hxt on 2018/4/18.
//  Copyright © 2018年 伯驹 黄. All rights reserved.
//

import UIKit
import Foundation
//typealias swiftBlock = (_ str: String) -> Void




struct Recipe {
    let photoName: String
    let startTime: String
}

extension Recipe {
    static func createRecipes() -> [Recipe] {
        
        var user_time_list: [String] = []
        var user_photo_list: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreate()
        let user_time: [String] = database.queryTableTime()
        let user_photo: [String] = database.queryTablePhotoName()
//
        var sampleElements: [Recipe] = []
        if(user_time.count > 0){
            let date = Date()
            let timeFormatter = DateFormatter()
            timeFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
            var current_time = timeFormatter.string(from: date) as String
            let current_day = String(current_time.split(separator: "-")[0])
            var temp_day: String = ""
            for i in 0...(user_time.count - 1){
                temp_day = String(user_time[i].split(separator: "-")[0])
                if(temp_day == current_day){
                    user_time_list.append(user_time[i])
                    user_photo_list.append(user_photo[i])
                }
            }



            if(user_time_list.count > 0){
                for i in 0...(user_time_list.count - 1){
                    sampleElements.append(Recipe(photoName: user_photo_list[i], startTime: user_time_list[i]))
                }
            }

        }
        
//        sampleElements.append(Recipe(photoName: "/Users/wanglab/Library/Developer/CoreSimulator/Devices/A8E42CDA-A1A6-46BD-A1A3-A188FE4C7C83/data/Containers/Data/Application/35522890-8E5E-4F6F-B80D-CD5F3F986AB8/Documents/DietClock/20201130003124jgtqji.jpg", startTime: "2020.11.17 8:55"))
//        sampleElements.append(Recipe(photoName: "mushroom_risotto.jpg", startTime: "2020.11.17 9:55"))
//        sampleElements.append(Recipe(photoName: "full_breakfast.jpg", startTime: "2020.11.17 10:55"))
//        sampleElements.append(Recipe(photoName: "hamburger.jpg", startTime: "2020.11.17 11:55"))
//        sampleElements.append(Recipe(photoName: "ham_and_egg_sandwich.jpg", startTime: "2020.11.17 12:55"))
        
        
        return sampleElements
    }
    
    
    
    static func createRecipes2() -> [Recipe] {
        
        var user_time_list: [String] = []
        var user_photo_list: [String] = []
        var database: Database!
        database = Database()
        database.tableLampCreateWeight()
        let user_time: [String] = database.queryTableWeightTime()
        let user_photo: [String] = database.queryTableWeight()
//
        var sampleElements: [Recipe] = []
        if(user_time.count > 0){
            let date = Date()
            let timeFormatter = DateFormatter()
            timeFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
            var current_time = timeFormatter.string(from: date) as String
            let current_day = String(current_time.split(separator: "-")[0])
            var temp_day: String = ""
            for i in 0...(user_time.count - 1){
                if(!(user_time[i]=="")){
                    temp_day = String(user_time[i].split(separator: " ")[0])
                    var temp_day_new: String = String(temp_day.split(separator: "-")[0])+"."+String(temp_day.split(separator: "-")[1])+"."+String(temp_day.split(separator: "-")[2])
                    if(temp_day_new == current_day){
                        user_time_list.append(user_time[i])
                        user_photo_list.append(user_photo[i])
                    }
                }
                
            }



            if(user_time_list.count > 0){
                for i in 0...(user_time_list.count - 1){
                    sampleElements.append(Recipe(photoName: user_photo_list[i], startTime: user_time_list[i]))
                }
            }

        }
        
        return sampleElements
    }
}







//class QDTableViewCellInsetsViewController: QDCommonTableViewController {
//
//    var dataArr:Array<Goods>?
//    let num = 5
//    var inputName:String = "thai_shrimp_cake.jpg"
//
//    var recipes = Recipe.createRecipes()
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        self.title = nil
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
//    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
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
//    func qmui_tableView(_ tableView: UITableView, cellWithIdentifier identifier: String) -> UITableViewCell {
//        var cell = tableView.dequeueReusableCell(withIdentifier: identifier)
//
//        if cell == nil {
//            cell = QMUITableViewCell(tableView: self.tableView, style: .subtitle, reuseIdentifier: identifier)
////            cell!.imageView?.image = UIImage.qmui_image(shape: .oval, size: CGSize(width: 16, height: 16), lineWidth: 2, tintColor: QDCommonUI.randomThemeColor())
//            cell!.imageView?.image = UIImage(named: "image5@2x.png")
//
//
//            cell!.textLabel?.text = String(describing: QMUITableViewCell.self)
//            cell!.accessoryType = .disclosureIndicator
//        }
//        return cell ?? UITableViewCell()
//    }
//
//    override func tableView(_: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        guard let cell = qmui_tableView(tableView, cellWithIdentifier: "cell") as? QMUITableViewCell else {
//            return UITableViewCell()
//        }
//
//
//
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
//
//        for i in 0...recipes.count{
//            if(indexPath.section == i){
//                cell.imageView?.image = UIImage(named: recipes[i].photoName)
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
//    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
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
//    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if editingStyle == .delete {
//
//            recipes.remove(at: indexPath.row)
//            tableView.deleteRows(at: [indexPath], with: .bottom)
//        }
//    }
//
//}





class QDTableViewCellInsetsViewController: UITableViewController {
    
    var dataArr:Array<Goods>?
    let num = 5
    var inputName:String = "thai_shrimp_cake.jpg"
    
    var current_table = "photo"
    
    var recipes = Recipe.createRecipes()
    var recipes2 = Recipe.createRecipes2()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = nil
        //tableView.separatorStyle = UITableViewCellSeparatorStyleNone
        
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .done, target: self, action: nil)
        navigationItem.title = nil
        tableView.reloadData()
        self.tableView.tableFooterView = UIView()
        
        
        

        let headerView = UIView(frame: CGRectFlat(view.bounds.width , 0, 150, 50))

        headerView.addSubview(fillButton1)

        headerView.addSubview(fillButton2)
        self.tableView.tableHeaderView = headerView
        
        
//        view.superview?.addSubview(fillButton1)
//        view.superview?.addSubview(fillButton2)
        
        
        // 背景色设为白色
        
    }
    
    
   // static let shared = QDTableViewCellInsetsViewController()
    
    
//
//    func callBackBlock(_ block: @escaping swiftBlock) {
//        block(_:"闭包传值1")
//    }
//
//    //闭包：变量
//    var callBack : swiftBlock?
//    func useBlock(){
//        if callBack != nil {
//            callBack!("hamburger.jpg")
//        }
//    }
    
    
    
    

//    override func numberOfSections(in _: UITableView) -> Int {
//        return recipes.count
//    }
    
    override func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        if(current_table=="photo"){
            return recipes.count
        }else{
            return recipes2.count
        }
        
    }
    
    
    
//    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat{
//        return 0.001
//    }

    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
//        if section == 0 {
//            return "普通 cell"
//        } else if section == 1 {
//            return "使用 imageEdgeInsets"
//        } else if section == 2 {
//            return "使用 textLabelEdgeInsets"
//        } else if section == 3 {
//            return "使用 detailTextLabelEdgeInsets"
//        } else if section == 4 {
//            return "使用 accessoryEdgeInsets"
//        }
        return nil
    }
    
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Save", for: .normal)
        fillButton.addTarget(self, action:#selector(cancelInfo1), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var fillButton2: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Cancel", for: .normal)
        fillButton.addTarget(self, action:#selector(cancelInfo2), for: .touchUpInside)
        return fillButton
    }()
    
    
    

    func qmui_tableView(_ tableView: UITableView, cellWithIdentifier identifier: String) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: identifier)
        
        if cell == nil {
            if(current_table=="photo"){
                cell = QMUITableViewCell(tableView: self.tableView, style: .subtitle, reuseIdentifier: identifier)
    //            cell!.imageView?.image = UIImage.qmui_image(shape: .oval, size: CGSize(width: 16, height: 16), lineWidth: 2, tintColor: QDCommonUI.randomThemeColor())
                cell!.imageView?.image = UIImage(named: "image5@2x.png")
                
                cell!.textLabel?.text = String(describing: QMUITableViewCell.self)
                cell!.accessoryType = .disclosureIndicator
            }else{
                cell = QMUITableViewCell(tableView: self.tableView, style: .subtitle, reuseIdentifier: identifier)
                cell!.textLabel?.text = String(describing: QMUITableViewCell.self)
                cell!.detailTextLabel?.text = "textLabelEdgeInsets"
                cell!.accessoryType = .disclosureIndicator
            }
            
            
        }
        return cell ?? UITableViewCell()
    }
    
    override func tableView(_: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = qmui_tableView(tableView, cellWithIdentifier: "cell") as? QMUITableViewCell else {
            return UITableViewCell()
        }
        
        if(current_table=="photo"){
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
           
           
           
           // reset
           cell.imageEdgeInsets = .zero
           cell.textLabelEdgeInsets = .zero
           cell.detailTextLabelEdgeInsets = .zero
           cell.accessoryEdgeInsets = .zero
           
           
           var documentsUrl: URL {
               return FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
           }



           for i in 0...recipes.count{
               if(indexPath.row == i){
                   cell.imageView?.image = load(fileName: recipes[i].photoName)
                   cell.textLabel?.text = recipes[i].startTime
                   cell.detailTextLabel?.text = ""
               }
           }
        }else{

         
           cell.textLabelEdgeInsets = .zero
           cell.detailTextLabelEdgeInsets = .zero
           cell.accessoryEdgeInsets = .zero
           




           for i in 0...recipes2.count{
               //if(indexPath.section == i){
               if(indexPath.row == i){
                   cell.imageView?.image = nil
                   cell.detailTextLabel?.text = recipes2[i].photoName
                   cell.textLabel?.text = recipes2[i].startTime
               }
           }
        }
         
        
        
        
        
//        if indexPath.section == 0 {
//            //cell.detailTextLabel?.text = "2020.11.16 6:30:00"
//            cell.imageView?.image = UIImage(named: "egg_benedict.jpg")
//            cell.textLabel?.text = "2020.11.16 6:30:00"
//        } else if indexPath.section == 1 {
//            cell.textLabel?.text = "2020.11.16 7:00:00"
//            //cell.detailTextLabel?.text = "2020.11.16 7:00:00"
//            cell.imageView?.image = UIImage(named: "green_tea.jpg")
//            //cell.imageEdgeInsets = UIEdgeInsets.init(top: 0, left: 30, bottom: 0, right: 0)
//        } else if indexPath.section == 2 {
//            cell.textLabel?.text = "2020.11.16 8:11:00"
//            //cell.detailTextLabel?.text = "2020.11.16 8:11:00"
//            //cell.textLabelEdgeInsets = UIEdgeInsets.init(top: -6, left: 30, bottom: 0, right: 0)
//            cell.imageView?.image = UIImage(named: "mushroom_risotto.jpg")
//        } else if indexPath.section == 3 {
//            cell.textLabel?.text = "2020.11.16 8:50:00"
//            //cell.detailTextLabel?.text = "2020.11.16 8:50:00"
//            cell.imageView?.image = UIImage(named: "creme_brelee.jpg")
//           // cell.detailTextLabelEdgeInsets = UIEdgeInsets.init(top: 6, left: 30, bottom: 0, right: 0);
//        } else if indexPath.section == 4 {
//            cell.textLabel?.text = "2020.11.16 8:55:00"
//            //cell.detailTextLabel?.text = "2020.11.16 8:55:00"
//            cell.imageView?.image = UIImage(named: "full_breakfast.jpg")
//            //cell.accessoryEdgeInsets = UIEdgeInsets.init(top: 0, left: 0, bottom: 0, right: 32);
//        }
        
        return cell
    }
    
    override func tableView(_: UITableView, heightForRowAt _: IndexPath) -> CGFloat {
        return TableViewCellNormalHeight + 10
    }
    
    
    @objc func cancelInfo1() {
        print("asdadas")
        current_table="photo"
        recipes = Recipe.createRecipes()
        tableView.reloadData()
      
    }
    @objc func cancelInfo2() {
        print("asdadas")
        current_table="index"
        recipes2 = Recipe.createRecipes2()
        tableView.reloadData()
      
    }
//    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
//            return 0.1
//
//        }

    
    
    
    
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        
        var viewController: UIViewController?
        viewController = PictureDetailController()
        
        
        let vc = PictureDetailController()
        if(current_table=="photo"){
            vc.imageName = recipes[indexPath.section].photoName
            //vc.imageName = "green_tea.jpg"
            navigationController?.pushViewController(vc, animated: true)
        }

       
        
//        if let viewController = viewController {
//            viewController.title = title
//
//            inputName = "green_tea.jpg"
//
////            func useBlock(){
////                if callBack != nil {
////                    callBack!("green_tea.jpg")
////                }
////            }
//
//            navigationController?.pushViewController(viewController, animated: true)
//        }
        //tableView.deselectRow(at: indexPath, animated: true)
    }
    
    
    
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
        
        
        tableView.frame = CGRectFlat(0, 100, view.bounds.width, view.bounds.height)

        fillButton1.frame = CGRectFlat((view.bounds.width / 2)+25, 0, 150, 50)
        
        fillButton2.frame = CGRectFlat(50, 0, 150, 50)

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

}


extension QDTableViewCellInsetsViewController{
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            //debugPrint(indexPath.row)
            debugPrint(indexPath.section)
            //recipes.remove(at: indexPath.row)
            //tableView.deleteRows(at: [indexPath], with: .bottom)
            recipes.remove(at: indexPath.section)
            tableView.deleteRows(at: [indexPath], with: .bottom)
            //tableView.deleteRows(at: <#T##[IndexPath]#>, with: <#T##UITableView.RowAnimation#>)
        }
    }
}
