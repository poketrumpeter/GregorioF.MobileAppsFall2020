package com.example.lab9

import java.text.FieldPosition

data class MovieStreaming(var name:String = "", var url:String = "") {

    fun suggestStreaming(movie: String){
        setStreamingServiceName(movie)
        setStreamingUrl(movie)

    }

    private fun setStreamingServiceName(movie:String){
        name = when(movie){
            "WonderWoman" -> "on HBO Max"
            "BlackWidow" -> "on Disney +"
            "AQuietPlace" -> "at AMC Theatres"
            else -> "Where you like to see movies"
        }
    }

    private fun setStreamingUrl(movie:String){
        url = when(movie){
            "WonderWoman" -> "https://play.hbomax.com"
            "BlackWidow" -> "https://www.disneyplus.com"
            "AQuietPlace" -> "https://www.amctheatres.com"
            else -> "https://www.google.com/search?q=streaming+services&oq=streaming+service&aqs=chrome.0.0i433i457j69i57j0i433j0l5.2560j0j4&sourceid=chrome&ie=UTF-8"
        }
    }

}