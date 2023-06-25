package io.itch.fr.quizgame.feature_end.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun EndPage(
    totalScore: Int,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Quiz completed!")

        Text(text = "Total Score: $totalScore")

        Button(
            onClick = { navController.navigate("start") }
        ) {
            Text(text = "Play Again")
        }

        Button(
            onClick = { navController.navigate("history") }
        ) {
            Text(text = "View History")
        }
    }
}
