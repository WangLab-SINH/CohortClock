//
//  HowtouseViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/8.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
////
////  HelpViewController.swift
////  FWSideMenu
////
////  Created by 池宇豪 on 2021/3/8.
////  Copyright © 2021 xfg. All rights reserved.
////
//
import Foundation
class HowtouseViewController: UITableViewController {

    var sections = sectionsData1

    override func viewDidLoad() {
        super.viewDidLoad()


        // Auto resizing the height of the cell
        tableView.estimatedRowHeight = 44.0
        tableView.rowHeight = UITableView.automaticDimension

        self.title = "帮助"
    }

}

//
// MARK: - View Controller DataSource and Delegate
//
extension HowtouseViewController {

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

        let item: Item1 = sections[indexPath.section].items[indexPath.row]

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
extension HowtouseViewController: CollapsibleTableViewHeaderDelegate {

    func toggleSection(_ header: CollapsibleTableViewHeader, section: Int) {
        let collapsed = !sections[section].collapsed

        // Toggle collapse
        sections[section].collapsed = collapsed
        header.setCollapsed(collapsed)

        tableView.reloadSections(NSIndexSet(index: section) as IndexSet, with: .automatic)
    }

}


public var sectionsData1: [Section1] = [
    Section1(name: "1.我该如何使用这个App？", items: [
        Item1(name: "", detail: "打开APP后，同意使用协议，选择传食物界面，点击拍照或者选择图片按钮，根据提示赋予所需的手机存储与使用相机权限。使用APP对食物进行拍照，或选择手机内的食物图片后点击上传按钮。APP会将当前图片上传到服务器进行分析判断是否是食物，并返回近三个月内的用户饮食规律和当前时间之后的24小时内用户的饮食时间建议。")

    ]),
    Section1(name: "2.这个App有哪些功能？", items: [
        Item1(name: "", detail: "本应用目前主要是通过用户上传的食物图片，推断出用户的饮食规律，并给出合理的饮食建议，比如当前时间之后的24小时内用户的饮食时间建议。同时本应用还可以记录用户的基本信息如身高、体重、睡眠时间等数据。")

    ]),
    Section1(name: "3.我忘记按时上传食物图片了怎么办？", items: [
        Item1(name: "", detail: "如果忘记上传数据，可以点击更新按钮，进入补传数据页面，选择食物的图片，然后选择当天进食的时间点，注意是只能补传当天忘记的食物的图片，然后选中图片，点击上传，那么补传就完成了。")

    ]),
    Section1(name: "4.我没有上传食物图片可以么？", items: [
        Item1(name: "", detail: "本应用会使用深度学习算法判断上传的图片是否是食物，如果上传非食物图片并不会被记录到用户的饮食规律中。本应用也可以选择手动上传功能，可以不上传图片")

    ]),
    Section1(name: "5.注册账号有什么用处？", items: [
        Item1(name: "", detail: "注册账号后在您更换手机后可以同步之前手机所记录的饮食规律，这样有利于给出您更合理的饮食建议。")

    ])
]

public struct Item1 {
    var name: String
    var detail: String

    public init(name: String, detail: String) {
        self.name = name
        self.detail = detail
    }
}

public struct Section1 {
    var name: String
    var items: [Item1]
    var collapsed: Bool

    public init(name: String, items: [Item1], collapsed: Bool = false) {
        self.name = name
        self.items = items
        self.collapsed = collapsed
    }
}

//
//extension UIView {
//
//    func rotate(_ toValue: CGFloat, duration: CFTimeInterval = 0.2) {
//        let animation = CABasicAnimation(keyPath: "transform.rotation")
//
//        animation.toValue = toValue
//        animation.duration = duration
//        animation.isRemovedOnCompletion = false
//        animation.fillMode = CAMediaTimingFillMode.forwards
//
//        self.layer.add(animation, forKey: nil)
//    }
//
//}
