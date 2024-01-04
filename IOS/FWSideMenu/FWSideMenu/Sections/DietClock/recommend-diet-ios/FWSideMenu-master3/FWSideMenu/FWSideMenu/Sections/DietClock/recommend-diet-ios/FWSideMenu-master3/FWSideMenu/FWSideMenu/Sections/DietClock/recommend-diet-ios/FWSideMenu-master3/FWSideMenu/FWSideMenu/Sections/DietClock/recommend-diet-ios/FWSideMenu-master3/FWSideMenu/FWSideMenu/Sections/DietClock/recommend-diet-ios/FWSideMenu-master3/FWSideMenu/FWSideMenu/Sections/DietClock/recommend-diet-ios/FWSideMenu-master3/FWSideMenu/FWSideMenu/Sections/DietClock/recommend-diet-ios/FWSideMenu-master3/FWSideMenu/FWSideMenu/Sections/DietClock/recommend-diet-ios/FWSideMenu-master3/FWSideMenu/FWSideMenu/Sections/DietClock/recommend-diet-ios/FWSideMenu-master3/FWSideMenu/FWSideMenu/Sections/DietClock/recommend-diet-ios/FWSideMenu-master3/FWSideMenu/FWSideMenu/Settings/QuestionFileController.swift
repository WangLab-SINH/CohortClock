////
////  QuestionFileController.swift
////  FWSideMenu
////
////  Created by 池宇豪 on 2021/3/3.
////  Copyright © 2021 xfg. All rights reserved.
////
//import Foundation
//import UIKit
//import Photos
//import Alamofire
//import SQLite
//import SelectableTextView
//
//class QuestionViewController: UIViewController, SelectableTextViewDelegate{
//
//    var textView1: SelectableTextView = SelectableTextView(frame:CGRect(x:0,y:0,width:400,height:200))
//    var textView2: SelectableTextView = SelectableTextView(frame:CGRect(x:0,y:100,width:400,height:200))
//    var heightConstraint: NSLayoutConstraint!
//
//    var hashtagFrame: CGRect? {
//        let frames = textView1.framesOfWordsMatchingValidator(HashtagTextValidator())
//        return frames.first
//    }
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//           self.view.backgroundColor = UIColor.white
//
//        textView1 = SelectableTextView(frame:CGRect(x:0,y:0,width:view.bounds.width,height:200))
//        textView2 = SelectableTextView(frame:CGRect(x:0,y:100,width:view.bounds.width,height:200))
//        textView1.delegate = self
//        textView2.delegate = self
//
//
//        var textView3 = UITextView(frame:CGRect(x:0,y:300,width:view.bounds.width,height:200))
//
//        textView1.lineBreakMode = .byCharWrapping
//
//        view.backgroundColor = UIColor.white
//        textView1.numberOfLines = 1
//        textView1.tag = 1
//        textView1.text = "1.什么是生物节律？\n 生物节律是指生物体比如我们人类在生理、行为指标等方面出现的周期性特征，比如我们的体温，情绪，血压等都在以24小时为周期发生有规律的变化，我们的睡眠-觉醒行为也是一种常见的生物节律现象。"
//        textView1.truncationMode = .truncateTail
//        let attributes: [NSAttributedString.Key: Any] = [HighlightedTextSelectionAttributes.SelectedBackgroundColorAttribute : UIColor.purple.withAlphaComponent(0.5)]
//        let collapsedNumberOfLines = max(Int(1), 1)
//        textView1.addExpansionButton(collapsedState: ("More...", collapsedNumberOfLines),
//                                    expandedState: ("Less", 0),
//                                    attributes: attributes)
////        textView1.addExpansionButton(collapsedState: ("More...", collapsedNumberOfLines),
////                                    expandedState: ("Less", 0),
////                                    attributes: attributes)
//
//        textView1.registerValidator(HashtagTextValidator()) { (text, validator) in
//            self.createParticles(seconds: 1)
//        }
//
//
//        textView2.text = "你好2,I'm{Imadetosolveheproblemofhighlightingandselectingspecific text in UILabel and UITextView. An example is a 啊啊啊 like #Stars or an unsafe link like http://google.com\0!"
//        textView2.truncationMode = .truncateTail
//        textView2.addExpansionButton(collapsedState: ("More...", 2),
//                                     expandedState: ("Less", 0),
//                                        attributes: attributes)
//
//
//
//        textView3.text = "1.什么是生物节律？\n 生物节律是指生物体比如我们人类在生理、行为指标等方面出现的周期性特征，比如我们的体温，情绪，血压等都在以24小时为周期发生有规律的变化，我们的睡眠-觉醒行为也是一种常见的生物节律现象。"
//
//
//        view.addSubview(textView1)
//        view.addSubview(textView2)
//        view.addSubview(textView3)
//
//
//
////        }
//    }
//
//    func selectableTextViewContentHeightDidChange(textView: SelectableTextView, oldHeight: CGFloat, newHeight: CGFloat) {
//        //heightConstraint.constant = min(newHeight, view.bounds.height)
//        if(oldHeight == 0){
//            view.setNeedsLayout()
//        }
//        else{
//            if(textView.tag == 1){
//                textView2.frame = CGRectFlat(0,(100 + newHeight - oldHeight),400,200)
//                view.setNeedsLayout()
//            }
//        }
//
//    }
//
//
//
//    func createParticles(seconds: TimeInterval) {
//
//        if let frame = hashtagFrame {
//            let particleEmitter = CAEmitterLayer()
//            let center = CGPoint(x: frame.origin.x + frame.width / 2, y: frame.origin.y + frame.height / 2)
//            particleEmitter.emitterPosition = center
//            particleEmitter.emitterShape = CAEmitterLayerEmitterShape.point
//            particleEmitter.emitterSize = frame.size
//
//            let cell = makeEmitterCell()
//
//            particleEmitter.emitterCells = [cell]
//
//            textView1.layer.addSublayer(particleEmitter)
//
//            DispatchQueue.main.asyncAfter(deadline: .now() + seconds) {
//                particleEmitter.removeFromSuperlayer()
//            }
//        }
//    }
//
//    func makeEmitterCell() -> CAEmitterCell {
//        let cell = CAEmitterCell()
//        let image = UIImage(named: "star")?.withRenderingMode(.alwaysTemplate)
//        cell.contents = image?.cgImage
//        cell.birthRate = 12
//        cell.lifetime = 1.0
//        cell.lifetimeRange = 0.2
//        cell.velocity = 50
//        cell.velocityRange = 20
//        cell.emissionRange = CGFloat.pi * 2
//        cell.spin = 2
//        cell.spinRange = 3
//        cell.scaleRange = 0.5
//        cell.scaleSpeed = -0.05
//        cell.alphaSpeed = 0.5
//        cell.alphaRange = 0
//        cell.scale = 1.0
//        cell.scaleSpeed = 0.5
//        cell.color = UIColor.yellow.cgColor
//
//        return cell
//    }
//
//
//
//    override func viewDidLayoutSubviews() {
//        super.viewDidLayoutSubviews()
//
////        let contentMinY = qmui_navigationBarMaxYInViewCoordinator
////        let buttonSpacingHeight = QDButtonSpacingHeight
////        let buttonSize = CGSize(width: 260, height: 40)
////        let buttonMinX = view.bounds.width.center(buttonSize.width)
////        let buttonOffsetY = buttonSpacingHeight.center(buttonSize.height)
////
////
////        textView1.frame = CGRectFlat(0, 0, view.bounds.width,200)
////
////        textView2.frame = CGRectFlat(0, 100, view.bounds.width,200)
//
//
//
//    }
//
//}
//
//
//public extension SelectableTextView {
//
//    public override func observeValue(forKeyPath keyPath: String?, of object: Any?, change: [NSKeyValueChangeKey : Any]?, context: UnsafeMutableRawPointer?) {
//
//            if let change = change as? [NSKeyValueChangeKey: NSValue],
//                let oldSize = change[NSKeyValueChangeKey.oldKey]?.cgSizeValue,
//                let newSize = change[NSKeyValueChangeKey.newKey]?.cgSizeValue,
//                oldSize.height != newSize.height
//            {
//                let additions = topTextInsets + bottomTextInsets
//
//                if(self.tag == 1){
//                    print(self.text)
//                }
//
//                delegate?.selectableTextViewContentHeightDidChange(textView: self, oldHeight: oldSize.height + additions, newHeight: newSize.height + additions)
//
//
//            }
//        }
//
//}



