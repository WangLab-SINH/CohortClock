//
//  ViewController.swift
//  Example
//
//  Created by long on 2020/8/11.
//

import UIKit
import ZLPhotoBrowser
import Photos
import Alamofire
import SQLite


extension Array {
    /// 从数组中返回一个随机元素
    public var sample: Element? {
        //如果数组为空，则返回nil
        guard count > 0 else { return nil }
        let randomIndex = Int(arc4random_uniform(UInt32(count)))
        return self[randomIndex]
    }
     
    /// 从数组中从返回指定个数的元素
    ///
    /// - Parameters:
    ///   - size: 希望返回的元素个数
    ///   - noRepeat: 返回的元素是否不可以重复（默认为false，可以重复）
    public func sample(size: Int, noRepeat: Bool = false) -> [Element]? {
        //如果数组为空，则返回nil
        guard !isEmpty else { return nil }
         
        var sampleElements: [Element] = []
         
        //返回的元素可以重复的情况
        if !noRepeat {
            for _ in 0..<size {
                sampleElements.append(sample!)
            }
        }
        //返回的元素不可以重复的情况
        else{
            //先复制一个新数组
            var copy = self.map { $0 }
            for _ in 0..<size {
                //当元素不能重复时，最多只能返回原数组个数的元素
                if copy.isEmpty { break }
                let randomIndex = Int(arc4random_uniform(UInt32(copy.count)))
                let element = copy[randomIndex]
                sampleElements.append(element)
                //每取出一个元素则将其从复制出来的新数组中移除
                copy.remove(at: randomIndex)
            }
        }
        
        return sampleElements
    }
}


class ViewController: UIViewController{

    var collectionView: UICollectionView!
    
    var selectedImages: [UIImage] = []
    
    var selectedAssets: [PHAsset] = []
    
    var isOriginal = false
    
    var takeSelectedAssetsSwitch: UISwitch!
    
    
    
