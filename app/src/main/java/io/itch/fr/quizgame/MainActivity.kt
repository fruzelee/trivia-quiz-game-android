package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.itch.fr.quizgame.feature_start.presentation.StartPage

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizGameApp()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun QuizGameApp() {
    MaterialTheme {
        Surface {
            StartPage()
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewQuizGameApp() {
    QuizGameApp()
}