//
//  CollapsibleTableViewController.swift
//  ios-swift-collapsible-table-section
//
//  Created by Yong Su on 5/30/16.
//  Copyright © 2016 Yong Su. All rights reserved.
//

import UIKit
import MMKV
//
// MARK: - View Controller
//
class QuestionViewController: UITableViewController {
    
    var sections = sectionsData
    
    override func viewDidLoad() {
        super.viewDidLoad()
     
        
        // Auto resizing the height of the cell
        tableView.estimatedRowHeight = 44.0
        tableView.rowHeight = UITableView.automaticDimension
        
        self.title = "常见问题"
    }
    
}

//
// MARK: - View Controller DataSource and Delegate
//
extension QuestionViewController {

    override func numberOfSections(in tableView: UITableView) -> Int {
        return sections.count
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return sections[section].collapsed ? 0 : sections[section].items.count
    }
    
    // Cell
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: CollapsibleTableViewCell = tableView.dequeueReusableCell(withIdentifier: "cell") as? CollapsibleTableViewCell ??
            CollapsibleTableViewCell(style: .default, reuseIdentifier: "cell")
        
        let item: Item = sections[indexPath.section].items[indexPath.row]
        
        cell.nameLabel.text = item.name
        cell.detailLabel.text = item.detail
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
    
