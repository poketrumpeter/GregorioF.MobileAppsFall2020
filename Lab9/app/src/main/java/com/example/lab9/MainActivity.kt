package com.example.lab9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var movieChoice: String = ""
    private val REQUEST_CODE = 1

    lateinit var streamingButton: Button
    lateinit var reviewText:TextView

    private var myMovieStreaming = MovieStreaming()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        streamingButton = findViewById(R.id.reviewButton)
        reviewText = findViewById(R.id.reviewOutput)

        //Event Listener
        streamingButton.setOnClickListener{
            myMovieStreaming.suggestStreaming(movieChoice)

            //intent
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("StreamingName", myMovieStreaming.name)
            intent.putExtra("StreamingUrl", myMovieStreaming.url)

            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {

                //Is the BigBudget button selected?
                R.id.wonderWoman ->
                    if (checked) {
                        //set entertainment spending to big_budget
                        movieChoice = "WonderWoman"
                    }

                R.id.blackWidow ->
                    if (checked) {
                        //set entertainment spending to indie
                        movieChoice = "BlackWidow"
                    }

                R.id.quietPlace ->
                    if(checked) {
                        //set movieChoice to A Quiet Place Pt 2
                        movieChoice = "AQuietPlace"
                    }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)){
            reviewText.setText((data?.let { data.getStringExtra("review") }))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("movie", movieChoice)
        outState.putString("review", reviewText.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        movieChoice = savedInstanceState.getString("movie", "")
        reviewText.text = savedInstanceState.getString("review", "")
    }

}