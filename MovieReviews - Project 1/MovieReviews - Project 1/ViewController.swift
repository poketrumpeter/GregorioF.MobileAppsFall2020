//
//  ViewController.swift
//  MovieReviews - Project 1
//
//  Created by Gregorio Figueroa on 10/5/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{
    
    
    @IBOutlet weak var movieTable: UITableView!
    
    @IBOutlet weak var compareButton: UIButton!
    
    
    var score : Dictionary = Dictionary<String, Any>()
    
    let cellID = "cellID"
    
    //Booleans to see which score to show
    var rotten : Bool = false
    var critic : Bool = false
    
    var movieArray = [String]()
    var tomatoScores = [String]()
    var metaCriticScores = [String]()
    var displayScores = [String]()
    
    //var movies: Dictionary = Dictionary<String, String>()
    
    var movies = Dictionary<String, MovieInfo>()
    
    var rottenTomatoes = ["Parasite", "The+Farewell", "Knives+Out", "Toy+Story+4", "Booksmart", "The+Irishman", "Little+Women", "Avengers:+Endgame", "Us", "A+Beautiful+Day+In+The+Neighborhood"]
    
    var metacritic = ["Parasite", "Portrait+of+a+Lady+on+Fire", "The+Irishman", "Marriage+Story", "The+Souvenir", "Little+Women", "Uncut+Gems", "For+Sama", "The+Farewell", "Long+Day's+Journey+Into+Night"]

    var reviewer: String = ""
    
    @IBAction func changeMovieList(_ sender: UISegmentedControl) {
        
        movieTable.isEditing = false;
        //Look at scores and movies for Rotten Tomatoes
        if(sender.selectedSegmentIndex == 0) {//Rotten Tomatoes Selected
        
            compareButton.isHidden = true
            
            for i in rottenTomatoes{
                
                if(movies[i] == nil){
                    //print("Making Call")
                    makeAPICall(movieTitle: i)
                }
                
            }
            
            movieArray = rottenTomatoes
            reviewer = "Rotten Tomatoes"
            
            movieTable.reloadData()
        
        }
        //Look at scores and Movies for Metacritic
        else if(sender.selectedSegmentIndex == 1){
            
            compareButton.isHidden = true
            
            for i in metacritic{
                if(movies[i] == nil){
                
                    makeAPICall(movieTitle: i)
                }
            }
            
            movieArray = metacritic
            reviewer = "Metacritic"
            
            movieTable.reloadData()
            
        }
        
        //Enable Table editing on Rotten Tomatoes Movies to allow users to select their ranking
        else if(sender.selectedSegmentIndex == 2){
            
            compareButton.isHidden = false

            for i in rottenTomatoes{
                
                if(movies[i] == nil){
                    print("")
                    makeAPICall(movieTitle: i)
                }
            }
            
            movieArray = rottenTomatoes
            reviewer = ""
            
            movieTable.isEditing = true;
            
            movieTable.reloadData()
        }
    }
    
    @IBAction func compareChoices(_ sender: UIButton) {
        
        var matches = [String]()
        
        var count: Int = 0
        if sender.tag == 0 {
            for i in 0..<movieArray.count{
                if(movieArray[i] == rottenTomatoes[i]){
                    count+=1
                    matches.append(movieArray[i])
                    }
                
            }
            
            var message: String
            
            if count == 1 {
                message = "Your ranking of " + movies[matches[0]]!.title + " matched the Rotten Tomatoes rankings"
            }
            else{
                var movieString: String = ""
                for i in 0..<(matches.count - 1){
                    movieString.append(movies[matches[i]]!.title + ", ")
                }
                movieString.append("and " + movies[matches[matches.count-1]]!.title + " ")
                
                message = "Your Rankings of " + movieString + "matched the Rotten Tomatoes rankings"
            }
            
            let alert = UIAlertController(title: "Compare", message: message, preferredStyle: .alert)
            
            let okAction = UIAlertAction(title: "Dismiss", style: .default, handler: {action -> Void in
                
            })
            alert.addAction(okAction)
            
            self.present(alert, animated: true, completion: nil)
            
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        movieTable.dataSource = self
        movieTable.delegate = self
        
        for i in rottenTomatoes{
            makeAPICall(movieTitle: i)
        }
        
        compareButton.isHidden = true
        
        movieArray = rottenTomatoes
        reviewer = "Rotten Tomatoes"

    
    }
    
    //function that returns the size of main tableview array
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movieArray.count
    }
    
    //function for displaying cell contents from movie array
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        var cell = tableView.dequeueReusableCell(withIdentifier: cellID)
        
        if(cell == nil){
            cell = UITableViewCell(style: UITableViewCell.CellStyle.default, reuseIdentifier: cellID)
        }

        cell?.textLabel?.numberOfLines = 2
        cell?.textLabel?.font = UIFont.systemFont(ofSize: CGFloat(15))
        cell?.detailTextLabel?.font = UIFont.systemFont(ofSize: CGFloat(15))
        
        //print(movies)
        cell?.textLabel?.text = movies[movieArray[indexPath.row]]?.title
        cell?.detailTextLabel?.text = movies[movieArray[indexPath.row]]?.ratings[reviewer]
        
        return cell!
    }

    func makeAPICall(movieTitle: String){
        
        let session = URLSession(configuration: URLSessionConfiguration.default)

        let request = URLRequest(url: URL(string: "http://www.omdbapi.com/?i=tt3896198&apikey=e15423a9&t="+movieTitle)!)

        let task: URLSessionDataTask = session.dataTask(with: request) { (data, response, error) -> Void in
            if let data = data {
                let response = NSString(data: data, encoding: String.Encoding.utf8.rawValue)
                //print(response!)
                
                let data = response!.data(using:String.Encoding.utf8.rawValue)
                
                let json = try? JSONSerialization.jsonObject(with: data!, options: [])
                
                if let dictionary = json as? [String: Any] {
                    
                    //print(dictionary)
                    
                    DispatchQueue.main.async {
                        if let title = dictionary["Title"] as? String {
                            // access individual value in dictionary
                          
                                
                            //Location to perform actions with data in Title
                            var movieInfo = self.movies[movieTitle]
                            if(movieInfo == nil){
                                movieInfo = self.loadInfo(title: title)
                                self.movies[movieTitle] = movieInfo
                            }
                            
                            
                            //self.movieArray.append(title)
                            
                            //self.movieTable.reloadData()
                            
                            if let ratings = dictionary["Ratings"] as? [Any]{
                                
                                //print(ratings)
                                var tomato: Bool = false
                                var meta: Bool = false
                            
                                for i in ratings{
                                    
                                    self.score = i as! [String: String]
                                        
                                    
                                    if((self.score["Source"] as! String) == "Rotten Tomatoes"){
                                        
                                        self.movies[movieTitle]?.ratings["Rotten Tomatoes"] = self.score["Value"] as? String
                       
                                        tomato = true
                                        
                                    }
                                    else if ((self.score["Source"] as! String) == "Metacritic"){
                                        
                                        self.movies[movieTitle]?.ratings["Metacritic"] = self.score["Value"] as? String
                  
                                        meta = true
                                        
                                    }
                                }
                                
                                if(!meta){
                                    self.movies[movieTitle]?.ratings["Metacritic"] = "None"
                                }
                                else if(!tomato){
                                    self.movies[movieTitle]?.ratings["Rotten Tomatoes"] = "None"
                                }
                            }
                            
                            if let posterURL = dictionary["Poster"] as? String{
                                
                                let imageData = NSData(contentsOf: URL(string: posterURL)!)
                                self.movies[movieTitle]?.poster = UIImage(data: imageData! as Data)!
                                
                            }
                            
                            if let plotDetails = dictionary["Plot"] as? String{
                                
                                self.movies[movieTitle]?.plot = plotDetails
                                
                            }
                            
                            if let actors = dictionary["Actors"] as? String{
                                self.movies[movieTitle]?.actors = actors
                            }
                            
                            if let year = dictionary["Year"] as? String{
                                self.movies[movieTitle]?.year = year
                            }
                            
                            self.movieTable.reloadData()
                        }
                        
                    }
                }
            }
        }
        
        task.resume()
    }
    
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .none;
    }
    
    func tableView(_ tableView: UITableView, shouldIndentWhileEditingRowAt indexPath: IndexPath) -> Bool {
        return false;
    }
    
    //function to move an entry at one index to another index
    func tableView(_ tableView: UITableView, moveRowAt sourceIndexPath: IndexPath, to destinationIndexPath: IndexPath) {
        
        let movedObject = self.movieArray[sourceIndexPath.row]
        movieArray.remove(at: sourceIndexPath.row)
        movieArray.insert(movedObject, at: destinationIndexPath.row)

        
        
        }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let selectedItem = movies[movieArray[indexPath.row]]
        
        let alert = UIAlertController(title: selectedItem!.title + " - " + selectedItem!.year, message: selectedItem!.actors + "\n \n " + selectedItem!.plot, preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "dismiss", style: .default, handler: {action -> Void in
            
        })
        
        let image = (selectedItem?.poster)!
        
        alert.addImage(image: image)
        
        
        
        alert.addAction(okAction)
        
        self.present(alert, animated: true, completion: nil)
        
    }
    
    

    
    func loadInfo(title: String) -> MovieInfo {
        var movieInfo = MovieInfo()
        
        movieInfo.title = title
        
        return movieInfo
    }
}

struct MovieInfo {
    var title: String = ""
    var year: String = ""
    var actors: String = ""
    
    var plot: String = ""
    var poster: UIImage = UIImage()
    
    var ratings: Dictionary = Dictionary<String, String>()
}


