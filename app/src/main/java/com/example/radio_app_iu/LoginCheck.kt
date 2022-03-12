package com.example.radio_app_iu

class LoginCheck {

    val users = mapOf("Moderator1" to "abc", "Moderator2" to "xyz")

    fun checkLogin(username : String, password : String) : Boolean {

        if(users.containsKey(username) && users[username]!!.equals(password)){
            return true
        } else return false
    }
}
