package com.example.radio_app_iu

class PlaylistEvaluation (var idPlaylistEvaluation : Int = 0,
                          var playlistEvaluation : String = "",
                          var playlistEvaluationNickname : String = "",
                          var playlistRating : Int,
                          var playlistTimestamp : String) {

    fun returnId() : Int {
        return this.idPlaylistEvaluation
    }

    fun returnPlaylistEvaluation() : String {
        return this.playlistEvaluation
    }

    fun returnNickname() : String {
        return this.playlistEvaluationNickname
    }

    fun returnRating() : Int {
        return this.playlistRating
    }

    fun returnTimestamp() : String {
        return this.playlistEvaluation
    }
}