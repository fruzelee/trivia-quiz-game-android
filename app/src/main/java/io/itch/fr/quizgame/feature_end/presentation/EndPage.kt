package io.itch.fr.quizgame.feature_end.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.itch.fr.quizgame.feature_quiz.presentation.QuizViewModel

@Composable
fun EndPage(
    viewModel: QuizViewModel = hiltViewModel(),
    onPlayAgainClicked: () -> Unit,
    onViewHistoryClicked: () -> Unit
) {
    val score by viewModel.score.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz Completed!")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total Score: $score")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onPlayAgainClicked) {
            Text(text = "Play Again")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onViewHistoryClicked) {
            Text(text = "View History")
        }
    }
}
