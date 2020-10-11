//
//  UIImage.swift
//  MovieReviews - Project 1
//
//  Created by Gregorio Figueroa on 10/11/20.
//  Copyright © 2020 Gregorio Figueroa. All rights reserved.
//

import UIKit

extension UIImage{
    
    func imageWithSize(_ size: CGSize) -> UIImage{
        
        var scaledImageRect = CGRect.zero
        
        let aspectWidth: CGFloat = size.width / self.size.width
        let aspectHeight: CGFloat = size.height / self.size.height
        let aspectRatio: CGFloat = min(aspectWidth, aspectHeight)
        
        scaledImageRect.size.width = self.size.width * aspectRatio
        scaledImageRect.size.height = self.size.height * aspectRatio
        scaledImageRect.origin.x = (size.width - scaledImageRect.size.width) / 2.0
        scaledImageRect.origin.y = (size.height - scaledImageRect.size.height) / 2.0
        
        UIGraphicsBeginImageContextWithOptions(size, false, 0)
        
        self.draw(in: scaledImageRect)
        
        let scaledImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return scaledImage!
        
        
    }
    
}
