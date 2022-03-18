package com.example.radio_app_iu

//singleton for saving the lists of evaluations
object StubEvaluationDB {

    var playlistEvaluationList : MutableList<PlaylistEvaluation> = mutableListOf()
    var radioHostEvaluationList : MutableList<RadioHostEvaluation> = mutableListOf()
}
