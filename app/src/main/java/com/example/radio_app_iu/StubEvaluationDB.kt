package com.example.radio_app_iu

object StubEvaluationDB {

    //var playlistEvaluationList : MutableList<StubServerPlaylistEvaluation> = mutableListOf()
    //var radioHostEvaluationList : MutableList<StubServerRadioHostEvaluation> = mutableListOf()

    var idPlaylistEvaluation : Int = 0
    var playlistEvaluation : String = ""
    var playlistEvaluationNickname : String = ""

    var idRadioHostEvaluation : Int = 0
    var radioHostEvaluation : String = ""
    var radioHostEvaluationNickname : String = ""
    var moderator : String = ""

    fun getPlaylistEvaluationsElement() : String {
        return this.idPlaylistEvaluation.toString() + "\n" +
                this.playlistEvaluation + "\n" +
                this.playlistEvaluationNickname
    }

    fun getRadioHostEvaluationsElement() : String {
        return this.idRadioHostEvaluation.toString() + "\n" +
                this.radioHostEvaluation + "\n" +
                this.radioHostEvaluationNickname
    }
}
