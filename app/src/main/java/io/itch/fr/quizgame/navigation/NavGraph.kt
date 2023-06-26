package io.itch.fr.quizgame.navigation

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.itch.fr.quizgame.data.QuizHistoryEntry
import io.itch.fr.quizgame.screens.EndPage
import io.itch.fr.quizgame.screens.HistoryPage
import io.itch.fr.quizgame.screens.QuizPage
import io.itch.fr.quizgame.screens.StartPage

@Composable
fun QuizAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        addStartPage(navController)
        addQuizPage(navController)
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

private fun NavGraphBuilder.addQuizPage(navController: NavController) {
    composable("quiz") {
        QuizPage(
            viewModel = hiltViewModel(),
            navController = navController,
            onQuizFinished = { score: Int ->
                saveScoreToPreferences(score, navController.context)
                navController.navigate("end/$score") // Pass the score as part of the navigation destination
            }
        )
    }
}


private fun NavGraphBuilder.addEndPage(navController: NavController) {
    composable("end/{score}") { backStackEntry ->
        val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
        EndPage(
            navController = navController,
            score = score, // Pass the score to the EndPage
            restartQuiz = { navController.navigate("quiz") },
            onViewHistoryClicked = { navController.navigate("history") }
        )
    }
}


private fun NavGraphBuilder.addHistoryPage(navController: NavController) {
    composable("history") {
        val historyList = getHistoryListFromPreferences(navController.context)
        HistoryPage(
            navController = navController,
            historyList = historyList
        )
    }
}

private fun getHistoryListFromPreferences(context: Context): List<QuizHistoryEntry> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val historyJson = sharedPreferences.getString("history", null)
    return if (historyJson != null) {
        val type = object : TypeToken<List<QuizHistoryEntry>>() {}.type
        Gson().fromJson(historyJson, type)
    } else {
        emptyList()
    }
}

private fun saveScoreToPreferences(score: Int, context: Context) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPreferences.edit()
    editor.putInt("score", score)
    editor.apply()
}

