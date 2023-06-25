package io.itch.fr.quizgame.feature_end.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import io.itch.fr.quizgame.feature_quiz.presentation.QuizViewModel

@Composable
fun EndPage(viewModel: EndPageViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val score: Int by viewModel.score.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.saveQuizHistory(score)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz Finished!")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Score: $score")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Back to Start")
        }
    }
}
