package com.example.movieproduction_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val REQUEST_CODE = 1

    lateinit var titlesDisplay: TextView
    lateinit var genreDisplay: TextView
    lateinit var budgetDisplay: TextView
    lateinit var titleView: EditText
    lateinit var studioView: EditText
    lateinit var budget: Spinner
    lateinit var startButton: Button
    lateinit var budgetSpinner: Spinner

    private var newMovie = Movie()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleView = findViewById(R.id.TitleEntry)
        studioView = findViewById(R.id.StudioEntry)
        budget = findViewById(R.id.moneySpinner)


        titlesDisplay = findViewById(R.id.TitleDisplay)
        genreDisplay = findViewById(R.id.GenreDisplay)
        budgetDisplay = findViewById(R.id.BudgetDisplay)

        budgetSpinner = findViewById(R.id.moneySpinner)
        budgetSpinner.onItemSelectedListener = this


    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {

                //Is the BigBudget button selected?
                R.id.HorrorRadio ->
                    if (checked) {
                        //set Genre to Horror
                        newMovie.setGenre("Horror")
                        displayInfo()
                    }

                R.id.ActionRadio ->
                    if (checked) {
                        //set Genre to Action
                        newMovie.setGenre("Action")
                        displayInfo()
                    }

                R.id.DramaRadio ->
                    if (checked){
                        //Set Genre to Drama
                        newMovie.setGenre("Drama")
                        displayInfo()
                    }
            }
        }
    }

    private fun convertMoney(startingBudget:String){
        var startVal:Int

        if (startingBudget == "1 Million (Big Budget)"){
            startVal = 1000000
        }
        else if (startingBudget == "750K (Standard)"){
            startVal = 750000
        }
        else if (startingBudget == "250K (Small Studio)"){
            startVal = 250000
        }
        else if(startingBudget == "75K (Indie)"){
            startVal = 75000
        }
        else{
            startVal = 0
        }

        newMovie.setBudget(startingBudget, startVal)
    }

    fun getTitles(){
        var title: String = titleView.text.toString()
        var studio:String = studioView.text.toString()
        newMovie.setTitle(title, studio)
    }

    fun displayInfo(){
        getTitles()

        titlesDisplay.text = getString(R.string.titlesDisplay, newMovie.getTitle(), newMovie.getStudio())
        genreDisplay.text = getString(R.string.genreDisplay, newMovie.getGenre())
        budgetDisplay.text = getString(R.string.budgetDisplay, newMovie.getCurrentBudget())

    }

    fun StartProduction(view: View){
         displayInfo()

        if (newMovie.getTitle() == ""){
            Snackbar.make(view, "Please give your Movie a title", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
            return
        }
        else if (newMovie.getStudio() == ""){
            Snackbar.make(view, "Please create a studio, that you will release the movie under", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
            return
        }
        else if(newMovie.getGenre() == ""){
            Snackbar.make(view, "Please specify a genre", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
            return
        }

        //transition to next activity
        val intent = Intent(this, ProductionInfo::class.java)
        intent.putExtra("movieDetails", newMovie)

        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

        if (parent.id == R.id.moneySpinner){
            convertMoney(parent.getItemAtPosition(position) as String)
            displayInfo()
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("savedMovie", newMovie)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        newMovie = savedInstanceState.getSerializable("savedMovie") as Movie
    }

}