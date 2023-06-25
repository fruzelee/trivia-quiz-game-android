package io.itch.fr.quizgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.screens.EndPage
import io.itch.fr.quizgame.screens.HistoryPage
import io.itch.fr.quizgame.screens.QuizPage
import io.itch.fr.quizgame.screens.StartPage

@Composable
fun QuizAppNavigation(navController: NavHostController, questions: List<QuizQuestion>) {
    NavHost(navController = navController, startDestination = "start") {
        addStartPage(navController)
        addQuizPage(navController, questions)
        addEndPage(navController)
        addHistoryPage(navController)
    }
}

private fun NavGraphBuilder.addStartPage(navController: NavController) {
    composable("start") {
        StartPage(
            navController = navController,
            onStartQuizClicked = { navController.navigate("quiz") },
            onViewHistoryClicked = { navController.navigate("history") }
        )
    }
}

private fun NavGraphBuilder.addQuizPage(
    navController: NavController,
    questions: List<QuizQuestion>
) {
    composable("quiz") {
        QuizPage(
            navController = navController,
            questions = questions
        )
    }
}

private fun NavGraphBuilder.addEndPage(navController: NavController) {
    composable("end") {
        EndPage(
            navController = navController,
            score = 0, // Add the score parameter here or pass the actual score value
            onPlayAgainClicked = { navController.navigate("quiz") },
            onViewHistoryClicked = { navController.navigate("history") }
        )
    }
}


private fun NavGraphBuilder.addHistoryPage(navController: NavController) {
    composable("history") {
        HistoryPage(
            navController = navController,
            history = emptyList() // Pass the actual history list here
        )
    }
}
