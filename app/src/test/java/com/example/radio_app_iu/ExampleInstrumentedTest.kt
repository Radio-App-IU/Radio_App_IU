package com.example.radio_app_iu

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    //always executed in this class
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun albumDisplayed() {
        onView(withId(R.id.ivAlbum)).check(matches(isDisplayed()))
    }

    @Test
    fun evaluateButtonClickable() {
        onView(allOf(withId(R.id.buEvaluate))).perform(click())
    }

    @Test
    fun wishSongClickable() {
        onView(allOf(withId(R.id.buWishSong))).perform(click())
    }

    @Test
    fun playButtonClickable() {
        onView(allOf(withId(R.id.buPlaybuttoninvisible))).perform(click())
    }

    @Test
    fun infoTextDisplayed() {
        onView(withId(R.id.tvInfoText)).check(matches(isDisplayed()))
    }

    @Test
    fun infoTextEmpty() {
        onView(allOf(withId(R.id.tvInfoText))).check(matches(withText("")))
    }
}