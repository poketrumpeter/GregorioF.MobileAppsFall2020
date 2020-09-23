//
//  ViewController.swift
//  skilletApp
//
//  Created by Gregorio Figueroa on 9/10/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var skilletView: UIImageView!
    
    @IBOutlet weak var skilletLabel: UILabel!
    
    @IBOutlet weak var fontSize: UILabel!
    
    @IBOutlet weak var capatilizationLabel: UILabel!
    
    @IBOutlet weak var capitalSwitch: UISwitch!
    
    @IBOutlet weak var sizeSlider: UISlider!
    
    @IBAction func changeInfo(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0{
            //Awake Image Change
            skilletView.image = UIImage(named: "earlierSkillet")
            skilletLabel.text = "Awake"
        }
        else if sender.selectedSegmentIndex == 1{
            //Victorious Image Change
            skilletView.image = UIImage(named: "newSkillet")
            skilletLabel.text = "Victorious"
        }
        
    }
    
    @IBAction func updateFont(_ sender: UISwitch) {
        if sender.isOn{
            //Capatilize
            skilletLabel.text = skilletLabel.text?.uppercased()
            capatilizationLabel.text = capatilizationLabel.text?.uppercased()
        }
        else if !sender.isOn{
            //Lower Case
            skilletLabel.text = skilletLabel.text?.lowercased()
            capatilizationLabel.text = capatilizationLabel.text?.lowercased()
        }
    }
    
    @IBAction func changeFontSize(_ sender: UISlider) {
        //get new value
        let newValue = sender.value
        //change text label
        fontSize.text  = String(format: "%.0f", newValue)
        //change font size
        let fontSizeCGFloat = CGFloat(newValue)
        
        skilletLabel.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
        
        fontSize.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
        
        
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

