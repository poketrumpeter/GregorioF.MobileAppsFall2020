package com.example.movieproduction_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinalResult : AppCompatActivity() {
    private val REQUEST_CODE:Int = 4

    lateinit var titleView: TextView
    lateinit var studioView: TextView
    lateinit var boxOfficeView: TextView
    lateinit var reviewView: TextView

    lateinit var finishedFilm:Movie

    lateinit var startOverButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_result)

        titleView = findViewById(R.id.MovieTitle)
        studioView = findViewById(R.id.studioView)
        boxOfficeView = findViewById(R.id.BoxofficeResult)
        reviewView = findViewById(R.id.ReceptionDisplay)

        finishedFilm = intent.getSerializableExtra("finished") as Movie

        titleView.text = finishedFilm.getTitle()
        studioView.text = finishedFilm.getStudio()

        finishedFilm.calculateResult()

        //Get results
        boxOfficeView.text = getString(R.string.BoxOfficeDisplay, finishedFilm.getBoxOffice().toString())
        var result:String = ""

        if (finishedFilm.isSuccess()){
            result += getString(R.string.boxOfficeSuccess)
        }
        else{
            result += getString(R.string.boxOfficeFail)
        }

        if (finishedFilm.getGenre() == "Drama"){

            if (finishedFilm.isDramaSuccess()){
                result+=getString(R.string.DramaSuccess)
            }
            else{
                result+=getString(R.string.DramaFail)
            }
        }
        else if (finishedFilm.getGenre() == "Action"){
            if (finishedFilm.isActionSuccess()){
                result+=getString(R.string.ActionSuccess)
            }
            else{
                result+=getString(R.string.ActionFail)
            }
        }
        else{
            result+=getString(R.string.Horror)
        }

        reviewView.text = result

        startOverButton = findViewById(R.id.StartOverButton)

        startOverButton.setOnClickListener {
            val returnIntent = Intent(this, MainActivity::class.java)

            startActivityForResult(returnIntent, REQUEST_CODE)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("savedMovie", finishedFilm)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        finishedFilm = savedInstanceState.getSerializable("savedMovie") as Movie
    }
}