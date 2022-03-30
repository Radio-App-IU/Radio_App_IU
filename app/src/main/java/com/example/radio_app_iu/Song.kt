package com.example.radio_app_iu

import android.media.MediaPlayer

class Song(var title : String, var interpret : String, var album : String, var refSong : Int, var refAlbumImage : Int, var year : String, var length : String) {

    //called by song information button
    override fun toString() : String {
        return title + " by " + interpret + " from album: " + album
    }

    fun getTitleAndInterpret() : String{
        return title + "    -    " + interpret
    }
}