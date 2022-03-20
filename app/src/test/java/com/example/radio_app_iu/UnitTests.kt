package com.example.radio_app_iu

import org.junit.Test

import org.junit.Assert.*

var stubServerLoginCheck = StubServerLoginCheck()

class UnitTests {
    @Test
    fun checkCorrectLogin() {
        assertTrue("valid login", stubServerLoginCheck.checkLogin("Moderator2", "xyz"))
    }

    @Test
    fun checkIncorrectLogin() {
        assertFalse("invalid login", stubServerLoginCheck.checkLogin("Moderator1", "xyz"))
    }
}