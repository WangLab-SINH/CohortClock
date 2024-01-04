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
        
        self.title = "FAQs"
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
    Section(name: "1. What is biological rhythms?", items: [
        Item(name: "", detail: "Biological rhythms refer to the cyclical properties that appear in physiological and behavioral parameters of living organisms, such as humans. The behavior of most living organisms oscillates due to the rotation and revolution of the Earth. The transition from spring to autumn, the rise and fall of tides; the blooming and wilting of flowers, the transition from night to day; starting work at sunrise and resting at sunset... all of these are rhythmic phenomena in nature.")
       
    ]),
    Section(name: "2. What is the relationship between circadian rhythms and diet?", items: [
        Item(name: "", detail: "Many physiological functions of organs need to adapt to eating behaviors. For example, the intestine secretes digestive enzymes to promote the digestion and absorption of food, the liver secretes bile, and the pancreas secretes digestive enzymes. Food signals play an important role in the modulation of circadian clocks, and this modulation is significant for maintaining the synchronization of human metabolism and behavior. In addition to the brain, circadian clocks also exist in other tissues such as the liver, pancreas, and gastrointestinal tract. These clocks are responsible for coordinating various functions within these organs. For example, the circadian clock in the liver plays a crucial role in maintaining internal homeostasis, primarily regulating the secretion of enzymes and metabolism. Disruption of the circadian rhythms in the liver can accelerate the development of liver diseases, including fatty liver, cholestasis, hepatitis, cirrhosis, and liver cancer. These diseases, in turn, can affect the circadian rhythms.")


        
    ]),
    Section(name: "3. What are the principles of chronobiology that this app is based on?", items: [
        Item(name: "", detail: "Our eating patterns inherently exhibit obvious rhythmic patterns; both fasting and eating change the metabolic state of the body. Studies on the diurnal rhythm models in humans and animals show that restricting the time of energy intake without changing the type of diet can bring multiple physiological benefits. At the same time, regular sleep and exercise are also necessary for health. This app was developed based on these principles by combining it with artificial intelligence algorithms.")
        
    ]),
    Section(name: "4. How much sleep do I need every day?", items: [
        Item(name: "", detail: "There are individual differences among people, but it is generally believed that adults need between 7 to 9 hours of sleep each day.")
       
    ]),
    Section(name: "5. How much exercise should I be getting?", items: [
        Item(name: "", detail: "The typical recommendation for adults is a minimum of 150 minutes of moderate aerobic activity or at least 75 minutes of vigorous aerobic activity each week.")
        
    ]),
    Section(name: "6. What is restrictive eating and what are its benefits?", items: [
        Item(name: "", detail:  "Restrictive eating, first proposed in the 1970s, refers to the long-term strict control of food intake to reduce or maintain body weight, essentially limiting daily calorie intake. In today's society, due to the high availability of food, people are in a relatively obesity-prone environment. As a result, more and more people are adopting restrictive eating as a strategy for weight loss. Benefits:Restrictive eating can reduce the intake of unnecessary energy in the body, lessen the burden on the body's major internal organs, and prevent the occurrence of metabolic disorders. It is an effective dietary intervention measure to prevent diabetes and cardiovascular diseases. Restrictive eating can also slow down the aging process and decrease the incidence of chronic diseases.")
        
    ]),
    Section(name: "7. What is the relationship between restrictive eating and diseases, as well as aging?", items: [
        Item(name: "", detail:  "Restrictive eating can modulate multiple pathways such as the insulin signaling pathway, TOR pathway, AMPK signal transduction pathway, and others. These pathways play a very important role in regulating various aspects of the body. Restrictive eating can improve insulin sensitivity, reduce fasting blood glucose and insulin concentrations, and prevent obesity, type 2 diabetes, hypertension, and chronic inflammation. It may also prevent the occurrence of tumors. Numerous studies have found that restrictive eating is an effective nutritional intervention measure to prevent most aging-related diseases and has the effect of delaying aging.")
       
    ]),
    Section(name: "8. Is water considered food?", items: [
        Item(name: "", detail: "No, it is not. Water can provide the body with necessary minerals, maintain water balance, and help the body maintain acid-base balance. However, water does not provide the body with calories, so it is not considered food in this context. Similarly, light tea and most medications are also not considered as food.")
        
    ]),
    Section(name: "9. Are beverages and coffee considered food?", items: [
        Item(name: "", detail: "Yes, they are. Even though beverages and coffee are primarily water-based, in addition to providing hydration, they contain varying amounts of sugars, acids, milk, various amino acids, vitamins, inorganic salts, fruit and vegetable juices, and other nutritional components in coffee and different beverages. These can provide energy to the body, so they are considered as part of the diet.")
        
    ]),
    Section(name: "10. How often should I use this app?", items: [
        Item(name: "", detail: "It is recommended that you record your diet on the app daily. This will help you understand your habits better, which is beneficial for your health.")
        
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
