package com.example.radio_app_iu

class PlaylistEvaluation ( var idPlaylistEvaluation : Int = 0,
                           var playlistEvaluation : String = "",
                           var playlistEvaluationNickname : String = "") {

    var evaluationList = EvaluationLists

    fun addToList(){ //Problem to solve: Increase each number of ID by 1 (list.size doesn't work)
        evaluationList.playlistEvaluationList.add(this)
    }
}