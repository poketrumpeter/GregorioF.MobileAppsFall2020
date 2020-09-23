//
//  ViewController.swift
//  StarWars
//
//  Created by Gregorio Figueroa on 9/22/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var titleLabel: UILabel!
    
    @IBOutlet weak var starWarsImage: UIImageView!
    
    @IBOutlet weak var colorLabel: UILabel!
    
    @IBOutlet weak var CapitalizeLabel: UILabel!
    
    @IBOutlet weak var fontSizeLabel: UILabel!
    
    @IBOutlet weak var trilogyControl: UISegmentedControl!
    
    
    @IBOutlet weak var sliderControl: UISlider!
    
    @IBAction func changeTrilogy(_ sender: UISegmentedControl) {
        
        if sender.selectedSegmentIndex == 0{
            starWarsImage.image = UIImage(named: "prequels")
        }
        else if sender.selectedSegmentIndex == 1{
            starWarsImage.image = UIImage(named: "originals")
        }
        else if sender.selectedSegmentIndex == 2{
            starWarsImage.image = UIImage(named: "sequels")
        }
        
    }
    
    @IBAction func changeColor(_ sender: UISwitch) {
        
        if sender.isOn{
            titleLabel.textColor = UIColor(displayP3Red: 0.47, green: 0.186, blue: 0.826, alpha: 1)
            
            colorLabel.textColor = UIColor(displayP3Red: 0.47, green: 0.186, blue: 0.826, alpha: 1)
            
            CapitalizeLabel.textColor = UIColor(displayP3Red: 0.47, green: 0.186, blue: 0.826, alpha: 1)
            
            fontSizeLabel.textColor = UIColor(displayP3Red: 0.47, green: 0.186, blue: 0.826, alpha: 1)
            
            trilogyControl.selectedSegmentTintColor = UIColor(displayP3Red: 0.47, green: 0.334, blue: 0.826, alpha: 1)
            
            sliderControl.minimumTrackTintColor = UIColor(red: 0.47, green: 0.334, blue: 0.826, alpha: 1)
        }
        else{
            titleLabel.textColor = UIColor(displayP3Red: 0, green: 0, blue: 0, alpha: 1)
            
            colorLabel.textColor = UIColor(displayP3Red: 0, green: 0, blue: 0, alpha: 1)
            
            CapitalizeLabel.textColor = UIColor(displayP3Red: 0, green: 0, blue: 0, alpha: 1)
            
            fontSizeLabel.textColor = UIColor(displayP3Red: 0, green: 0, blue: 0, alpha: 1)
            
            trilogyControl.selectedSegmentTintColor = UIColor(displayP3Red: 1, green: 1, blue: 1, alpha: 1)
            
            sliderControl.minimumTrackTintColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
            
            
        }
    }
    
    
    @IBAction func capitalizeText(_ sender: UISwitch) {
        
        if sender.isOn{
            
            titleLabel.text = titleLabel.text?.uppercased()
            
            colorLabel.text = colorLabel.text?.uppercased()
            
            CapitalizeLabel.text = CapitalizeLabel.text?.uppercased()
            
            fontSizeLabel.text = fontSizeLabel.text?.uppercased()
            
        }
        
        else{
            
            titleLabel.text = titleLabel.text?.lowercased()
            
            colorLabel.text = titleLabel.text?.lowercased()
            
            CapitalizeLabel.text = CapitalizeLabel.text?.lowercased()
            
            fontSizeLabel.text = fontSizeLabel.text?.lowercased()
        
        }
    }
    
    
    @IBAction func changeFont(_ sender: UISlider) {
        
        //get new value
        let newValue = sender.value
        
        //change text label
        fontSizeLabel.text  = String(format: "Font Size: %.0f", newValue)
        
        let newFont = CGFloat(newValue)
        
        titleLabel.font = UIFont.systemFont(ofSize: newFont)
        
        colorLabel.font = UIFont.systemFont(ofSize: newFont)
        
        CapitalizeLabel.font = UIFont.systemFont(ofSize: newFont)
        
        fontSizeLabel.font = UIFont.systemFont(ofSize: newFont)
        
        
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

