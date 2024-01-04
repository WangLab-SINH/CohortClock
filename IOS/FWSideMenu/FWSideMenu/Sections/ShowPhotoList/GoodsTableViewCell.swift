//
//  GoodsTableViewCell.swift
//  FWSideMenu
//
//  Created by wanglab on 2020/11/13.
//  Copyright © 2020 xfg. All rights reserved.
//

import Foundation
import UIKit

class Goods: NSObject {
    var name:String?

    var price:String?

    var coverIamge:String?

    var desTitle:String?

}

class GoodsTableViewCell: UITableViewCell {
    var titleLab:UILabel?

    var coverImageView:UIImageView?

    var desLab:UILabel?

    var priceLab:UILabel?

    

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)

    }

    

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)

        self.lauoutUI()

        

    }

    

    func lauoutUI()  {
        coverImageView = UIImageView(frame:CGRect(x:10,y:5,width:100,height:100))

        self.addSubview(coverImageView!)

        

        titleLab = UILabel(frame:CGRect(x:120,y:5,width:self.contentView.bounds.size.width - 130,height:30))

        self.addSubview(titleLab!)

        

        priceLab = UILabel(frame:CGRect(x:120,y:35,width:self.contentView.bounds.size.width - 130,height:30))

        self.addSubview(priceLab!)

        

        desLab = UILabel(frame:CGRect(x:120,y:65,width:self.contentView.bounds.size.width - 130,height:30))

        self.addSubview(desLab!)

    }

    

    func setValueForCell(model:Goods){
        self.titleLab?.text = model.name

        self.priceLab?.text = model.price

        self.desLab?.text = model.desTitle

        self.coverImageView?.image = UIImage(named:model.coverIamge!)

    }

    

    

    override func awakeFromNib() {
        super.awakeFromNib()

        // Initialization code

    }



    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)



        // Configure the view for the selected state

    }



}
