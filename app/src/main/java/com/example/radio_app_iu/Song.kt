package com.example.radio_app_iu

class Song(var title : String, var interpret : String, var album : String) {

    override fun toString() : String {
        return title + " by " + interpret + " from album: " + album
    }

    fun getTitleAndInterpret() : String{
        return title + " - " + interpret
    }

}