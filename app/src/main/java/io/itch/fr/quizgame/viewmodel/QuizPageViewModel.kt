package io.itch.fr.quizgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.repository.QuizRepository
import javax.inject.Inject

@HiltViewModel
class QuizPageViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _currentQuestion = MutableLiveData<QuizQuestion>()
    val currentQuestion: LiveData<QuizQuestion> get() = _currentQuestion

    private val _timer = MutableLiveData<Int>()
    val timer: LiveData<Int> get() = _timer

    val quizQuestions: MutableLiveData<List<QuizQuestion>> = MutableLiveData()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        val questions = quizRepository.getQuizQuestions()
        // Initialize with the first question
        _currentQuestion.value = questions.firstOrNull()
    }

    fun onAnswerSelected(option: QuizOption) {
        // Handle answer selected
        // ...
        // Proceed to the next question or end the quiz when all questions are answered
        val currentQuestion = _currentQuestion.value
        val questions = quizRepository.getQuizQuestions()

        currentQuestion?.let {
            val currentQuestionIndex = questions.indexOf(it)
            if (currentQuestionIndex < questions.size - 1) {
                // Move to the next question
                _currentQuestion.value = questions[currentQuestionIndex + 1]
            } else {
                // Quiz completed
                // Handle quiz completion
                // ...
                // Notify the UI or navigate to the appropriate destination
            }
        }
    }

    fun onTimerExpired() {
        // Handle timer expired
        // ...
        // Quiz completed
        // Handle quiz completion
        // ...
        // Notify the UI or navigate to the appropriate destination

        val currentQuestion = _currentQuestion.value
        val questions = quizRepository.getQuizQuestions()

        currentQuestion?.let {
            val currentQuestionIndex = questions.indexOf(it)
            if (currentQuestionIndex < questions.size - 1) {
                // Move to the next question
                _currentQuestion.value = questions[currentQuestionIndex + 1]
            } else {
                // Quiz completed
                // Handle quiz completion
                // ...
                // Notify the UI or navigate to the appropriate destination
            }
        }
    }

    fun fetchQuizQuestions() {
        val questions = quizRepository.getQuizQuestions()
        quizQuestions.value = questions
    }

}
