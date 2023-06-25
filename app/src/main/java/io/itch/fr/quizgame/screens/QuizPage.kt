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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion

@Composable
fun QuizPage(
    question: QuizQuestion,
    onAnswerSelected: (QuizOption) -> Unit,
    onTimerExpired: () -> Unit
) {
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
                onClick = { onAnswerSelected(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = option.optionText)
            }
        }

        // Timer implementation goes here
        // Call onTimerExpired when the timer expires
    }
}
