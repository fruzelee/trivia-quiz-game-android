package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EndPage(
    navController: NavController,
    score: Int,
    restartQuiz: () -> Unit, // Add the restartQuiz parameter
    onViewHistoryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Score: $score",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                restartQuiz()
                //onPlayAgainClicked()
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Play Again")
        }

        Button(
            onClick = onViewHistoryClicked
        ) {
            Text(text = "View History")
        }
    }
}
