package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import io.itch.fr.quizgame.feature_start.presentation.StartPage
import io.itch.fr.quizgame.feature_start.presentation.StartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaQuizApp()
        }
    }

    @Composable
    private fun TriviaQuizApp() {
        val startViewModel: StartViewModel by viewModels()

        StartPage(
            onStartQuizClicked = { /* Handle start quiz button click */ },
            onViewHistoryClicked = { /* Handle view history button click */ }
        )
    }
}