package com.example.radio_app_iu

object StubEvaluationDB {

    //var playlistEvaluationList : MutableList<StubServerPlaylistEvaluation> = mutableListOf()
    //var radioHostEvaluationList : MutableList<StubServerRadioHostEvaluation> = mutableListOf()

    var idPlaylistEvaluation : Int = 0
    var playlistEvaluation : String = ""
    var playlistEvaluationNickname : String = ""

    fun getPlaylistEvaluationsElement() : String {
        return this.idPlaylistEvaluation.toString() + "\n" +
                this.playlistEvaluation + "\n" +
                this.playlistEvaluationNickname
    }
}
