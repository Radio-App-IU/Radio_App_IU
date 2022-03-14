package com.example.radio_app_iu

class RadioHostEvaluation ( var idRadioHostEvaluation : Int = 0,
                            var radioHostEvaluation : String = "",
                            var radioHostEvaluationNickname : String = "",
                            var moderator : String = "") {

    var evaluationList = EvaluationLists

    fun addToList(){
        evaluationList.radioHostEvaluationList.add(this)
    }
}