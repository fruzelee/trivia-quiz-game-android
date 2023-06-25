package io.itch.fr.quizgame.feature_start.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StartPage(
    viewModel: StartPageViewModel = hiltViewModel(),
    onNavigateToQuizPage: () -> Unit
) {
    Column {
        Text(text = "Trivia Quiz")
        Button(
            onClick = {
                viewModel.startQuiz()
                onNavigateToQuizPage()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Start Quiz")
        }
        Button(
            onClick = { viewModel.viewHistory() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "View History")
        }
    }
}
