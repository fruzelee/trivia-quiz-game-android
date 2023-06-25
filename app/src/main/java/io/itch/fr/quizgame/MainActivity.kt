package io.itch.fr.quizgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.itch.fr.quizgame.feature_start.presentation.StartPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Preview
    @Composable
    fun MyApp() {
        // You can use a Navigation component or a simple composable function for navigation
        StartPage()
    }
}