    private lazy var fillButton1: QMUIFillButton = {
        let fillButton = QMUIFillButton(fillType: .blue)
        fillButton.titleLabel?.font = UIFontMake(14)
        fillButton.setTitle("上传", for: .normal)
        fillButton.addTarget(self, action:#selector(librarySelectPhoto), for: .touchUpInside)
        return fillButton
    }()
    
    
    private lazy var separatorLayer1: CALayer = {
        let separatorLayer = QDCommonUI.generateSeparatorLayer()
        return separatorLayer
    }()

    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
       // self.title = "拍食物"
        
        self.navigationItem.title = "拍食物"
        self.view.backgroundColor = .white
        
        func createBtn(_ title: String, _ action: Selector) -> UIButton {
            let btn = UIButton(type: .custom)
            btn.setTitle(title, for: .normal)
            btn.titleLabel?.font = UIFont.systemFont(ofSize: 14)
            btn.addTarget(self, action: action, for: .touchUpInside)
            btn.backgroundColor = .black
            btn.layer.cornerRadius = 5
            btn.layer.masksToBounds = true
            return btn
        }
        
        let configBtn = createBtn("进入相册选择", #selector(librarySelectPhoto))
        self.view.addSubview(configBtn)
        configBtn.snp.makeConstraints { (make) in
            make.top.equalTo(self.view.snp.topMargin).offset(20)
            make.left.equalTo(self.view).offset(30)
        }
        
//        let previewSelectBtn = createBtn("快速选择", #selector(previewSelectPhoto))
//        self.view.addSubview(previewSelectBtn)
//        previewSelectBtn.snp.makeConstraints { (make) in
//            make.top.equalTo(self.view.snp.topMargin).offset(20)
//            make.left.equalTo(configBtn.snp.right).offset(20)
//        }
//
//        let libratySelectBtn = createBtn("进入相册选择", #selector(librarySelectPhoto))
//        self.view.addSubview(libratySelectBtn)
//        libratySelectBtn.snp.makeConstraints { (make) in
//            make.top.equalTo(self.view.snp.topMargin).offset(20)
//            make.left.equalTo(previewSelectBtn.snp.right).offset(20)
//        }
//
        
//        let ps = ZLPhotoPreviewSheet()
//        ps.selectImageBlock = { [weak self] (images, assets, isOriginal) in
//            // your code
//        }
//        ps.showPhotoLibrary(sender: self)
        
//        let cameraBtn = createBtn("自定义相机", #selector(showCamera))
//        self.view.addSubview(cameraBtn)
//        cameraBtn.snp.makeConstraints { (make) in
//            make.left.equalTo(configBtn.snp.left)
//            make.top.equalTo(configBtn.snp.bottom).offset(20)
//        }
        
        let uploadBtn = createBtn("上传", #selector(showCamera))
        self.view.addSubview(uploadBtn)
        uploadBtn.snp.makeConstraints { (make) in
            make.left.equalTo(configBtn.snp.right).offset(20)
            make.top.equalTo(self.view).offset(20)
        }
        
//        let takeLabel = UILabel()
//        takeLabel.font = UIFont.systemFont(ofSize: 14)
//        takeLabel.text = "记录已选择照片："
//        self.view.addSubview(takeLabel)
//        takeLabel.snp.makeConstraints { (make) in
//            make.left.equalTo(configBtn.snp.left)
//            make.top.equalTo(uploadBtn.snp.bottom).offset(20)
//        }
        
//        self.takeSelectedAssetsSwitch = UISwitch()
//        self.takeSelectedAssetsSwitch.isOn = false
//        self.view.addSubview(self.takeSelectedAssetsSwitch)
//        self.takeSelectedAssetsSwitch.snp.makeConstraints { (make) in
//            make.left.equalTo(takeLabel.snp.right).offset(20)
//            make.centerY.equalTo(takeLabel.snp.centerY)
//        }
        
        let layout = UICollectionViewFlowLayout()
        self.collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        self.collectionView.backgroundColor = .clear
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.view.addSubview(self.collectionView)

        self.collectionView.snp.makeConstraints { (make) in
            make.top.equalTo(uploadBtn.snp.bottom).offset(30)
            make.left.bottom.right.equalTo(self.view)
        }
                view.addSubview(fillButton1)
                view.layer.addSublayer(separatorLayer1)
        
        self.collectionView.register(ImageCell.classForCoder(), forCellWithReuseIdentifier: "ImageCell")
        

    }
    
    @objc func configureClick() {
        let vc = PhotoConfigureViewController()
        self.showDetailViewController(vc, sender: nil)
    }
    
    @objc func previewSelectPhoto() {
        ZLPhotoConfiguration.default().editImageClipRatios = [.custom, .wh1x1, .wh3x4, .wh16x9, ZLImageClipRatio(title: "2 : 1", whRatio: 2 / 1)]
        let ac = ZLPhotoPreviewSheet(selectedAssets: self.takeSelectedAssetsSwitch.isOn ? self.selectedAssets : [])
        ac.selectImageBlock = { [weak self] (images, assets, isOriginal) in
            self?.selectedImages = images
            self?.selectedAssets = assets
            self?.isOriginal = isOriginal
            self?.collectionView.reloadData()
            debugPrint("\(images)   \(assets)   \(isOriginal)")

            
        }
        ac.showPreview(animate: true, sender: self)
    }
    
    //********************************************************************
//    struct HTTPBinResponse: Decodable { let url: String }
    
    @objc func librarySelectPhoto() {
        ZLPhotoConfiguration.default().editImageClipRatios = [.custom, .wh1x1, .wh3x4, .wh16x9, ZLImageClipRatio(title: "2 : 1", whRatio: 2 / 1)]
//        let ac = ZLPhotoPreviewSheet(selectedAssets: self.takeSelectedAssetsSwitch.isOn ? self.selectedAssets : [])
//        let ac = ZLPhotoPreviewSheet(selectedAssets: self.selectedAssets)
//        ac.selectImageBlock = { [weak self] (images, assets, isOriginal) in
//            self?.selectedImages = images
//            self?.selectedAssets = assets
//            self?.collectionView.reloadData()
//            debugPrint("\(images)   \(assets)   \(isOriginal)")
//            debugPrint("\(images[0])")
//            let imageData = images[0].jpegData(compressionQuality: 1.0)!
//            let uuid = UIDevice.current.identifierForVendor?.uuidString
//            let date = Date()
//            let timeFormatter = DateFormatter()
//            //日期显示格式，可按自己需求显示
//            timeFormatter.dateFormat = "yyy.MM.dd-HH:mm:ss"
//            let strNowTime = timeFormatter.string(from: date) as String
//
//            let url_string = (uuid ?? "default value") + "_" + strNowTime + "_image.jpg"
//            
//            let uuidName: String = uuid ?? "default value"
//            
//            
//            let manager = FileManager.default
//            //        创建一个字符串对象，该字符串对象表示文档目录下的一个文件夹
//            let baseFileUrl = NSHomeDirectory() + "/Documents/DietClock"
//    //        使用文件管理对象，判断文件夹是否存在，并把结果储存在常量中
//            let exist = manager.fileExists(atPath: baseFileUrl)
//    //        如果文件夹不存在，则执行之后的代码
//            if !exist
//            {
//                do{
//    //                创建指定位置上的文件夹
//                    try manager.createDirectory(atPath: baseFileUrl, withIntermediateDirectories: true, attributes: nil)
//                    //debugPrint("Succes to create folder")
//                }
//                catch{
//                    debugPrint("Error to create folder")
//                }
//            }
//            let tempTimeAll:[Substring] = strNowTime.split(separator: "-")
//            let tempTime:[Substring] = String(tempTimeAll[0]).split(separator: ".")
//            let tempYear:String = String(tempTime[0])
//            let tempMonth:String = String(tempTime[1])
//            let tempDay:String = String(tempTime[2])
//            let timeTimeSecond:[Substring] = String(tempTimeAll[1]).split(separator: ":")
//            let tempHour:String = String(timeTimeSecond[0])
//            let tempMinute:String = String(timeTimeSecond[1])
//            let tempSecond:String = String(timeTimeSecond[2])
//            let arrRandom = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
//            let randomName:[String] = arrRandom.sample(size:6)!
//            let newRandomName = String(randomName[0]) + String(randomName[1]) + String(randomName[2]) + String(randomName[3]) + String(randomName[4]) + String(randomName[5])
//            let imageName = tempYear + tempMonth + tempDay + tempHour + tempMinute + tempSecond + newRandomName + ".jpg"
//            let dt:String = NSHomeDirectory().appending("/Documents/DietClock/").appending(imageName) as String;
//                   /*打印路径： 可通过 finder 然后 快捷键 ctrl+shift+g 前往文件夹功能 进入 如下文件夹
//                    /Users/admin/Library/Developer/CoreSimulator/Devices/8C4DA6F7-4431-402A-92A9-E83087F5159D/data/Containers/Data/Application/4935114D-926D-4EDA-918D-FA14EE023A34/Documents/2.png
//                   */
//                   //将Image文件写入 如上的文件夹
//            let image = UIImage(data: imageData)
//            try? image!.pngData()?.write(to: URL(fileURLWithPath: dt))
//                   
//
//
//            
//
//            
//            
//            
//            var database: Database!
//            database = Database()
//            database.tableLampCreate()
//            database.tableLampInsertItem(user_name: uuidName, user_time: strNowTime, photo_type: "food", photo_url: dt, photo_kind: "", photo_cal: "", photo_web_url: "", workday: "", weekend: "")
//            //database.queryTableLamp()
//            
//            
//            AF.upload(
//                   multipartFormData: { multipartFormData in
//                     multipartFormData.append(imageData,
//                                              withName: "image",
//                                              fileName:  url_string,
//                                              mimeType: "image/jpeg")
//                   },
//                   to: "http://39.100.73.118/deeplearning_photo/ios_upload_test.php", usingThreshold: UInt64.init(), method: .post)
//                .responseJSON { response in
//                    debugPrint(response)
//                   self?.tabBarController?.selectedIndex = 0
//                }
////                .responseData { response in
////                        if let data = response.value {
////                            debugPrint(response.data)
////                        }
////                    }
//
//
////                .responseDecodable(of: HTTPBinResponse.self) { response in
////                        debugPrint(response)
////                    }
//            
//
////            let imgData = UIImageJPEGRepresentation(images[0], 0.2)!
////
////
////            let parameters = ["name": "rname"] //Optional for extra parameter
////
////           AF.upload(multipartFormData: { multipartFormData in
////                   multipartFormData.append(imgData, withName: "fileset",fileName: "file.jpg", mimeType: "image/jpg")
////                   for (key, value) in parameters {
////                           multipartFormData.append(value.data(using: String.Encoding.utf8)!, withName: key)
////                       } //Optional for extra parameters
////               },
////           to:"http://39.100.73.118/deeplearning_photo/ios_upload_test.php")
////           { (result) in
////               switch result {
////               case .success(let upload, _, _):
////
////                   upload.uploadProgress(closure: { (progress) in
////                       print("Upload Progress: \(progress.fractionCompleted)")
////                   })
////
////                   upload.responseJSON { response in
////                        print(response.result.value)
////                   }
////
////               case .failure(let encodingError):
////                   print(encodingError)
////               }
////           }
//        }
//        ac.showPhotoLibrary(sender: self)
    }
    
    //********************************************************************
    
//
//    func uploadToPHP(){
//        AF.upload(multipartFormData: { (multipartFormData) in
//            multipartFormData.append(ecodeData!, withName: "data")
//            multipartFormData.append(jpegImage!, withName: "avatar", fileName: "avatar"+".jpeg", mimeType: "image/jpeg")
//                    }, to: "https://www.ka5188.com/app/api/v1/user/uploadImg")
//    }
    
    
    
    @objc func showCamera() {
        let camera = ZLCustomCamera()
        camera.takeDoneBlock = { [weak self] (image, videoUrl) in
            self?.save(image: image, videoUrl: videoUrl)
        }
        self.showDetailViewController(camera, sender: nil)
    }
    
    func save(image: UIImage?, videoUrl: URL?) {
        let hud = ZLProgressHUD(style: ZLPhotoConfiguration.default().hudStyle)
        if let image = image {
            hud.show()
            ZLPhotoManager.saveImageToAlbum(image: image) { [weak self] (suc, asset) in
                if suc, let at = asset {
                    self?.selectedImages = [image]
                    self?.selectedAssets = [at]
                    self?.collectionView.reloadData()
                } else {
                    debugPrint("保存图片到相册失败")
                }
                hud.hide()
            }
        } else if let videoUrl = videoUrl {
            hud.show()
            ZLPhotoManager.saveVideoToAblum(url: videoUrl) { [weak self] (suc, asset) in
                if suc, let at = asset {
                    self?.fetchImage(for: at)
                } else {
                    debugPrint("保存视频到相册失败")
                }
                hud.hide()
            }
        }
    }
    
    func fetchImage(for asset: PHAsset) {
        let option = PHImageRequestOptions()
        option.resizeMode = .fast
        option.isNetworkAccessAllowed = true
        
        PHImageManager.default().requestImage(for: asset, targetSize: CGSize(width: 100, height: 100), contentMode: .aspectFill, options: option) { (image, info) in
            var downloadFinished = false
            if let info = info {
                downloadFinished = !(info[PHImageCancelledKey] as? Bool ?? false) && (info[PHImageErrorKey] == nil)
            }
            let isDegraded = (info?[PHImageResultIsDegradedKey] as? Bool ?? false)
            if downloadFinished, !isDegraded {
                self.selectedImages = [image!]
                self.selectedAssets = [asset]
                self.collectionView.reloadData()
            }
        }
    }
    
    
    
    
    
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
        let buttonSpacingHeight = QDButtonSpacingHeight
        let buttonSize = CGSize(width: 260, height: 40)
        let buttonMinX = view.bounds.width.center(buttonSize.width)
        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)

        fillButton1.frame = CGRectFlat(buttonMinX, 600, buttonSize.width, buttonSize.height)


        separatorLayer1.frame = CGRect(x: 0, y: 600 - PixelOne, width: view.bounds.width, height: PixelOne)

        var farme = separatorLayer1.frame


        farme = separatorLayer1.frame

    }
    
}


extension ViewController: UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 2
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 2
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        var columnCount: CGFloat = (UI_USER_INTERFACE_IDIOM() == .pad) ? 6 : 4
        if UIApplication.shared.statusBarOrientation.isLandscape {
            columnCount += 2
        }
        let totalW = collectionView.bounds.width - (columnCount - 1) * 2
        let singleW = totalW / columnCount
        return CGSize(width: singleW, height: singleW)
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.selectedImages.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ImageCell", for: indexPath) as! ImageCell
        
        cell.imageView.image = self.selectedImages[indexPath.row]
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let ac = ZLPhotoPreviewSheet()
        ac.selectImageBlock = { [weak self] (images, assets, isOriginal) in
            self?.selectedImages = images
            self?.selectedAssets = assets
            self?.isOriginal = isOriginal
            self?.collectionView.reloadData()
            debugPrint("\(images)   \(assets)   \(isOriginal)")
        }
        ac.previewAssets(sender: self, assets: self.selectedAssets, index: indexPath.row, isOriginal: self.isOriginal)
    }
    
}




