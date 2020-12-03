package com.example.movieproduction_finalproject

import java.io.Serializable
import kotlin.math.cos


//https://medium.com/@gaandlaneeraja/how-to-pass-objects-between-android-activities-86f2cfb61bd4

//Used this source to learn how to pass a custom object between activities
//This basically allows the object to be serialized, or formatted for being able to be transported


public class Movie : Serializable{
    private var title: String = ""
    private var studio: String = ""
    private var genre: String = ""
    private var startingBudget: String = ""
    private var currentBudget: Int = 0

    private var leadActor: Int = 0
    private var leadActorCost: Int = 0
    private var supActor1:Int = 0
    private var supActor1Cost:Int = 0
    private var supActor2:Int = 0
    private var supActor2Cost: Int = 0

    private var practicalEffect:Int = 0
    private var practicalEffectCost:Int = 0

    private var equipment: Int = 0
    private var equipmentCost = 0

    private var directing: Int = 0
    private var directingCost = 0

    private var costumes: Int = 0
    private var costumesCost:Int = 0

    private var editing:Int = 0
    private var editingCost:Int = 0

    private var VFX:Int = 0
    private var VFXcost:Int = 0

    private var music:Int = 0
    private var musicCost:Int = 0

    private var marketing:Int = 0
    private var marketingCost:Int = 0

    private var marketingMessage:String = ""

    //OUTPUT MEMBERS
    private var boxOffice:Int = 0
    private var success:Boolean = false
    private var dramaSuccess:Boolean = false
    private var actionSuccess:Boolean = false



    fun getTitle(): String{
        return this.title
    }
    fun getStudio():String{
        return this.studio
    }
    fun getGenre():String{
        return this.genre
    }
    fun getCurrentBudget():String{
        return this.currentBudget.toString()
    }
    fun getBoxOffice():Int{
        return this.boxOffice
    }
    fun isSuccess():Boolean{
        return this.success
    }
    fun isDramaSuccess():Boolean{
        return  this.dramaSuccess
    }
    fun isActionSuccess():Boolean{
        return this.actionSuccess
    }

    fun setTitle(name: String, studio:String){
        this.title = name
        this.studio = studio
    }

    fun setGenre(genreName: String){
        this.genre = genreName
    }

    fun setBudget(startAmount: String, startInt: Int){
        this.startingBudget = startAmount
        this.currentBudget = startInt
    }

    fun setActor(actor: Int, cost: Int, type: String){

        if (type == "lead"){
            this.leadActor = actor
            this.leadActorCost = cost
        }
        else if(type == "sup1"){
            this.supActor1 = actor
            this.supActor1Cost = cost
        }
        else if (type == "sup2"){
            this.supActor2 = actor
            this.supActor2Cost = cost
        }
    }

    fun setOption(optionRank:Int, optionCost: Int, type:String){
        if(type == "practicalEffect"){
            this.practicalEffect = optionRank
            this.practicalEffectCost = optionCost
        }
        else if(type == "equipment"){
            this.equipment = optionRank
            this.equipmentCost = optionCost
        }
        else if(type == "directing"){
            this.directing = optionRank
            this.directingCost = optionCost
        }
        else if(type == "costumes"){
            this.costumes = optionRank
            this.costumesCost = optionCost
        }
        else if(type == "editing"){
            this.editing = optionRank
            this.editingCost = optionCost
        }
        else if(type == "VFX"){
            this.VFX = optionRank
            this.VFXcost = optionCost
        }
        else if(type == "music"){
            this.music = optionRank
            this.musicCost = optionCost
        }
        else if(type == "marketing") {
            this.marketing = optionRank
            this.marketingCost = optionCost
        }

    }

    fun setMarketingMessage(message:String){
        this.marketingMessage = message
    }

    fun calculateBudget():String{

        var budget:Int = currentBudget
        var output:String
        budget = currentBudget - leadActorCost -
                supActor1Cost - supActor2Cost

        budget = budget - practicalEffectCost - equipmentCost -
                directingCost - costumesCost


        budget = budget - editingCost - VFXcost -
                musicCost - marketingCost

        if(budget < 0){
            return "Error"
        }
        return budget.toString()
    }

    fun calculateResult(){
        var boxOffice:Int = 0

        //Action buff
        if(genre == "Action"){
            boxOffice += 100000
        }
        else{
            boxOffice += 25000
        }

        //Actors
        boxOffice += leadActor * 100000
        boxOffice += supActor1 * 20000
        boxOffice += supActor2 * 20000

        //Options Priority to Directing, Editing, Costumes, Marketing if Drama
        //        Priority to VFX, Costumes, Music, Equipment for others
        if (genre == "Drama"){
            boxOffice += directing * 50000
            boxOffice += editing * 30000
            boxOffice += costumes * 10000
            boxOffice += marketing * 60000
            boxOffice += VFX * 5000
            boxOffice += music * 10000
            boxOffice += equipment * 3000
            boxOffice += practicalEffect * 1000
        }
        else{
            boxOffice += directing * 20000
            boxOffice += editing * 10000
            boxOffice += costumes * 25000
            boxOffice += marketing * 40000
            boxOffice += VFX * 50000
            boxOffice += music * 20000
            boxOffice += equipment * 15000
            boxOffice += practicalEffect * 1000
        }

        if(boxOffice >= currentBudget){
            success = true
        }
        if(boxOffice >= 1000000){
            actionSuccess = true
        }
        if(genre == "drama" && directing >= 3 && editing >= 4){
            dramaSuccess = true
        }

        this.boxOffice = boxOffice
    }

}