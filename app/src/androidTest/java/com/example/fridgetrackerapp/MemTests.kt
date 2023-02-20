package com.example.fridgetrackerapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fridgetrackerapp.ui.theme.FridgeTrackerAppTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class MemTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openWriteToMemTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
    }

    @Test
    fun closeWriteToMemTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertDoesNotExist()
    }

    @Test
    fun writeToTextField() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))
    }

    @Test
    fun writeToTextFieldAndSave() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()

    }

    @Test
    fun testMemPersistence() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithText("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("BANANA")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("BANANA"))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()



        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nBANANA").assertExists()
    }

    @Test
    fun writeToTextFieldAndSaveStressTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        composeTestRule.onNodeWithText("Internal Storage in Android").assertExists()
        composeTestRule.onNodeWithTag("wordField").performTextInput("LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG")
        composeTestRule.onNodeWithTag("wordField").assert(hasText("LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG"))

        composeTestRule.onNodeWithText("Write Data to Internal Storage").performClick()
        composeTestRule.onNodeWithText("Read Data from Internal Storage").performClick()
        composeTestRule.onNodeWithText("Data will appear below : \nLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG").assertExists()

    }
}