package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import io.itch.fr.quizgame.navigation.QuizAppNavigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp()
        }
    }
}

@Composable
fun QuizApp() {
    val navController = rememberNavController()

    QuizAppNavigation(navController)
}
