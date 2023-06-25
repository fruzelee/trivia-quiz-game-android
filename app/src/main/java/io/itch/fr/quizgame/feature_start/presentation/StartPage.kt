package io.itch.fr.quizgame.feature_start.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun StartPage(
    viewModel: StartPageViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Trivia Quiz")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.startQuiz() },
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
