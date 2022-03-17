package com.example.radio_app_iu

//singleton for passing data to the radioHostActivity
object StubEvaluationDB {

    //var playlistEvaluationList : MutableList<StubServerPlaylistEvaluation> = mutableListOf()
    //var radioHostEvaluationList : MutableList<StubServerRadioHostEvaluation> = mutableListOf()

    var idPlaylistEvaluation : Int = 0
    var playlistEvaluation : String = ""
    var playlistEvaluationNickname : String = ""
    var playlistRating : Int = 1

    var idRadioHostEvaluation : Int = 0
    var radioHostEvaluation : String = ""
    var radioHostEvaluationNickname : String = ""
    var radioHostRating : Int = 1
    var moderator : String = ""

    fun getPlaylistEvaluationsElement() : String {
        return this.idPlaylistEvaluation.toString() + "\n" +
                this.playlistEvaluation + "\n" +
                this.playlistEvaluationNickname
    }

    fun getPlaylistElementRating() : Int {
        return this.playlistRating
    }

    fun getRadioHostEvaluationsElement() : String {
        return this.idRadioHostEvaluation.toString() + "\n" +
                this.radioHostEvaluation + "\n" +
                this.radioHostEvaluationNickname
    }

    fun getRadioHostElementRating() : Int {
        return this.playlistRating
    }
}
