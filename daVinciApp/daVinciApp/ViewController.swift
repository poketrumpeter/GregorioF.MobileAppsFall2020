//
//  ViewController.swift
//  daVinciApp
//
//  Created by Gregorio Figueroa on 9/1/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    

    @IBOutlet weak var saoirseImage: UIImageView!
    
    @IBAction func chooseMovie(_ sender: UIButton) {
        if sender.tag == 1{
            saoirseImage.image = UIImage(named: "LadyBird")
        }
        else if sender.tag == 2{
            saoirseImage.image = UIImage(named: "LittleWomen")
            
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

