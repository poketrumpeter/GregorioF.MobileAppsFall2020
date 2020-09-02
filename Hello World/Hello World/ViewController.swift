//
//  ViewController.swift
//  Hello World
//
//  Created by Gregorio Figueroa on 8/27/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var messageText: UILabel!
    @IBAction func ButtonPress(_ sender: UIButton) {
        messageText.text = "Hello Kyber!"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

