//
//  ViewController.swift
//  tableViewTest
//
//  Created by Gregorio Figueroa on 9/28/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    let petArray = ["Dragon", "Dog", "Rhino", "Cat", "Parakeet", "Finch", "Turtle", "Snake"]
    
    let cellID = "cellID"
    
    @IBOutlet weak var testTable: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        testTable.dataSource = self
        
        testTable.delegate = self
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
               return petArray.count
           }
           
        func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
               
            var cell = tableView.dequeueReusableCell(withIdentifier: cellID)
               
            if(cell == nil){
                cell = UITableViewCell(style: UITableViewCell.CellStyle.default, reuseIdentifier: cellID)
            }
               
            cell?.textLabel?.text = petArray[indexPath.row]
            
            return cell!
            
    }
}

