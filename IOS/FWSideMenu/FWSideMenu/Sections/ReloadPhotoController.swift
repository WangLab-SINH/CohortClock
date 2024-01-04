//
//  ReloadPhotoController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/10/18.
//  Copyright © 2020 xfg. All rights reserved.
//


import Foundation
import UIKit
import LXFitManager
import ZLPhotoBrowser
import Photos
import Alamofire
import SQLite
import MMKV
//import LXPhotosManager


var selectedImageReload: UIImage? = nil

class ReloadPhotoController: UIViewController{
    
    
    let mmkv = MMKV(mmapID: "testSwift", mode: MMKVMode.singleProcess)

    var time_select_string: String = ""
    
    let dialog = SelectionDialog(title: "", closeButtonTitle: "关闭")

    var models = [FileInfoProtocol]()
    
    deinit {
        print("\(self)内存已释放")
    }
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Save", for: .normal)
        fillButton.addTarget(self, action:#selector(uploadPhoto), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var separatorLayer1: CALayer = {
        let separatorLayer = QDCommonUI.generateSeparatorLayer()
        return separatorLayer
    }()
    
    
    private lazy var fillButton2: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .green)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("Select time", for: .normal)
        fillButton.addTarget(self, action:#selector(showPicker), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var separatorLayer2: CALayer = {
        let separatorLayer = QDCommonUI.generateSeparatorLayer()
        return separatorLayer
    }()
    
    
    var addView = AddPhotosView(frame: CGRect(x: 0, y: 50, width: UIScreen.main.bounds.width, height: 600),config: SinglePhotoConfig())
   
    var global_use_camera_flag = 0
    
    
    private lazy var imagePositionButton1: QMUIButton = {
        let imagePositionButton = QMUIButton()
        imagePositionButton.tintColorAdjustsTitleAndImage = (QDThemeManager.shared.currentTheme?.themeTintColor)!
        imagePositionButton.imagePosition = .top // 将图片位置改为在文字上方
        imagePositionButton.spacingBetweenImageAndTitle = 8

        imagePositionButton.setImage(UIImageMake("camera_photo333.png"), for: .normal)
        //imagePositionButton.addTarget(self, action: #selector(openCamera), for: .touchUpInside)
        
        
        imagePositionButton.addTarget(self, action: #selector(openCamera1), for: .touchUpInside)
        
        
        
        
        imagePositionButton.titleLabel?.font = UIFontMake(11)
        imagePositionButton.qmui_borderPosition = [.top, .bottom]
        return imagePositionButton
    }()
    
    
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
           self.view.backgroundColor = UIColor.white
         //  self.navigationItem.title = "图片添加"
        
//           let model = FileModel()
//           model.width = 250
//           model.height = 250
//           model.imgUrl = datas[0]
//
//           let model1 = FileModel()
//           model1.width = 500
//           model1.height = 375
//           model1.imgUrl = datas[1]
//
//           let model2 = FileModel()
//           model2.width = 500
//           model2.height = 313
//           model2.imgUrl = datas[2]
//
//           models.append(model1)
//           models.append(model2)
//           models.append(model)
                        
        view.backgroundColor = UIColor.white
//        var config = SinglePhotoConfig()
////        config.type = SinglePhotoType.video
//        let addView = AddPhotosView(frame: CGRect(x: 0, y: 40, width: UIScreen.main.bounds.width, height: 600),config: config)
        view.addSubview(addView)
        addView.delegate = self
        
        addView.loadBlock = { model, imgView in
            if model.isNetWork {
                imgView.kf.setImage(with: URL(string: model.imgUrl)!)
            }else{
                imgView.image = model.image
            }
        }
        
        

//        let selector = UIStoryboard(name: "WWCalendarTimeSelector", bundle: nil).instantiateInitialViewController() as! WWCalendarTimeSelector
//        selector.delegate = self
//        selector.optionCurrentDate = singleDate
//        selector.optionCurrentDates = Set(multipleDates)
//        selector.optionCurrentDateRange.setStartDate(multipleDates.first ?? singleDate)
//        selector.optionCurrentDateRange.setEndDate(multipleDates.last ?? singleDate)
//        selector.optionStyles.showDateMonth(false)
//        selector.optionStyles.showMonth(false)
//        selector.optionStyles.showYear(false)
//        selector.optionStyles.showTime(true)
//        present(selector, animated: true, completion: nil)

        
        
        view.addSubview(imagePositionButton1)
        
        view.addSubview(fillButton1)
        view.layer.addSublayer(separatorLayer1)
        view.addSubview(fillButton2)
        view.layer.addSublayer(separatorLayer2)
        
        
        
        
        
        
        dialog.addItem(item: "拍照上传",
                       icon: UIImage(named: "update_ios_new")!,
                       didTapHandler: { () in
                        self.dialog.close()
                        self.openCamera_button()
                        
        })
        
        
        dialog.addItem(item: "手动输入",
                       icon: UIImage(named: "edit_ios_new")!,
                       didTapHandler: { () in

                        self.dialog.close()
                        
                        
                        var viewController: UIViewController?
                        viewController = SelectFoodViewController()
                        if let viewController = viewController {
                            self.navigationController?.pushViewController(viewController, animated: true)
                        }
                        
                        
        })
        
        
        
        
        
        

//        addView.pubPhotoModels = models
//        addView.loadCurrentViewMaxY = { maxY in
//            print("---------\(maxY)")
//        }
    }
    
    
    func aboveViewController() -> UIViewController? {
        var aboveController = UIApplication.shared.delegate?.window??.rootViewController
        while aboveController?.presentedViewController != nil {
            aboveController = aboveController?.presentedViewController
        }
        return aboveController
    }
    
    
    @objc func openCamera1() {
        let hand = mmkv?.bool(forKey: "hand") ?? false
        if(hand == false){
            if(selectedImage != nil){
                
            }
            else{
                addView.click_add_button = 0
                let picker = UIImagePickerController()
                picker.delegate = addView
                //判断是否有上传相册权限
                if PrivilegeManager.isSupportCamera {
                    picker.sourceType = .camera
                    picker.modalPresentationStyle = .fullScreen
                    aboveViewController()?.present(picker, animated: true, completion: nil)
                    
                    global_use_camera_flag = 1
                }else{
                    let msg = "启动相机失败,请在手机设置中打开相机权限"
                    let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                    alertController.modalPresentationStyle = .fullScreen

                   aboveViewController()?.present(alertController, animated: true, completion: nil)
                }
            }
        }
        else{
            if(selectedImage != nil){
                
            }
            else{
                addView.click_add_button = 0
                
                dialog.show()
            }
        }
        
        
        
    }
    
    
    
    @objc func openCamera() {
        
        if(selectedImage != nil){
            
        }
        else{
            addView.click_add_button = 0
            let picker = UIImagePickerController()
            picker.delegate = addView
            //判断是否有上传相册权限
            if PrivilegeManager.isSupportCamera {
                picker.sourceType = .camera
                picker.modalPresentationStyle = .fullScreen
                aboveViewController()?.present(picker, animated: true, completion: nil)
                
                global_use_camera_flag = 1
            }else{
                let msg = "启动相机失败,请在手机设置中打开相机权限"
                let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                alertController.modalPresentationStyle = .fullScreen

               aboveViewController()?.present(alertController, animated: true, completion: nil)
            }
        }
    }
    
    
    func openCamera_button() {
        
        if(selectedImage != nil){
            
        }
        else{
            addView.click_add_button = 0
            let picker = UIImagePickerController()
            picker.delegate = addView
            //判断是否有上传相册权限
            if PrivilegeManager.isSupportCamera {
                picker.sourceType = .camera
                picker.modalPresentationStyle = .fullScreen
                aboveViewController()?.present(picker, animated: true, completion: nil)
                
                global_use_camera_flag = 1
            }else{
                let msg = "启动相机失败,请在手机设置中打开相机权限"
                let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                alertController.modalPresentationStyle = .fullScreen

               aboveViewController()?.present(alertController, animated: true, completion: nil)
            }
        }
    }
    
    
    
    
    
    @objc func showPicker() {
        let timeSelector = TimeSelector()
        timeSelector.timeSelected = {
            (timeSelector) in
            self.setLabelFromDate(timeSelector.date)
        }
        
        let date = Date()
        let timeFormatter = DateFormatter()
        timeFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
        var current_time = timeFormatter.string(from: date) as String
        let current_times = current_time.split(separator: "-")[1]
        var current_hour: Double = Double(String(current_times.split(separator: ":")[0]))!
        let current_minute: Double = Double(String(current_times.split(separator: ":")[1]))!
        var isAM:Bool = false
        if(current_hour >= 12){
            isAM = false
            current_hour -= 12
        }else{
            isAM = true
        }
        timeSelector.overlayAlpha = 0.8
        timeSelector.clockTint = timeSelector_rgb(0, 230, 0)
        timeSelector.minutes = Int(current_minute)
        timeSelector.hours = Int(current_hour)
        timeSelector.isAm = isAM
        timeSelector.presentOnView(view: self.view)
        
    }
    
    func setLabelFromDate(_ date: Date) {
        let df = DateFormatter()
        df.dateStyle = .none
        df.timeStyle = .long
        df.dateFormat = "yyyy.MM.dd-HH:mm:ss"
        fillButton2.setTitle(df.string(from: date), for: .normal)
        time_select_string = df.string(from: date)
        //selectedTime.text = df.string(from: date)
    }
    
    
    
    
    
    
    
    
    
    
    @objc func uploadPhoto(){
        if(selectedImage != nil){
            let images = selectedImage

            //let images = image[0].UIImage
            //let imageData = images[0].jpegData(compressionQuality: 1.0)!
            let imageData = images!.jpegData(compressionQuality: 1.0)!
            let uuid = UIDevice.current.identifierForVendor?.uuidString
            let date = Date()
            let timeFormatter = DateFormatter()
            //日期显示格式，可按自己需求显示
            timeFormatter.dateFormat = "yyyy.MM.dd-HH:mm:ss"
            let strNowTime = timeFormatter.string(from: date) as String

            let url_string = (uuid ?? "default value") + "_" + time_select_string + "_image.jpg"

            let uuidName: String = uuid ?? "default value"


            let manager = FileManager.default
            //        创建一个字符串对象，该字符串对象表示文档目录下的一个文件夹
            let baseFileUrl = NSHomeDirectory() + "/Documents/DietClock"
        //        使用文件管理对象，判断文件夹是否存在，并把结果储存在常量中
            let exist = manager.fileExists(atPath: baseFileUrl)
        //        如果文件夹不存在，则执行之后的代码
            if !exist
            {
                do{
        //                创建指定位置上的文件夹
                    try manager.createDirectory(atPath: baseFileUrl, withIntermediateDirectories: true, attributes: nil)
                    //debugPrint("Succes to create folder")
                }
                catch{
                    debugPrint("Error to create folder")
                }
            }
            let tempTimeAll:[Substring] = time_select_string.split(separator: "-")
            let tempTime:[Substring] = String(tempTimeAll[0]).split(separator: ".")
            let tempYear:String = String(tempTime[0])
            let tempMonth:String = String(tempTime[1])
            let tempDay:String = String(tempTime[2])
            let timeTimeSecond:[Substring] = String(tempTimeAll[1]).split(separator: ":")
            let tempHour:String = String(timeTimeSecond[0])
            let tempMinute:String = String(timeTimeSecond[1])
            let tempSecond:String = String(timeTimeSecond[2])
            let arrRandom = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
            let randomName:[String] = arrRandom.sample(size:6)!
            let newRandomName = String(randomName[0]) + String(randomName[1]) + String(randomName[2]) + String(randomName[3]) + String(randomName[4]) + String(randomName[5])
            let imageName = tempYear + tempMonth + tempDay + tempHour + tempMinute + tempSecond + newRandomName + ".jpg"
            let dt:String = NSHomeDirectory().appending("/Documents/DietClock/").appending(imageName) as String;
                   /*打印路径： 可通过 finder 然后 快捷键 ctrl+shift+g 前往文件夹功能 进入 如下文件夹
                    /Users/admin/Library/Developer/CoreSimulator/Devices/8C4DA6F7-4431-402A-92A9-E83087F5159D/data/Containers/Data/Application/4935114D-926D-4EDA-918D-FA14EE023A34/Documents/2.png
                   */
                   //将Image文件写入 如上的文件夹
            let image = UIImage(data: imageData)
            try? image!.pngData()?.write(to: URL(fileURLWithPath: dt))






            let dt2:String = "DietClock/".appending(imageName) as String;

            var database: Database!
            database = Database()
            database.tableLampCreate()
            database.tableLampInsertItem(user_name: uuidName, user_time: time_select_string, photo_type: "food", photo_url: dt2, photo_kind: "", photo_cal: "", photo_web_url: "", workday: "", weekend: "")
            //database.queryTableLamp()
            //ios_upload_test

            AF.upload(
                   multipartFormData: { multipartFormData in
                     multipartFormData.append(imageData,
                                              withName: "image",
                                              fileName:  url_string,
                                              mimeType: "image/jpeg")
                   },
                   to: "http://39.100.73.118/deeplearning_photo/ios_upload_test_new.php", usingThreshold: UInt64.init(), method: .post)
                .responseJSON { response in
                    debugPrint(response)
                    self.tabBarController?.tabBarController?.selectedIndex = 0
                }
        }
        else{
            guard let parentView = navigationController?.view else {
                return
            }
            QMUITips.showError(text: "请先选择需要上传的图片", in: parentView, hideAfterDelay: 2)
        }
       

    }
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        
        imagePositionButton1.frame = CGRectFlat(150, 300, 100,100)
        
        
        fillButton1.frame = CGRectFlat(buttonMinX, 500, buttonSize.width, buttonSize.height)


        separatorLayer1.frame = CGRect(x: 0, y: 500 - PixelOne, width: view.bounds.width, height: PixelOne)
        
        fillButton2.frame = CGRectFlat(buttonMinX, 450, buttonSize.width, buttonSize.height)


        separatorLayer2.frame = CGRect(x: 0, y: 450 - PixelOne, width: view.bounds.width, height: PixelOne)
        
        
       
        

        var farme = separatorLayer1.frame


        farme = separatorLayer1.frame

    }

}





