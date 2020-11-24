package com.example.lab9

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class MovieActivity : AppCompatActivity() {

    private var streamingName: String? = null
    private var streamingUrl: String? = null

    lateinit var streamText:TextView
    lateinit var reviewText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(findViewById(R.id.toolbar))

        streamText = findViewById(R.id.StreamingText)
        reviewText = findViewById(R.id.ReviewEdit)

        streamingName = intent.getStringExtra("StreamingName")
        streamingUrl = intent.getStringExtra("StreamingUrl")

        streamingName.let { streamText.text  = getString(R.string.streamingOutput, streamingName)}
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            loadWebSite()
        }
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("review", reviewText.text.toString())
        setResult(Activity.RESULT_OK, data)


        super.onBackPressed()
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("streaming", streamingName)
        outState.putString("url", streamingUrl)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        streamingName = savedInstanceState.getString("streaming", "")
        streamingUrl = savedInstanceState.getString("url", "")
    }

    private fun loadWebSite(){

        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = streamingUrl?.let{ Uri.parse(streamingUrl)}

        startActivity(intent)
    }
}