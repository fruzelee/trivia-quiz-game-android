package io.itch.fr.quizgame.feature_quiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizPage(
    viewModel: QuizViewModel = hiltViewModel(),
    onTimerExpired: () -> Unit
) {
    val currentQuestion by viewModel.currentQuestion.collectAsState()
    val score by viewModel.score.collectAsState()
    val timerValue by viewModel.timerValue.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentQuestion != null) {
            Text(text = currentQuestion?.question.toString())
            Spacer(modifier = Modifier.height(16.dp))
            currentQuestion?.options?.forEachIndexed { index, option ->
                Button(
                    onClick = { viewModel.submitAnswer(index) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = option)
                }
            }
        } else {
            Text(text = "Quiz Completed!")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Score: $score")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Time Left: $timerValue")
    }

    // Timer handling
    LaunchedEffect(Unit) {
        viewModel.startTimer()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.cancelTimer()
        }
    }
    LaunchedEffect(timerValue) {
        if (timerValue <= 0) {
            onTimerExpired()
        }
    }
}
