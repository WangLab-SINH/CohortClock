//
//  TestTableViewController.swift
//  FWSideMenu
//
//  Created by 池宇豪 on 2021/3/10.
//  Copyright © 2021 xfg. All rights reserved.
//

import Foundation
import UIKit

class RecipesTableViewController: UITableViewController {

    var recipes = Recipe.createRecipes()
    let identifier: String = "tableCell"

    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
    }

   

}

extension RecipesTableViewController {

    func setupUI() {
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .done, target: self, action: nil)
        navigationItem.title = "Recipes"
        tableView.reloadData()
    }

}

// MARK: - UITableView DataSource

extension RecipesTableViewController {

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return recipes.count
    }

//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        if let cell = tableView.dequeueReusableCell(withIdentifier: identifier) as? TableCell {
//            cell.configurateTheCell(recipes[indexPath.row])
//            return cell
//        }
//        return UITableViewCell()
//    }

}

// MARK: - UITableView Delegate

extension RecipesTableViewController {

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            recipes.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .bottom)
        }
    }

}
