//
//  QuestionViewController.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/12/13.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation

import UIKit
//import SwiftyComments

/// Simplest implementation, colored to show the different zones in a commentCell.
//class SimpleCommentsViewController: CommentsViewController, UITextViewDelegate {
//    private let commentCellId = "simpleCommentCellId"
//    var allComments: [RichComment] = [] // All the comments (nested, not in a linear format)
//    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        tableView.register(SimpleCommentCell.self, forCellReuseIdentifier: commentCellId)
//        
//        allComments = RandomDiscussion.generate().comments
//        currentlyDisplayed = allComments
//
//    }
//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> CommentCell {
//        let commentCell = tableView.dequeueReusableCell(withIdentifier: commentCellId, for: indexPath) as! SimpleCommentCell
//        let comment = currentlyDisplayed[indexPath.row] as! RichComment
//        commentCell.level = comment.level
//        commentCell.commentContent = comment.body
//        commentCell.posterName = comment.posterName
//        return commentCell
//    }
//    
//}



//String str1 = "<p><B>1.什么是生物节律？</B><br/>" +
//               "生物节律是指生物体比如我们人类在生理、行为指标等方面出现的周期性特征，比如我们的体温，情绪，血压等都在以24小时为周期发生有规律的变化，我们的睡眠-觉醒行为也是一种常见的生物节律现象。</p>";
//       String  str2 = "<p><B>2.生物节律和饮食的关系是什么？</B><br/>" +
//               "很多器官和组织的生理功能都要与人的进食行为相适应，比如小肠分泌消化酶来促进食物的消化与吸收，肝脏分泌胆汁，胰腺分泌消化酶。食物信号是一种可以独立地调节身体生物钟的影响因子，这种调控对于维持人体代谢和行为的同步化具有重要的意义。生物钟在人体的消化器官里也会存在，比如肝脏、胰腺以及胃肠等部位。比如肝脏的生物钟对于维持肝脏内部的稳态具有重要作用，主要负责调节肝脏的酶的分泌和新陈代谢，肝脏中生物节律的紊乱，会影响饮食后的食物的营养的消化与吸收，严重会引发肝部相关疾病，比如脂肪肝、肝炎，甚至癌症。</p>";
//       String str3 = "<p><B>3.这个App基于的节律生物学原理是什么？</B><br/>" +
//               "人的饮食本身具有明显的节律性，空腹与进食都会改变人体的代谢状态。在人与动物中的昼夜节律模型的研究表明，在不改变饮食种类的情况下将能量摄入的时间限制在一定时间之内，可以带来多方面的生理益处。这个App是在这个原理并结合人工智能算法的基础上研制的。" +
//               "</p>";
//       String str4 = "<p><B>4.什么是限制性饮食，它的好处有哪些？</B><br/>" +
//               "限制性饮食最早在上世纪就已经被提出了, 是指通过控制进食的方式来减轻或维持体重。这种行为本质上是限制每日卡路里摄入量或卡路里摄入总量。由于美食越来越丰富，人们很容易摄入超出自身活动需要的热量，从而导致超重甚至过度肥胖。限制性饮食通常会被女性所采用以维持体重，并逐渐被更多的人用来降低体重或维持体重，以满足人们审美和健康生活的需要。" +
//               "好处： \n" +
//               "限制性饮食通过减少能量的摄入量来减轻机体主要内脏器官的负担，避免机体稳态的破坏和代谢紊乱的发生，可以有效地预防肥胖的发生，控制体重。此外，限制性饮食还可以降低糖尿病和心血管疾病的发病率，减缓衰老速度，有效地预防慢性疾病。" +
//               "</p>";
//       String str5 = "<p><B>5.什么是时间限制性饮食？</B><br/>" +
//               "时间限制性饮食，顾名思义，就是通过限制饮食的时间窗口来进行限制性饮食。时间限制性饮食旨在维持一致的进食和禁食的周期，来维持非常强劲的昼夜节律。时间限制性饮食可以人为的有意增加内脏器官的节律性调控，进而减少肥胖的发生，改善睡眠质量，减少系统性炎症，维持肠道的稳态，对人体的健康会产生十分积极的影响。" +
//               "</p>";
//       String str6 = "<p><B>6.限制性饮食和体重有什么关系？</B><br/>" +
//               "许多研究发现限制性饮食是一个减轻体重的有效方法。这其中有两方面的原因，一方面是进食的时间减少了，肝脏以及胃肠得到休息的时间延长；另一方面是因为身体摄入的总卡路里数也减少了。" +
//               "</p>";
//       String str7 = "<p><B>7.限制性饮食和疾病以及衰老有什么关系？</B><br/>" +
//               "限制性饮食会调动胰岛素信号通路、TOR通路，AMPK信号转导途径等多个通路，这些通路可能会相互作用，或者在调控人体反应机制的多个方面有着十分重要的作用，进而会对疾病的发生有着一定的影响。例如，限制性饮食可以通过改善胰岛素敏感性来降低空腹血糖和胰岛素浓度，对于Ⅱ型糖尿病，慢性炎症、肥胖症以及高血压等疾病具有预防作用。此外，限制性饮食对于减少各种慢性顽疾和肿瘤的发生具有积极的预防和治疗作用。\n" +
//               "根据多项研究和临床试验表明，限制性饮食还对大多数与衰老有关的多种疾病具有预防作用，即限制性饮食是一种具有延缓衰老作用的营养干预措施。因此从长远来看，限制性饮食对于促进健康，提高生活质量，延长寿命具有积极影响\n。</p>";
//       String str8 = "<p><B>8.水是饮食吗？</B><br/>" +
//               "不是。水可以为人体提供所需的矿物质，维持体液渗透压、保持人体内的水平衡，以及帮助人体维持酸碱平衡，但是水不能为人体提供卡路里，所以在这里将水不作为饮食来考虑。同样，淡茶以及大部分药物都不作为饮食来考虑。</p>";
//       String str9 = "<p><B>9.饮料，咖啡是饮食吗？</B><br/>" +
//               "是。饮料咖啡虽然是以水作为基本原料，但是饮料咖啡除给人提供水分外，咖啡和饮料中会含有一定量的碳水化合物、脂肪、维生素等成分，甚至还会含有各种氨基酸等，可以为人体提供生命活动所必需的能量，所以将他们作为饮食来考虑。</p>";
//       String str10 = "<p><B>10.我应该多久使用一次这个App？</B><br/>" +
//               "建议每天记录下您的饮食，这样的有利于了解自己的饮食规律，让我们的应用给出更准确的建议哦，更有利于您的健康。</p>";
