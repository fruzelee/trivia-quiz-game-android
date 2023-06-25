package io.itch.fr.quizgame.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.itch.fr.quizgame.MainActivity
import org.junit.Rule
import org.junit.Test

class QuizUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testQuizPage() {
        // Launch the MainActivity
        composeTestRule.setContent {
            MainActivity()
        }

        // Perform UI interactions and assertions
        composeTestRule.onNodeWithText("Start Quiz").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Question 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Option 1").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Next Question").assertIsDisplayed().performClick()
        // Add more interactions and assertions as needed
    }
}
