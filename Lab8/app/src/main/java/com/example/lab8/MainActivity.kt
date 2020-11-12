 package com.example.lab8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.snackbar.Snackbar

 class MainActivity : AppCompatActivity() {

     var spending: String = ""
     var movie: Boolean = false
     var videoGame: Boolean = false
     var time: String = "later"
     var genre:String = ""

     var movieOutput: String = ""
     var gameOutput: String = ""

     lateinit var output: TextView
     lateinit var spinner: Spinner
     lateinit var switch: Switch

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         output = findViewById(R.id.outputResponse)
         spinner = findViewById(R.id.spinner)
         switch = findViewById(R.id.timeSwitch)
         switch.setOnCheckedChangeListener{_, isChecked ->
             if(isChecked){
                 time = "now"
             }
             else{
                 time = "later"
             }
         }
     }

     fun onRadioButtonClicked(view: View) {
         if (view is RadioButton) {
             val checked = view.isChecked

             when (view.getId()) {

                 //Is the BigBudget button selected?
                 R.id.big_budget ->
                     if (checked) {
                         //set entertainment spending to big_budget
                         spending = "BigBudget"
                     }

                 R.id.indie ->
                     if (checked) {
                         //set entertainment spending to indie
                         spending = "Indie"
                     }
             }
         }
     }

     fun onCheckboxClicked(view: View) {

         if(view is CheckBox){

             val checked: Boolean = view.isChecked

             when(view.id){
                 R.id.movieCheck -> {
                     movie = checked
                 }
                 R.id.gameCheck -> {
                     videoGame = checked
                 }
             }
         }
     }

     fun generateResponse(view: View) {

         //Assign Time
         switch.setOnCheckedChangeListener{_, isChecked ->
             if(isChecked){
                 time = "now"
             }
             else{
                 time = "later"
             }
         }

         genre = spinner.selectedItem as String
         if(spending == "" || (!movie && !videoGame)){
             Snackbar.make(view, "Please select type and/or budget", Snackbar.LENGTH_SHORT).show()
             output.text = ""
             return
         }

         if (spending == "Indie") {
             //decide which genre
             if (genre == "Action") {
                 movieOutput = "Free Fire"
                 gameOutput = "Hades"
             } else if (genre == "Drama") {
                 movieOutput = "High Life"
                 gameOutput = "The Way"
             } else {
                 movieOutput = "Swallow"
                 gameOutput = "Soma"
             }

         }

         //Decide what type of spending for entertainment
         else {
             //decide which genre
             if (genre == "Action") {
                 movieOutput = "Avenger's Endgame"
                 gameOutput = "Assassin's Creed: Valhalla"
             } else if (genre == "Drama") {
                 movieOutput = "Green Book"
                 gameOutput = "Death Stranding"
             } else {
                 movieOutput = "IT"
                 gameOutput = "Resident Evil 2"
             }
         }

         if(movie and !videoGame){

             output.text = getString(R.string.movieCalculation, movieOutput, time)

         }
         else if(!movie and videoGame){

             output.text = getString(R.string.gameCalculation, gameOutput, time)

         }

         else{
             output.text = getString(R.string.bothCalculation, movieOutput, gameOutput, time)
         }

     }

     override fun onSaveInstanceState(outState: Bundle) {
         outState.putString("genre", genre)
         outState.putString("spending", spending)
         outState.putBoolean("movie", movie)
         outState.putBoolean("Videogame", videoGame)
         super.onSaveInstanceState(outState)
     }

     override fun onRestoreInstanceState(savedInstanceState: Bundle) {
         super.onRestoreInstanceState(savedInstanceState)
         spending = savedInstanceState.getString("spending", "")
         movie = savedInstanceState.getBoolean("movie", false)
         videoGame = savedInstanceState.getBoolean("Videogame", false)
         genre = savedInstanceState.getString("genre", "")




     }
 }