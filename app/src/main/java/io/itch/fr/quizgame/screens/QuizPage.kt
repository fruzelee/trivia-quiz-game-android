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
import androidx.navigation.NavController
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizPage(
    navController: NavController,
    questions: List<QuizQuestion>
) {
    val currentQuestionIndex = remember { mutableStateOf(0) }
    val currentQuestion = questions.getOrNull(currentQuestionIndex.value)
    val timer = remember { mutableStateOf(10) }

    currentQuestion?.let { question ->
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
                    onClick = { handleAnswerSelected(option, question, navController, questions) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(text = option.optionText)
                }
            }

            Text(
                text = "Time remaining: ${timer.value}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        LaunchedEffect(Unit) {
            startTimer(timer) {
                handleTimerExpired(navController, questions)
            }
        }
    } ?: run {
        // No more questions, end the quiz
        handleQuizCompleted(navController)
    }
}

private fun handleAnswerSelected(
    option: QuizOption,
    question: QuizQuestion,
    navController: NavController,
    questions: List<QuizQuestion>
) {
    // Handle answer selected
    // ...
    // Proceed to the next question or end the quiz when all questions are answered
    // ...
    val currentQuestionIndex = questions.indexOf(question)
    if (currentQuestionIndex < questions.size - 1) {
        // Move to the next question
        navController.navigate("quiz")
    } else {
        // Quiz completed
        navController.navigate("end")
    }
}

private fun handleTimerExpired(
    navController: NavController,
    questions: List<QuizQuestion>
) {
    // Handle timer expired
    // ...
    // Quiz completed
    navController.navigate("end")
}

private fun startTimer(
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

private fun handleQuizCompleted(navController: NavController) {
    // Handle quiz completion
    // ...
    // Navigate to the appropriate destination
    navController.navigate("end")
}
