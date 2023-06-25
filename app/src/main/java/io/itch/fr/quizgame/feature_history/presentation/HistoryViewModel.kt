package io.itch.fr.quizgame.feature_history.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.itch.fr.quizgame.feature_history.data.QuizHistory
import io.itch.fr.quizgame.feature_history.data.QuizHistoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val quizHistoryRepository: QuizHistoryRepository) : ViewModel() {
    private val _history = MutableLiveData<List<QuizHistory>>()
    val history: LiveData<List<QuizHistory>> get() = _history

    fun loadQuizHistory() {
        viewModelScope.launch {
            val quizHistory = quizHistoryRepository.getQuizHistory()
            _history.value = quizHistory
        }
    }
}
