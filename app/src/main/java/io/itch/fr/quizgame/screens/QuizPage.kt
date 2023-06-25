package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizPage(
    question: QuizQuestion,
    onAnswerSelected: (QuizOption) -> Unit,
    onTimerExpired: () -> Unit
) {
    val userScore = remember { mutableStateOf(0) }
    val timer = remember { mutableStateOf(10) }

    LaunchedEffect(Unit) {
        startTimer(timer, onTimerExpired)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.questionText,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        question.options.forEach { option ->
            Button(
                onClick = { handleAnswerSelected(option, question, onAnswerSelected, userScore) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = option.optionText)
            }
        }

        Text(
            text = "Time remaining: $timer",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 16.dp)
        )
    }

    LaunchedEffect(Unit) {
        while (timer.value > 0) {
            delay(1000)
            timer.value--
        }
        onTimerExpired()
    }

}

// Handle user's answer selection and scoring
fun handleAnswerSelected(
    option: QuizOption,
    question: QuizQuestion,
    onAnswerSelected: (QuizOption) -> Unit,
    userScore: MutableState<Int>
) {
    if (option.optionId == question.correctOptionId) {
        userScore.value++
    } else {
        if (userScore.value > 0) {
            userScore.value--
        }
    }
    // Proceed to the next question
    onAnswerSelected(option)
}

// Coroutine-based timer implementation
fun startTimer(
    timer: MutableState<Int>,
    onTimerExpired: () -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        while (timer.value > 0) {
            delay(1000)
            timer.value -= 1
        }
        onTimerExpired()
    }
}
