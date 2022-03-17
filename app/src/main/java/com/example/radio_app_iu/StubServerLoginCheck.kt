package com.example.radio_app_iu

class StubServerLoginCheck {

    private val users = mapOf("Moderator1" to "abc", "Moderator2" to "xyz")

    fun checkLogin(username : String, password : String) : Boolean {

        return users.containsKey(username) && users[username]!!.equals(password)
    }
}
