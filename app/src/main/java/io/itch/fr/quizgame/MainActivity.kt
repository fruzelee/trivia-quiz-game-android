package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.itch.fr.quizgame.navigation.QuizAppNavigation
import io.itch.fr.quizgame.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                QuizApp()
            }
        }
    }
}

@Composable
fun QuizApp() {
    val navController = rememberNavController()

    QuizAppNavigation(navController)
}
