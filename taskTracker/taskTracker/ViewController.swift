//
//  ViewController.swift
//  taskTracker
//
//  Created by Gregorio Figueroa on 9/28/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    var numbers = ["One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"]

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return numbers.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "numberCell", for: indexPath)
        
        cell.textLabel?.text = numbers[indexPath.row]
        
        return cell
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

