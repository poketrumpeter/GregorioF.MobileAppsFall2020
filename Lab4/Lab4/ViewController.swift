//
//  ViewController.swift
//  Lab4
//
//  Created by Gregorio Figueroa on 10/4/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    
    @IBOutlet weak var CityEntry: UITextField!
    
    @IBOutlet weak var MascotEntry: UITextField!
    
    @IBOutlet weak var WinStepper: UIStepper!
    
    @IBOutlet weak var winLabel: UILabel!
    
    @IBOutlet weak var LossStepper: UIStepper!
    
    @IBOutlet weak var lossLabel: UILabel!
    
    @IBOutlet weak var generateButton: UIButton!
    
    var msg: String = ""
    
    var winRatio: Double = 0.0
    
    @IBAction func winStepperChanged(_ sender: UIStepper) {
        
        winLabel.text = "\(Int(sender.value))"
        
    }
    
    
    @IBAction func lossStepperChanged(_ sender: UIStepper) {
        
        lossLabel.text = "\(Int(sender.value))"
    }
    
    
    @IBAction func generateButton(_ sender: UIButton) {
        
        msg.append("The ")
        msg.append(CityEntry.text ?? "City")
        msg.append(" ")
        msg.append(MascotEntry.text ?? "Animal")
        msg.append(" have won ")
        msg.append(String(Int(WinStepper.value)))
        msg.append(" games this season while losing ")
        msg.append(String(format: "%.1f", Int(LossStepper.value)))
        msg.append(" games. They have a win/loss ration of ")
        
        winRatio = WinStepper.value/LossStepper.value
        
        msg.append(String(winRatio))
        
        let alert = UIAlertController(title: "MAD LIBS", message: msg, preferredStyle: .alert)
        //print(msg)
        
        let okAction = UIAlertAction(title: "ok", style: .default, handler: {ACTION -> Void in})
        
        alert.addAction(okAction)
        
        self.present(alert, animated: true, completion: nil)
        msg = ""
        
    }
    
    override func viewDidLoad() {
        
        CityEntry.delegate = self
        MascotEntry.delegate = self
        
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        CityEntry.placeholder = "Enter City Here"
        //CityEntry.textColor = UIColor.purple
        
        MascotEntry.placeholder = "Enter Animal Here"
        
        winLabel.text = String(WinStepper.value)
        
        lossLabel.text = String(LossStepper.value)
        
        generateButton.isEnabled = false
        
        generateButton.alpha = 0.5
        
        [CityEntry, MascotEntry].forEach({
            $0.addTarget(self, action: #selector(editingChanged), for: .editingChanged)
            
        })
        
        // defines the view as an observer to recieve the Keyboardwill show
        // notification which will then tell it to run the specific function
        
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
        
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
        
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        
    }
    
    @objc func dismissKeyboard(){
        view.endEditing(true)
    }
    
    @objc func keyboardWillShow(notification: NSNotification){
        
        if let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue{
            
            if self.view.frame.origin.y == 0{
                self.view.frame.origin.y -= keyboardSize.height/1.5
            }
        }
    }
    
    @objc func keyboardWillHide(notification: NSNotification){
        
        if ((notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue) != nil{
            
            if self.view.frame.origin.y != 0{
                self.view.frame.origin.y = 0
            }
        }
    }
    
    @objc func editingChanged(_ textField: UITextField){
        if textField.text == "" {
            generateButton.isEnabled = false
            generateButton.alpha = 0.5
            return
        }
        guard
            let City = CityEntry.text, !City.isEmpty,
            let Mascot = MascotEntry.text, !Mascot.isEmpty
        else {
            generateButton.isEnabled = false
            generateButton.alpha = 0.5
            return
        }
        generateButton.isEnabled = true
        generateButton.alpha = 1
        
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }

}