extension ReloadPhotoController: AddPhotosViewDelegate {
    func addPhotosView(with datasource: [FileInfoProtocol]) {
        print("--=-=-=-=",datasource)
        if(datasource.count > 0){
            selectedImage = datasource[0].image

        }else{
            selectedImage = nil
        }







    }

    func addPhotosView(videoPlay addPhotosView1 : AddPhotosView, model: FileInfoProtocol){
        print("--=-=-=-=视频播放")

    }

    func addPhotosView(longPress addPhotosView1: AddPhotosView, model: FileInfoProtocol) {

        if model.isNetWork {
            UIImageView().kf.setImage(with: URL(string: model.imgUrl), placeholder: nil, options: nil, progressBlock: nil) { (image, error, cacge, url) in
               SaveAsset.saveImageToAsset(with:  image ?? UIImage()) { (type) in
                   if case .success = type {
                        let msg = "保存图片成功"
                           let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
                           alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                           alertController.modalPresentationStyle = .fullScreen
                          self.present(alertController, animated: true, completion: nil)
                      }else if case let  .failure(error) = type {
                             let alertController = UIAlertController(title: nil, message: error, preferredStyle: .alert)
                             alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                             alertController.modalPresentationStyle = .fullScreen
                            self.present(alertController, animated: true, completion: nil)
                      }
                   }
               }
        }else {
            SaveAsset.saveImageToAsset(with:  model.image) { (type) in
                if case .success = type {
                 let msg = "保存图片成功"
                    let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                    alertController.modalPresentationStyle = .fullScreen
                   self.present(alertController, animated: true, completion: nil)
               }else if case let  .failure(error) = type {
                      let alertController = UIAlertController(title: nil, message: error, preferredStyle: .alert)
                      alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                      alertController.modalPresentationStyle = .fullScreen
                     self.present(alertController, animated: true, completion: nil)
               }
            }
        }
    }
}
