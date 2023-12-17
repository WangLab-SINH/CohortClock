//
//  LXPhotoViewController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/11/8.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
import UIKit
import LXFitManager
import ZLPhotoBrowser
import Photos
import Alamofire
import SQLite
//import LXPhotosManager


var selectedImage: UIImage? = nil

class LXAddPhotoViewController: UIViewController, UIImagePickerControllerDelegate & UINavigationControllerDelegate {
//    let datas = [
//                 "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1661707474,1451343575&fm=26&gp=0.jpg",
//                 "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3159064993,1446035142&fm=26&gp=0.jpg",
//                 "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2712496081,4225310564&fm=26&gp=0.jpg"
//             ]
    var models = [FileInfoProtocol]()
    
    //let config = SinglePhotoConfig()
    //addView = AddPhotosView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 600),config: config)
    var addView = AddPhotosView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 600),config: SinglePhotoConfig())
    deinit {
        print("\(self)内存已释放")
    }
    
    
    var global_use_camera_flag = 0
    
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("上传", for: .normal)
        fillButton.addTarget(self, action:#selector(uploadPhoto), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var separatorLayer1: CALayer = {
        let separatorLayer = QDCommonUI.generateSeparatorLayer()
        return separatorLayer
    }()
    
    
    private lazy var imagePositionButton1: QMUIButton = {
        let imagePositionButton = QMUIButton()
        imagePositionButton.tintColorAdjustsTitleAndImage = (QDThemeManager.shared.currentTheme?.themeTintColor)!
        imagePositionButton.imagePosition = .top // 将图片位置改为在文字上方
        imagePositionButton.spacingBetweenImageAndTitle = 8

        imagePositionButton.setImage(UIImageMake("camera_photo333.png"), for: .normal)

        imagePositionButton.titleLabel?.font = UIFontMake(11)
        imagePositionButton.qmui_borderPosition = [.top, .bottom]
        imagePositionButton.addTarget(self, action: #selector(openCamera), for: .touchUpInside)
        return imagePositionButton
    }()
    
    
    
    var temp_addView = AddPhotosView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 600),config: SinglePhotoConfig())
    
    
    
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
        
//        config.type = SinglePhotoType.video
//        let config = SinglePhotoConfig()
//        addView = AddPhotosView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 600),config: config)
        addView.delegate = self
        addView.loadBlock = { model, imgView in
            if model.isNetWork {
                imgView.kf.setImage(with: URL(string: model.imgUrl)!)
            }else{
                imgView.image = model.image
            }
        }
        
        temp_addView = addView
        
        
        view.addSubview(addView)

        

        
        
        view.addSubview(imagePositionButton1)
        
        view.addSubview(fillButton1)
        view.layer.addSublayer(separatorLayer1)

