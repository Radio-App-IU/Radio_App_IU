package com.example.radio_app_iu

class RadioHostEvaluation (var idRadioHostEvaluation : Int = 0,
                           var radioHostEvaluation : String = "",
                           var radioHostEvaluationNickname : String = "",
                           var radioHostRating : Int = 1,
                           var moderator : String = "ein Moderator",
                           var radioHostTimestamp : String) {

    fun returnId() : Int {
        return this.idRadioHostEvaluation
    }

    fun returnRadioHostEvaluation() : String {
        return this.radioHostEvaluation
    }

    fun returnNickname() : String {
        return this.radioHostEvaluationNickname
    }

    fun returnRating() : Int {
        return this.radioHostRating
    }

    fun returnTimestamp() : String {
        return this.radioHostTimestamp
    }
}