//
//  File.swift
//  MovieReviews - Project 1
//
//  Created by Gregorio Figueroa on 10/11/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

extension UIAlertController{
    
    func addImage(image: UIImage){
        
        let maxSize = CGSize(width: 245, height: 300)
        let imgSize = image.size
        
        var ratio: CGFloat!
        
        if(imgSize.width > imgSize.height){
            ratio = maxSize.width / imgSize.width
        }
        else if (imgSize.width < imgSize.height){
            ratio = maxSize.height / imgSize.height
        }
        
        let scaledSize = CGSize(width: imgSize.width * ratio, height: imgSize.height * ratio)
        
        var resizedImg = image.imageWithSize(scaledSize)
        
        if(imgSize.height > imgSize.width){
            let left = (maxSize.width - resizedImg.size.width) / 2.0
            resizedImg = resizedImg.withAlignmentRectInsets(UIEdgeInsets(top: 0, left: -left, bottom: 0, right: 0))
        }
        
        
        let imageAction = UIAlertAction(title: "", style: .default, handler: nil)
        
        imageAction.isEnabled = false
        
        imageAction.setValue(resizedImg.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        self.addAction(imageAction)
    }
}


