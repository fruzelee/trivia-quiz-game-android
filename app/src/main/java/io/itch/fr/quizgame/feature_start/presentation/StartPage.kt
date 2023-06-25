package io.itch.fr.quizgame.feature_start.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.itch.fr.quizgame.feature_end.presentation.EndPage
import io.itch.fr.quizgame.feature_history.presentation.HistoryPage
import io.itch.fr.quizgame.feature_quiz.presentation.QuizPage
import io.itch.fr.quizgame.feature_quiz.presentation.QuizViewModel


@Composable
fun StartPage(viewModel: StartPageViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val startQuizUseCase: StartQuizUseCase by viewModel.startQuizUseCase.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startQuiz()
    }

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Trivia Quiz")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("quiz") },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Start Quiz")
                }
                Button(
                    onClick = { navController.navigate("history") },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "View History")
                }
            }
        }
        composable("quiz") {
            QuizPage(
                onQuizFinish = { navController.navigate("end") },
                viewModel = QuizViewModel(startQuizUseCase)
            )
        }
        composable("end") {
            EndPage()
        }
        composable("history") {
            HistoryPage()
        }
    }
}
