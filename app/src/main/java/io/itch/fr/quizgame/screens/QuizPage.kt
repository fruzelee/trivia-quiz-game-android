package io.itch.fr.quizgame.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizPage(
    navController: NavController,
    viewModel: QuizPageViewModel = viewModel()
) {
    val currentQuestion by viewModel.currentQuestion.observeAsState()
    val timer by viewModel.timer.observeAsState()

    QuizContent(
        currentQuestion = currentQuestion,
        timer = timer,
        onAnswerSelected = viewModel::onAnswerSelected,
        onTimerExpired = viewModel::onTimerExpired,
        onQuizCompleted = { navController.navigate("end") }
    )
}

@Composable
private fun QuizContent(
    currentQuestion: QuizQuestion?,
    timer: Int?,
    onAnswerSelected: (QuizOption) -> Unit,
    onTimerExpired: () -> Unit,
    onQuizCompleted: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentQuestion?.let { question ->
            Text(
                text = question.questionText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            question.options.forEach { option ->
                Button(
                    onClick = { onAnswerSelected(option) },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = option.optionText)
                }
            }

            timer?.let { remainingTime ->
                Text(
                    text = "Time remaining: $remainingTime",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        } ?: run {
            // No more questions, end the quiz
            onQuizCompleted()
        }
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
