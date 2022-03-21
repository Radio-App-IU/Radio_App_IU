package com.example.radio_app_iu

import com.example.radio_app_iu.com.example.radio_app_iu.WishSong

//singleton for saving the lists of evaluations and the wished songs
object StubEvaluationDB {

    var playlistEvaluationList: MutableList<PlaylistEvaluation> = mutableListOf()
    var radioHostEvaluationList: MutableList<RadioHostEvaluation> = mutableListOf()
    var wishSongList: MutableList<WishSong> = mutableListOf()
}