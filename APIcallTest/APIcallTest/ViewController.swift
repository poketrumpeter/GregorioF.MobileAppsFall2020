//
//  ViewController.swift
//  APIcallTest
//
//  Created by Gregorio Figueroa on 9/27/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{
    

    @IBOutlet weak var testLabel: UILabel!
    
    @IBOutlet weak var imageCall: UIImageView!
    
    @IBOutlet weak var movieTable: UITableView!
    
    var movieArray = [String]()
    
    let cellID = "cellID"
    
    @IBAction func CallButton(_ sender: UIButton) {
        
        if(sender.tag == 1){
            makeAPICall("http://www.omdbapi.com/?i=tt3896198&apikey=e15423a9&t=Star+Wars")
            
            makeAPICall("http://www.omdbapi.com/?i=tt3896198&apikey=e15423a9&t=Joker")
            
            makeAPICall("http://www.omdbapi.com/?i=tt3896198&apikey=e15423a9&t=Real+Steel")
            
            
        }
        
        print(movieArray)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        movieTable.dataSource = self
        
        movieTable.delegate = self
        
        self.movieTable.isEditing = true
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movieArray.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        var cell = tableView.dequeueReusableCell(withIdentifier: cellID)
        
        if(cell == nil){
            cell = UITableViewCell(style: UITableViewCell.CellStyle.default, reuseIdentifier: cellID)
        }
        
        cell?.textLabel?.text = movieArray[indexPath.row]
        
        return cell!
        
    }
    
    func makeAPICall(_ url: String){
        
        let session = URLSession(configuration: URLSessionConfiguration.default)

        let request = URLRequest(url: URL(string: url)!)

        let task: URLSessionDataTask = session.dataTask(with: request) { (data, response, error) -> Void in
            if let data = data {
                let response = NSString(data: data, encoding: String.Encoding.utf8.rawValue)
                print(response!)
                
                let data = response?.data(using: 9)
                
                let json = try? JSONSerialization.jsonObject(with: data!, options: [])
                
                if let dictionary = json as? [String: Any] {

                    
                    if let title = dictionary["Title"] as? String {
                        // access individual value in dictionary
                        DispatchQueue.main.async {
                            self.testLabel.text = title
                            self.movieArray.append(title)
                            
                            self.movieTable.reloadData()
                        }
                        
                    }
                    
                    if let image = dictionary["Poster"] as? String {
                        
                        DispatchQueue.main.async {
                            let imageData = NSData(contentsOf: URL(string: image)!)
                            self.imageCall.image = UIImage(data: imageData! as Data)
                            self.imageCall.reloadInputViews()
                        }
                        
                    }

                }
            }
            
        }
        task.resume()
    }

    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .none
    }

    func tableView(_ tableView: UITableView, shouldIndentWhileEditingRowAt indexPath: IndexPath) -> Bool {
        return false
    }
    
    func tableView(_ tableView: UITableView, moveRowAt sourceIndexPath: IndexPath, to destinationIndexPath: IndexPath) {
        let movedObject = self.movieArray[sourceIndexPath.row]
        movieArray.remove(at: sourceIndexPath.row)
        movieArray.insert(movedObject, at: destinationIndexPath.row)
        
        print(movieArray)
    }
}

