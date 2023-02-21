package com.example.fridgetrackerapp

import androidx.compose.ui.test.*
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
class UITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openNavBarTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("navigation").performClick()
        composeTestRule.onNodeWithText("nav").assertExists()
    }

    @Test
    fun closeNavBarTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithText("Close").performClick()
        composeTestRule.onNodeWithContentDescription("navigation").assertExists()
    }

    @Test
    fun openFABTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").assertExists()
        composeTestRule.onNodeWithContentDescription("Scan Barcode").assertExists()
    }

    @Test
    fun formButtonTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").performClick()
        // no functionality yet
    }

    @Test
    fun scannerButtonTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Scan Barcode").performClick()
        // no functionality yet
    }

    @Test
    fun closeFABTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("FAB").performClick()
        composeTestRule.onNodeWithContentDescription("Enter Manually").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Scan Barcode").assertDoesNotExist()
    }

    @Test
    fun homeTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("Home").performClick()
        composeTestRule.onNodeWithContentDescription("Home").assertIsSelected()

    }

    @Test
    fun profileTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("Profile").performClick()
        composeTestRule.onNodeWithContentDescription("Profile").assertIsSelected()

    }

    @Test
    fun cartTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("Cart").performClick()
        composeTestRule.onNodeWithContentDescription("Cart").assertIsSelected()

    }

    @Test
    fun settingsTest() {
        composeTestRule.setContent {
            FridgeTrackerAppTheme {
                FridgeTrackerApp()
            }
        }
        composeTestRule.onNodeWithContentDescription("Settings").performClick()
        composeTestRule.onNodeWithContentDescription("Settings").assertIsSelected()

    }
}