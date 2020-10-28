package com.example.lab7gaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var choiceImage: ImageView
    lateinit var slayMonster: Button
    lateinit var kissGirl: Button
    lateinit var responseOutput: TextView
    lateinit var nameEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choiceImage = findViewById(R.id.choiceImage)
    }

    fun showMonsterImage(view: View){

        choiceImage.setImageResource(R.drawable.geralt_vs_giant)

    }

    fun showKissImage(view: View){

        choiceImage.setImageResource(R.drawable.geralt_kissing_triss)

    }

}
