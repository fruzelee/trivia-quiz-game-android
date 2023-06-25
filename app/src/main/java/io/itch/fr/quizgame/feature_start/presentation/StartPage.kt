package io.itch.fr.quizgame.feature_start.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.itch.fr.quizgame.feature_start.data.QuizQuestion
import io.itch.fr.quizgame.feature_start.domain.GetQuizQuestionsUseCase

@Composable
fun StartPage(
    getQuizQuestionsUseCase: GetQuizQuestionsUseCase,
    navController: NavController
) {
    val quizQuestions = remember { mutableStateListOf<QuizQuestion>() }

    LaunchedEffect(Unit) {
        quizQuestions.addAll(getQuizQuestionsUseCase.getQuizQuestions())
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to Quiz Game!")

        Button(
            onClick = { navController.navigate("quiz") }
        ) {
            Text(text = "Start Quiz")
        }

        Button(
            onClick = { navController.navigate("history") }
        ) {
            Text(text = "View History")
        }
    }
}
