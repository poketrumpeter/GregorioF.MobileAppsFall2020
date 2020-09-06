//
//  ViewController.swift
//  Lab1_Movies
//
//  Created by Gregorio Figueroa on 9/2/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    
    
    @IBOutlet weak var movieImage: UIImageView!
    
    @IBOutlet weak var answerLabel: UILabel!
    
    @IBOutlet weak var inceptionButton: UIButton!
    
    @IBOutlet weak var socialNetworkButton: UIButton!
    
    @IBOutlet weak var lastJediButton: UIButton!
    
    @IBAction func selectMovie(_ sender: UIButton) {
        
        if(sender.tag == 1){
            movieImage.image = UIImage(named: "lastJedi")
            answerLabel.text = "Correct! Great Choice"
            answerLabel.textColor = UIColor(red: 0.3, green: 1, blue: 0.1, alpha: 1)
            answerLabel.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
            
            inceptionButton.isEnabled = false
            inceptionButton.isHidden = true
            
            socialNetworkButton.isEnabled = false
            socialNetworkButton.isHidden = true
            
            lastJediButton.isHighlighted = true
        }
            
        else if (sender.tag == 2){
            movieImage.image = UIImage(named: "inception")
            answerLabel.text = "Wrong Answer, this is not the best movie"
            answerLabel.textColor = UIColor(red: 1, green: 0.2, blue: 0, alpha: 1)
            answerLabel.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
            
            lastJediButton.isEnabled = false
            lastJediButton.isHidden = true
            
            socialNetworkButton.isEnabled = false
            socialNetworkButton.isHidden = true
        }
            
        else if(sender.tag == 3){
            movieImage.image = UIImage(named: "socialNetwork")
            answerLabel.text = "Wrong Answer, this is not the best movie"
            answerLabel.textColor = UIColor(red: 1, green: 0.2, blue: 0, alpha: 1)
            answerLabel.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
            
            inceptionButton.isEnabled = false
            inceptionButton.isHidden = true
            
            lastJediButton.isEnabled = false
            lastJediButton.isHidden = true
        }
            
        else if(sender.tag == 4){
            movieImage.image = UIImage(named: "movies")
            answerLabel.text = ""
            answerLabel.backgroundColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
            
            lastJediButton.isEnabled = true
            lastJediButton.isHidden = false
            
            inceptionButton.isEnabled = true
            inceptionButton.isHidden = false
            
            socialNetworkButton.isEnabled = true
            socialNetworkButton.isHidden = false
        }
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

