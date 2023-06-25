package io.itch.fr.quizgame.feature_quiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.itch.fr.quizgame.feature_quiz.data.QuizAnswer
import io.itch.fr.quizgame.feature_quiz.domain.SubmitQuizAnswersUseCase
import io.itch.fr.quizgame.feature_start.data.QuizQuestion
import kotlinx.coroutines.launch

@Composable
fun QuizPage(
    submitQuizAnswersUseCase: SubmitQuizAnswersUseCase,
    navController: NavController,
    quizQuestions: List<QuizQuestion>
) {
    val selectedOptions = remember { mutableStateListOf<String>() }
    val currentIndex = remember { mutableStateOf(0) }

    val currentQuestion = quizQuestions[currentIndex.value]

    val coroutineScope = rememberCoroutineScope()

    fun submitAnswer() {
        val selectedOption = selectedOptions.getOrNull(currentIndex.value)
        val isCorrect = selectedOption == currentQuestion.options[currentQuestion.correctAnswer]

        val quizAnswer = QuizAnswer(
            question = currentQuestion.question,
            selectedOption = selectedOption.orEmpty(),
            isCorrect = isCorrect
        )

        coroutineScope.launch {
            submitQuizAnswersUseCase.submitQuizAnswers(listOf(quizAnswer))
        }

        selectedOptions.add(selectedOption.orEmpty())

        if (currentIndex.value < quizQuestions.size - 1) {
            currentIndex.value++
        } else {
            navController.navigate("end")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = currentQuestion.question)

        currentQuestion.options.forEachIndexed { index, option ->
            Button(
                onClick = { selectedOptions[currentIndex.value] = option }
            ) {
                Text(text = option)
            }
        }

        Button(
            onClick = { submitAnswer() }
        ) {
            Text(text = "Submit Answer")
        }
    }
}
