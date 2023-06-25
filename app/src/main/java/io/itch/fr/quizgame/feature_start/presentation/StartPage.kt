package io.itch.fr.quizgame.feature_start.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StartPage(onStartQuizClicked: () -> Unit, onViewHistoryClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the Trivia Quiz!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { onStartQuizClicked() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Start Quiz")
        }
        Button(
            onClick = { onViewHistoryClicked() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "View History")
        }
    }
}

@Preview
@Composable
fun PreviewStartPage() {
    StartPage({}, {})
}