package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.itch.fr.quizgame.feature_quiz.presentation.QuizPage
import io.itch.fr.quizgame.feature_start.presentation.StartPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "start") {
            composable("start") {
                StartPage()
            }
            composable("quiz") {
                QuizPage()
            }
            composable("history") {
                //HistoryPage()
            }
            // Add other composable destinations as needed
        }
    }
}