//        addView.pubPhotoModels = models
//        addView.loadCurrentViewMaxY = { maxY in
//            print("---------\(maxY)")
//        }
    }
    
    var documentsUrl: URL {
        return FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
    }
    
    
    private func save(image: UIImage, fileName: String) -> String {
        //let fileName = "FileName"
        let fileURL = documentsUrl.appendingPathComponent(fileName)
        if let imageData = image.jpegData(compressionQuality: 1.0) {
           try? imageData.write(to: fileURL, options: .atomic)
           return fileName // ----> Save fileName
        }
        print("Error saving image")
        return ""
    }
    
    func aboveViewController() -> UIViewController? {
        var aboveController = UIApplication.shared.delegate?.window??.rootViewController
        while aboveController?.presentedViewController != nil {
            aboveController = aboveController?.presentedViewController
        }
        return aboveController
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
        
        
        
        
        
//        let datas = [
//                     "camera_photo333.png"
//                 ]
//        
//        models.removeAll()
//        let model = FileModel()
//        model.width = 250
//        model.height = 250
//        model.imgUrl = datas[0]
//        
//        models.append(model)
//        addView.pubPhotoModels = models
//           let picker = UIImagePickerController()
//           picker.delegate = addView
//           //判断是否有上传相册权限
//           if PrivilegeManager.isSupportCamera {
//               picker.sourceType = .camera
//               picker.modalPresentationStyle = .fullScreen
//               aboveViewController()?.present(picker, animated: true, completion: nil)
//           }else{
//               let msg = "启动相机失败,请在手机设置中打开相机权限"
//               let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
//               alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
//               alertController.modalPresentationStyle = .fullScreen
//
//            aboveViewController()?.present(alertController, animated: true, completion: nil)
//           }
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
            timeFormatter.dateFormat = "yyy.MM.dd-HH:mm:ss"
            let strNowTime = timeFormatter.string(from: date) as String

            let url_string = (uuid ?? "default value") + "_" + strNowTime + "_image.jpg"

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
            let tempTimeAll:[Substring] = strNowTime.split(separator: "-")
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
            let image = UIImage(data: imageData)!
            let save_name: String = save(image: image, fileName: imageName)
            //try? image!.pngData()?.write(to: URL(fileURLWithPath: dt))






            var database: Database!
            database = Database()
            database.tableLampCreate()
            database.tableLampInsertItem(user_name: uuidName, user_time: strNowTime, photo_type: "food", photo_url: save_name, photo_kind: "", photo_cal: "", photo_web_url: "", workday: "", weekend: "")
            //database.queryTableLamp()


            AF.upload(
                   multipartFormData: { multipartFormData in
                     multipartFormData.append(imageData,
                                              withName: "image",
                                              fileName:  url_string,
                                              mimeType: "image/jpeg")
                   },
                   to: "http://39.100.73.118/deeplearning_photo/ios_upload_test.php", usingThreshold: UInt64.init(), method: .post)
                .responseJSON { response in
                    debugPrint(response)
                    self.tabBarController?.selectedIndex = 0
                }
            
            addView = AddPhotosView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 600),config: SinglePhotoConfig())
            
            viewDidLoad()
            
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

        
        imagePositionButton1.frame = CGRectFlat(150, 400, 100,100)
        
        
        fillButton1.frame = CGRectFlat(buttonMinX, 600, buttonSize.width, buttonSize.height)


        separatorLayer1.frame = CGRect(x: 0, y: 600 - PixelOne, width: view.bounds.width, height: PixelOne)

        var farme = separatorLayer1.frame


        farme = separatorLayer1.frame

    }

}





extension LXAddPhotoViewController: AddPhotosViewDelegate {
    func addPhotosView(with datasource: [FileInfoProtocol]) {
//        print("--=-=-=-=",datasource)
//        if(datasource.count > 0){
//            selectedImage = datasource[0].image
//
//        }else{
//            selectedImage = nil
//        }

        
        if(datasource.count > 0){
            print("--=-=-=-=",datasource)
            var datasource_temp = datasource[(datasource.count - 1)]
            //var datasource_temp = datasource[0]
            var datasource_new: [FileInfoProtocol] = []
            datasource_new.append(datasource_temp)
            selectedImage = datasource_new[0].image
            
            
            
            
            
            if(global_use_camera_flag == 1 && addView.click_add_button == 0){
                SaveAsset.saveImageToAsset(with:  datasource_new[0].image) { (type) in
                    if case .success = type {
//                     let msg = "保存图片成功"
//                        let alertController = UIAlertController(title: nil, message: msg, preferredStyle: .alert)
//                        alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
//                        alertController.modalPresentationStyle = .fullScreen
//                       self.present(alertController, animated: true, completion: nil)
                   }else if case let  .failure(error) = type {
                          let alertController = UIAlertController(title: nil, message: error, preferredStyle: .alert)
                          alertController.addAction(UIAlertAction(title: "确定", style: .default, handler: nil))
                          alertController.modalPresentationStyle = .fullScreen
                         self.present(alertController, animated: true, completion: nil)
                   }
                }
                global_use_camera_flag = 0
                
            }
            
            
            
            
            
            
            
             
        }
        
        else{
            selectedImage = nil
        }
        
        
        
        
        
        
    }
    
    func addPhotosView(videoPlay addPhotosView : AddPhotosView, model: FileInfoProtocol){
        print("--=-=-=-=视频播放")

    }

    func addPhotosView(longPress addPhotosView: AddPhotosView, model: FileInfoProtocol) {
        
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
