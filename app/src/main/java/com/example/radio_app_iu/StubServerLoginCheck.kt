package com.example.radio_app_iu

class StubServerLoginCheck {

    val userList : MutableList<Triple<String, String, String>> = mutableListOf()
    val user1 = Triple("Moderator1", "abc", "Daniel")
    val user2 = Triple("Moderator2", "xyz", "Vera")


    //fun createUser creates a user and adds it to the userList --> for later implementation


    fun checkLogin(username : String, password : String) : Boolean {
        userList.add(user1)
        userList.add(user2)

        var passing = false
        userList.forEach {
            if(it.first == username && it.second == password) {
                passing = true
            }
        }
        return passing
    }
}