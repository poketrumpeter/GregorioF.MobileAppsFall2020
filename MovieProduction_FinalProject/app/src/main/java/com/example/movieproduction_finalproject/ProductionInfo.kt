package com.example.movieproduction_finalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.math.cos

class ProductionInfo : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private val REQUEST_CODE:Int = 1

    private lateinit var movieInProduction:Movie
    lateinit var budgetDisplay: TextView
    lateinit var postButton:Button

    //Actors
    lateinit var leadSpinner: Spinner
    lateinit var sup1Spinner: Spinner
    lateinit var sup2Spinner: Spinner

    //Options
    lateinit var practicalSpinner: Spinner
    lateinit var equipmentSpinner: Spinner
    lateinit var directingSpinner: Spinner
    lateinit var costumesSpinner: Spinner

    private var runningBudget:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_production_info)
        setSupportActionBar(findViewById(R.id.toolbar))

        budgetDisplay = findViewById(R.id.BudgetView1)
        postButton = findViewById(R.id.PostProductionButton)

        //Spinners

        leadSpinner = findViewById(R.id.LeadActorSpinner)
        leadSpinner.onItemSelectedListener = this

        sup1Spinner = findViewById(R.id.SupActor1)
        sup1Spinner.onItemSelectedListener = this

        sup2Spinner = findViewById(R.id.SupActor2)
        sup2Spinner.onItemSelectedListener = this

        practicalSpinner = findViewById(R.id.PracticalEffectsSpinner)
        practicalSpinner.visibility = View.INVISIBLE
        practicalSpinner.onItemSelectedListener = this

        equipmentSpinner = findViewById(R.id.EquipmentSpinner)
        equipmentSpinner.visibility = View.INVISIBLE
        equipmentSpinner.onItemSelectedListener = this

        directingSpinner = findViewById(R.id.DirectingSpinner)
        directingSpinner.visibility = View.INVISIBLE
        directingSpinner.onItemSelectedListener = this

        costumesSpinner = findViewById(R.id.CostumesSpinner)
        costumesSpinner.visibility = View.INVISIBLE
        costumesSpinner.onItemSelectedListener = this

        movieInProduction =  intent.getSerializableExtra("movieDetails") as Movie

        movieInProduction.setOption(0, 0, "practicalEffect")
        movieInProduction.setOption(0, 0, "equipment")
        movieInProduction.setOption(0, 0, "directing")
        movieInProduction.setOption(0, 0, "costumes")

        runningBudget = movieInProduction.getCurrentBudget()
        budgetDisplay.text = getString(R.string.BudgetIndicator, runningBudget)


        postButton.setOnClickListener{

            //Create a new intent
            var intent2 = Intent(this, Post_Production::class.java)

            intent2.putExtra("updatedMovie", movieInProduction)

            startActivityForResult(intent2, REQUEST_CODE)

        }

    }

    fun onCheckboxClicked(view: View) {

        if(view is CheckBox){

            val checked: Boolean = view.isChecked

            when(view.id){

                R.id.practicalEffect -> {
                    if(checked){
                        //Show Spinner
                        practicalSpinner.visibility = View.VISIBLE
                    }
                    else{
                        //Hide spinner
                        practicalSpinner.visibility = View.INVISIBLE
                        //Set val to 0
                        practicalSpinner.setSelection(0)

                    }
                }

                R.id.EquipmentCheck -> {
                    if(checked){
                        //Show Spinner
                        equipmentSpinner.visibility = View.VISIBLE
                    }
                    else{
                        //Hide spinner
                        equipmentSpinner.visibility = View.INVISIBLE
                        //Set val to 0
                        equipmentSpinner.setSelection(0)
                    }
                }

                R.id.DirectingCheck -> {
                    if(checked){
                        //Show Spinner
                        directingSpinner.visibility = View.VISIBLE
                    }
                    else{
                        //Hide spinner
                        directingSpinner.visibility = View.INVISIBLE
                        //Set val to 0
                        directingSpinner.setSelection(0)
                    }
                }

                R.id.CostumesCheck -> {
                    if(checked){
                        //Show Spinner
                        costumesSpinner.visibility = View.VISIBLE
                    }
                    else{
                        //Hide spinner
                        costumesSpinner.visibility = View.INVISIBLE
                        //Set val to 0
                        costumesSpinner.setSelection(0)
                    }
                }
            }
        }
    }


    fun convertActingOption(option: String, type:String){
        var actorPlaceholder:String
        var actorCostPlaceholder: Int
        var actorRank:Int
        if (option == "Award Winner(100K)"){
            actorPlaceholder = "Award Winner"
            actorRank = 5
            actorCostPlaceholder = 100000
        }
        else if(option == "High-Profile (75K)"){
            actorPlaceholder = "High Profile"
            actorRank = 4
            actorCostPlaceholder = 75000
        }
        else if(option == "Average (50K)"){
            actorPlaceholder = "Average"
            actorRank = 3
            actorCostPlaceholder = 50000
        }
        else if(option == "Indie (25K)"){
            actorPlaceholder = "Indie"
            actorRank = 2
            actorCostPlaceholder = 25000
        }
        else if(option == "No-Name (5K)"){
            actorPlaceholder = "No Name"
            actorRank = 1
            actorCostPlaceholder = 5000
        }
        else{
            actorPlaceholder = ""
            actorRank = 0
            actorCostPlaceholder = 0
        }

        movieInProduction.setActor(actorRank, actorCostPlaceholder, type)

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


        movieInProduction.setOption(optionrank, optionCost, type)
    }

    fun updateBudget(){
        runningBudget = movieInProduction.calculateBudget()
        postButton.isEnabled = true
        postButton.setText(R.string.ProductionTransitionLabel)
        if(runningBudget == "Error"){
            postButton.isEnabled = false
            postButton.setText(R.string.error)
        }

        budgetDisplay.text = getString(R.string.BudgetIndicator, runningBudget)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        if(parent.id == R.id.LeadActorSpinner){
            convertActingOption(parent.getItemAtPosition(pos) as String, "lead")
        }
        else if(parent.id == R.id.SupActor1){
            convertActingOption(parent.getItemAtPosition(pos) as String, "sup1")
        }
        else if(parent.id == R.id.SupActor2){
            convertActingOption(parent.getItemAtPosition(pos) as String, "sup2")
        }
        else if(parent.id == R.id.PracticalEffectsSpinner){
            convertSpendingOption(parent.getItemAtPosition(pos) as String, "practicalEffect")
        }
        else if(parent.id == R.id.EquipmentSpinner){
            convertSpendingOption(parent.getItemAtPosition(pos) as String, "equipment")
        }
        else if(parent.id == R.id.DirectingSpinner){
            convertSpendingOption(parent.getItemAtPosition(pos) as String, "directing")
        }
        else if(parent.id == R.id.CostumesSpinner){
            convertSpendingOption(parent.getItemAtPosition(pos) as String, "costumes")
        }

        updateBudget()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("savedMovie", movieInProduction)
        outState.putInt("practicalVisible", practicalSpinner.visibility)
        outState.putInt("directingVisible", directingSpinner.visibility)
        outState.putInt("equipmentVisible", equipmentSpinner.visibility)
        outState.putInt("costumesVisible", costumesSpinner.visibility)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        movieInProduction = savedInstanceState.getSerializable("savedMovie") as Movie
        practicalSpinner.visibility = savedInstanceState.getInt("practicalVisible")
        directingSpinner.visibility = savedInstanceState.getInt("directingVisible")
        equipmentSpinner.visibility = savedInstanceState.getInt("equipmentVisible")
        costumesSpinner.visibility = savedInstanceState.getInt("costumesVisible")

    }
}