    // Header
    override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: "header") as? CollapsibleTableViewHeader ?? CollapsibleTableViewHeader(reuseIdentifier: "header")
        
        header.titleLabel.text = sections[section].name
        header.arrowLabel.text = ">"
        header.setCollapsed(sections[section].collapsed)
        
        header.section = section
        header.delegate = self
        
        return header
    }
    
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 44.0
    }
    
    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 1.0
    }

}

//
// MARK: - Section Header Delegate
//
extension QuestionViewController: CollapsibleTableViewHeaderDelegate {
    
    func toggleSection(_ header: CollapsibleTableViewHeader, section: Int) {
        let collapsed = !sections[section].collapsed
        
        // Toggle collapse
        sections[section].collapsed = collapsed
        header.setCollapsed(collapsed)
        
        tableView.reloadSections(NSIndexSet(index: section) as IndexSet, with: .automatic)
    }
    
}


public var sectionsData: [Section] = [
    Section(name: "1.什么是生物节律？", items: [
        Item(name: "", detail: "生物节律是指生物体比如我们人类在生理、行为指标等方面出现的周期性特征，比如我们的体温，情绪，血压等都在以24小时为周期发生有规律的变化，我们的睡眠-觉醒行为也是一种常见的生物节律现象。")
       
    ]),
    Section(name: "2.生物节律和饮食的关系是什么？", items: [
        Item(name: "", detail: "很多器官和组织的生理功能都要与人的进食行为相适应，比如小肠分泌消化酶来促进食物的消化与吸收，肝脏分泌胆汁，胰腺分泌消化酶。食物信号是一种可以独立地调节身体生物钟的影响因子，这种调控对于维持人体代谢和行为的同步化具有重要的意义。生物钟在人体的消化器官里也会存在，比如肝脏、胰腺以及胃肠等部位。比如肝脏的生物钟对于维持肝脏内部的稳态具有重要作用，主要负责调节肝脏的酶的分泌和新陈代谢，肝脏中生物节律的紊乱，会影响饮食后的食物的营养的消化与吸收，严重会引发肝部相关疾病，比如脂肪肝、肝炎，甚至癌症。")
        
    ]),
    Section(name: "3.这个App基于的节律生物学原理是什么？", items: [
        Item(name: "", detail: "人的饮食本身具有明显的节律性，空腹与进食都会改变人体的代谢状态。在人与动物中的昼夜节律模型的研究表明，在不改变饮食种类的情况下将能量摄入的时间限制在一定时间之内，可以带来多方面的生理益处。这个App是在这个原理并结合人工智能算法的基础上研制的。")
        
    ]),
    Section(name: "4.什么是限制性饮食，它的好处有哪些？", items: [
        Item(name: "", detail: "限制性饮食最早在上世纪就已经被提出了, 是指通过控制进食的方式来减轻或维持体重。这种行为本质上是限制每日卡路里摄入量或卡路里摄入总量。由于美食越来越丰富，人们很容易摄入超出自身活动需要的热量，从而导致超重甚至过度肥胖。限制性饮食通常会被女性所采用以维持体重，并逐渐被更多的人用来降低体重或维持体重，以满足人们审美和健康生活的需要。\n好处：\n限制性饮食通过减少能量的摄入量来减轻机体主要内脏器官的负担，避免机体稳态的破坏和代谢紊乱的发生，可以有效地预防肥胖的发生，控制体重。此外，限制性饮食还可以降低糖尿病和心血管疾病的发病率，减缓衰老速度，有效地预防慢性疾病。")
       
    ]),
    Section(name: "5.什么是时间限制性饮食？", items: [
        Item(name: "", detail: "时间限制性饮食，顾名思义，就是通过限制饮食的时间窗口来进行限制性饮食。时间限制性饮食旨在维持一致的进食和禁食的周期，来维持非常强劲的昼夜节律。时间限制性饮食可以人为的有意增加内脏器官的节律性调控，进而减少肥胖的发生，改善睡眠质量，减少系统性炎症，维持肠道的稳态，对人体的健康会产生十分积极的影响。")
        
    ]),
    Section(name: "6.限制性饮食和体重有什么关系？", items: [
        Item(name: "", detail: "许多研究发现限制性饮食是一个减轻体重的有效方法。这其中有两方面的原因，一方面是进食的时间减少了，肝脏以及胃肠得到休息的时间延长；另一方面是因为身体摄入的总卡路里数也减少了。")
        
    ]),
    Section(name: "7.限制性饮食和疾病以及衰老有什么关系？", items: [
        Item(name: "", detail: "限制性饮食会调动胰岛素信号通路、TOR通路，AMPK信号转导途径等多个通路，这些通路可能会相互作用，或者在调控人体反应机制的多个方面有着十分重要的作用，进而会对疾病的发生有着一定的影响。例如，限制性饮食可以通过改善胰岛素敏感性来降低空腹血糖和胰岛素浓度，对于Ⅱ型糖尿病，慢性炎症、肥胖症以及高血压等疾病具有预防作用。此外，限制性饮食对于减少各种慢性顽疾和肿瘤的发生具有积极的预防和治疗作用。\n根据多项研究和临床试验表明，限制性饮食还对大多数与衰老有关的多种疾病具有预防作用，即限制性饮食是一种具有延缓衰老作用的营养干预措施。因此从长远来看，限制性饮食对于促进健康，提高生活质量，延长寿命具有积极影响。")
       
    ]),
    Section(name: "8.水是饮食吗？", items: [
        Item(name: "", detail: "不是。水可以为人体提供所需的矿物质，维持体液渗透压、保持人体内的水平衡，以及帮助人体维持酸碱平衡，但是水不能为人体提供卡路里，所以在这里将水不作为饮食来考虑。同样，淡茶以及大部分药物都不作为饮食来考虑。")
        
    ]),
    Section(name: "9.饮料，咖啡是饮食吗？", items: [
        Item(name: "", detail: "是。饮料咖啡虽然是以水作为基本原料，但是饮料咖啡除给人提供水分外，咖啡和饮料中会含有一定量的碳水化合物、脂肪、维生素等成分，甚至还会含有各种氨基酸等，可以为人体提供生命活动所必需的能量，所以将他们作为饮食来考虑。")
        
    ]),
    Section(name: "10.我应该多久使用一次这个App？", items: [
        Item(name: "", detail: "建议每天记录下您的饮食，这样的有利于了解自己的饮食规律，让我们的应用给出更准确的建议哦，更有利于您的健康。")
        
    ])
]

public struct Item {
    var name: String
    var detail: String
    
    public init(name: String, detail: String) {
        self.name = name
        self.detail = detail
    }
}

public struct Section {
    var name: String
    var items: [Item]
    var collapsed: Bool
    
    public init(name: String, items: [Item], collapsed: Bool = false) {
        self.name = name
        self.items = items
        self.collapsed = collapsed
    }
}


extension UIView {

    func rotate(_ toValue: CGFloat, duration: CFTimeInterval = 0.2) {
        let animation = CABasicAnimation(keyPath: "transform.rotation")
        
        animation.toValue = toValue
        animation.duration = duration
        animation.isRemovedOnCompletion = false
        animation.fillMode = CAMediaTimingFillMode.forwards
        
        self.layer.add(animation, forKey: nil)
    }

}
