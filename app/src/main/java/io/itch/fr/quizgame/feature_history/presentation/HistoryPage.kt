package io.itch.fr.quizgame.feature_history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.itch.fr.quizgame.feature_history.domain.GetQuizHistoryUseCase

@Composable
fun HistoryPage(getQuizHistoryUseCase: GetQuizHistoryUseCase) {
    val quizHistory = getQuizHistoryUseCase.getQuizHistory()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Quiz History")

        quizHistory.forEach { entry ->
            Text(text = "Date: ${entry.date}, Score: ${entry.score}")
        }
    }
}
