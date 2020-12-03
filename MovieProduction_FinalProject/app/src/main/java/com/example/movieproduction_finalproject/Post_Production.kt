package com.example.movieproduction_finalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class Post_Production : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val REQUEST_CODE:Int=3

    lateinit var makeMovieButton: Button

    lateinit var finalMovie: Movie

    lateinit var budgetView:TextView

    lateinit var messageEdit:EditText

    lateinit var editingSpinner: Spinner
    lateinit var VFXSpinner: Spinner
    lateinit var musicSpinner: Spinner
    lateinit var marketingSpinner: Spinner

    private var finalBudget:String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post__production)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        messageEdit = findViewById(R.id.MarketingMessage)

        makeMovieButton = findViewById(R.id.MakeMovieButton)

        finalMovie = intent.getSerializableExtra("updatedMovie") as Movie

        finalBudget = finalMovie.calculateBudget()

        budgetView = findViewById(R.id.BudgetView2)
        budgetView.setText(getString(R.string.BudgetIndicator, finalBudget))

        editingSpinner = findViewById(R.id.EditingSpinner)
        editingSpinner.onItemSelectedListener = this

        VFXSpinner = findViewById(R.id.VFXSpinner)
        VFXSpinner.onItemSelectedListener = this

        musicSpinner = findViewById(R.id.MusicSpinner)
        musicSpinner.onItemSelectedListener = this

        marketingSpinner = findViewById(R.id.MarketingSpinner)
        marketingSpinner.onItemSelectedListener = this


        //Button Listener
        makeMovieButton.setOnClickListener{

            finalMovie.setMarketingMessage(messageEdit.text.toString())

            //Navigate to final Intent
            val finalIntent = Intent(this, FinalResult::class.java)
            finalIntent.putExtra("finished", finalMovie)

            startActivityForResult(finalIntent, REQUEST_CODE)
        }


    }

    fun convertSpendingOption(option: String, type:String){
        var optionrank: Int = 0
        var optionCost: Int = 0

        if(option == "5K"){
            optionrank = 1
            optionCost = 5000
        }
        else if(option == "20K"){
            optionrank = 2
            optionCost = 20000
        }
        else if(option == "50K"){
            optionrank = 3
            optionCost = 50000
        }
        else if(option == "75K"){
            optionrank = 4
            optionCost = 75000
        }
        else if(option == "100K"){
            optionrank = 5
            optionCost = 100000
        }
        else if(option == "200K"){
            optionrank = 6
            optionCost = 200000
        }


        finalMovie.setOption(optionrank, optionCost, type)
    }

    fun updateBudget(){
        finalBudget = finalMovie.calculateBudget()
        makeMovieButton.isEnabled = true
        makeMovieButton.setText(R.string.makeMovieButton)
        if(finalBudget == "Error"){
            makeMovieButton.isEnabled = false
            makeMovieButton.setText(R.string.error)
        }

        budgetView.text = getString(R.string.BudgetIndicator, finalBudget)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

        if(parent.id == R.id.EditingSpinner){
            convertSpendingOption(parent.getItemAtPosition(position) as String, "editing")
        }
        else if(parent.id == R.id.VFXSpinner){
            convertSpendingOption(parent.getItemAtPosition(position) as String, "VFX")
        }
        else if(parent.id == R.id.MusicSpinner){
            convertSpendingOption(parent.getItemAtPosition(position) as String, "music")
        }
        else if(parent.id == R.id.MarketingSpinner){
            convertSpendingOption(parent.getItemAtPosition(position) as String, "marketing")
        }
        updateBudget()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("savedMovie", finalMovie)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        finalMovie = savedInstanceState.getSerializable("savedMovie") as Movie
    }
}