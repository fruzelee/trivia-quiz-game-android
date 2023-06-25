package io.itch.fr.quizgame.feature_history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.itch.fr.quizgame.feature_history.data.QuizHistoryEntry
import io.itch.fr.quizgame.feature_history.domain.GetQuizHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryPageViewModel @Inject constructor(
    private val getQuizHistoryUseCase: GetQuizHistoryUseCase
) : ViewModel() {

    private val _quizHistory = MutableStateFlow<List<QuizHistoryEntry>>(emptyList())
    val quizHistory: StateFlow<List<QuizHistoryEntry>> = _quizHistory

    init {
        loadQuizHistory()
    }

    private fun loadQuizHistory() {
        viewModelScope.launch {
            // Fetch quiz history from the repository and update UI
        }
    }
}
