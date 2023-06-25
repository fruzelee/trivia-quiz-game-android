package io.itch.fr.quizgame.feature_start.presentation

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.itch.fr.quizgame.feature_start.domain.GetQuestionsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartPageViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {
    fun startQuiz() {
        viewModelScope.launch {
            // Fetch questions using the use case
            val questions = getQuestionsUseCase()
            // Start the quiz with the fetched questions
            // (navigate to the Quiz Page with the questions)
        }
    }

    fun viewHistory() {
        // View history logic
        // (navigate to the History Page)
    }
}
