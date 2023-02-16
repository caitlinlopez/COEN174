package com.example.fridgetrackerapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)

class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.fridgetrackerapp", appContext.packageName)
    }
 
    @Test
    fun checkToastOpen() {
        // https://developer.android.com/training/testing/espresso/setup => espresso unit testing (interactive components)
        // can't get this to work: onView is not recognized even when we have the espresso dependency

        // works after adding the espresso dependency and the espresso test runner and importing proper packages
        
        onView(withText("Drawer opened")).check(matches(isDisplayed()))
    } 
}