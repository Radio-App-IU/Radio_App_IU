package com.example.radio_app_iu

//singleton
object EvaluationLists {

    lateinit var playlistEvaluationList : MutableList<PlaylistEvaluation>
    lateinit var radioHostEvaluationList : MutableList<RadioHostEvaluation>

    var playEva = PlaylistEvaluation()
    var radHostEva = RadioHostEvaluation()

    //returns the content of one element of the PlaylistEvaluations list
    fun getPlaylistEvaluationsElement() : String {
        return playEva.idPlaylistEvaluation.toString() + "/n" +
               playEva.playlistEvaluation + "/n" +
               playEva.playlistEvaluationNickname
    }

    //returns the content of one element of the RadioHostEvaluations list
    fun getRadioHostEvaluationsElement() : String {
        return radHostEva.idRadioHostEvaluation.toString() + "/n" +
               radHostEva.radioHostEvaluation + "/n" +
               radHostEva.radioHostEvaluationNickname
    }
}

// Problem to solve: how do each moderator gets only their evaluations? --> passing the used
// username to a variable and get it from there (getter for current moderator? and putting it in
// the moderator evaluation?) iterating and filtering with use of if to only display the right elements