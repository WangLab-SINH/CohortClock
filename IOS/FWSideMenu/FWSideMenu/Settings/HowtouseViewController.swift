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

        self.title = "Tutorials"
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
    Section1(name: "1. How do I use this app?", items: [
        Item1(name: "", detail:  "After opening the app, agree to the user agreement and set your gender, height, and age. Select the food upload interface, then click the button to take a photo or select an image, granting the necessary permissions for storage and camera use as prompted. Use the app to take a photo of the food, or choose a food image from your phone, then click the save button. At the same time, enter your sleep, weight, and disease information. The app will upload the current image to the server for analysis to determine whether it's food, and record the user's sleep patterns and other lifestyle habits.")

    ]),
    Section1(name: "2. What features does this app have?", items: [
        Item1(name: "", detail: "This app primarily characterizes users' dietary, exercise and sleep patterns through uploaded food images and sleep times. Additionally, the app can record users' basic information such as height, weight, disease history, and other body metrics. These data can be used for cohort level scientific research. To upload sleep information: Select the Main tab, click on the Weight/Sleep page, and choose your sleep and wake-up times. To upload weight information: Select the Main tab, click on the Weight/Sleep page, and enter your weight. To upload height information: Select the Body Metrics tab, click the Height button, and enter your height for data upload. To upload blood pressure data: Select the Body Metrics tab, click the Blood Pressure button, and enter your measured systolic and diastolic pressure data. To upload heart rate data: Select the Body Metrics tab, click the Heart Rate button, and enter your measured heart rate. To upload waist circumference data: Select the Body Metrics tab, click the Waist Circumference button, and enter your measured waist circumference. To upload exercise data: Select the Body Metrics tab, click the Exercise Duration button, and choose the start and end times of your exercise. To upload age data: Select the My information tab, click the Age button, and enter your age. To upload gender data: Select the My information tab, click the Gender button, and choose your gender. To upload body temperature data: Select the Body Metrics tab, click the Temperature button, and enter your measured body temperature. To upload medication data: Select the Body Metrics tab, click the Medicine button, and enter the medication you are taking. To upload disease data: Select the Body Metrics tab, click the Disease button, and select the diseases you want to record.")

    ]),
    Section1(name: "3. What should I do if I forget to upload food, sleep, weight, exercise, and other information on time?", items: [
        Item1(name: "", detail: "If you forget to upload data, you can click the update button to go to the data re-upload page. Select the food picture, then choose the time of eating for that day. Please note that you can only re-upload pictures of food that you forgot to upload on the same day. After selecting the picture, click save to complete the re-upload. The operations for sleep, weight, and exercise information are similar.")

    ]),
    Section1(name: "4. Can I upload non-food pictures?", items: [
        Item1(name: "", detail: "This app uses deep learning algorithms to determine whether the uploaded image is food. If a non-food image is uploaded, it will not be recorded in the user's dietary habits.")

    ]),
    Section1(name: "5. What should I do if I want to modify the body metrics data?", items: [
        Item1(name: "", detail: "If you need to modify data due to an upload error, you can click on the Main tab and then the Modification button. This page will display all the data you uploaded today, allowing you to edit and delete as necessary.")

    ]),
    Section1(name: "6. Is registering an account useful?", items: [
        Item1(name: "", detail: "After registering an account, if you change your phone, you can synchronize the dietary habits recorded on your previous phone. This facilitates the cohort research.")

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
