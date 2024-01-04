//
//  RecipesTableViewController.swift
//  Swift-TableView-Example
//
//  Created by Bilal Arslan on 28/02/16.
//  Copyright © 2016 Bilal ARSLAN. All rights reserved.
//

import UIKit
//class RecipesTableViewController: UIViewController,UITableViewDelegate,UITableViewDataSource{
//class RecipesTableViewController: UITableViewController {
//
//    var recipes = Recipe.createRecipes()
//    let identifier: String = "tableCell"
//    var forthTableView:UITableView!
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        
//        self.edgesForExtendedLayout = .init(rawValue: 0)
//        forthTableView = UITableView(frame:self.view.bounds)
//        forthTableView.delegate = self
//        forthTableView.dataSource = self
//        forthTableView.register(UITableViewCell.self, forCellReuseIdentifier: "tableCell")
//        self.view.addSubview(forthTableView)
//        setupUI()
//    }
//
////    // MARK: Segue Method
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        if segue.identifier == "recipeDetail",
//            let indexPath = tableView?.indexPathForSelectedRow,
//            let destinationViewController: DetailViewController = segue.destination as? DetailViewController {
//            destinationViewController.recipe = recipes[indexPath.row]
//        }
//    }
//
//}
//
//extension RecipesTableViewController {
//
//    func setupUI() {
//        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .done, target: self, action: nil)
//        navigationItem.title = "Recipes"
//        tableView.reloadData()
//    }
//
//}
//
//// MARK: - UITableView DataSource
//
//extension RecipesTableViewController {
//
//    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        return recipes.count
//    }
//
//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        //let temp_cell = tableView.dequeueReusableCell(withIdentifier: identifier)
////        if let cell = tableView.dequeueReusableCell(withIdentifier: identifier) as? TableCell {
////            cell.configurateTheCell(recipes[indexPath.row])
////            return cell
////        }
////        return UITableViewCell()
//        
//        
//        let cell = (tableView.dequeueReusableCell(withIdentifier: "tableCell", for: indexPath)) as? TableCell
//            //cell.textLabel?.text = recipes[indexPath.row]
//        cell?.configurateTheCell(recipes[indexPath.row])
//        return cell ?? UITableViewCell()
//    }
//
//}
//
//// MARK: - UITableView Delegate
//
//extension RecipesTableViewController {
//
//    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if editingStyle == .delete {
//            recipes.remove(at: indexPath.row)
//            tableView.deleteRows(at: [indexPath], with: .bottom)
//        }
//    }
//
//}
