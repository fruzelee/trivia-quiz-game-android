package io.itch.fr.quizgame.feature_start.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartPageViewModel @Inject constructor(
    private val navController: NavController
) : ViewModel() {

    fun startQuiz() {
        navController.navigate("quiz")
    }

    fun viewHistory() {
        navController.navigate("history")
    }
}
