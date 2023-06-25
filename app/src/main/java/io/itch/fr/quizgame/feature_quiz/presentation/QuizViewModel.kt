package io.itch.fr.quizgame.feature_quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.itch.fr.quizgame.feature_quiz.data.entities.Question
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository
import io.itch.fr.quizgame.feature_start.domain.GetQuestionsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex: Int = 0
    private var userScore: Int = 0

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion: StateFlow<Question?> = _currentQuestion.asStateFlow()

    private val _score = MutableStateFlow(userScore)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _timerValue = MutableStateFlow(DEFAULT_TIMER_VALUE)
    val timerValue: StateFlow<Int> = _timerValue.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            questions = getQuestionsUseCase()
            setCurrentQuestion()
        }
    }

    private fun setCurrentQuestion() {
        _currentQuestion.value = if (currentQuestionIndex < questions.size) {
            questions[currentQuestionIndex]
        } else {
            null
        }
    }

    fun submitAnswer(selectedAnswerIndex: Int) {
        val currentQuestion = _currentQuestion.value
        if (currentQuestion != null && currentQuestion.userAnswerIndex == null) {
            currentQuestion.userAnswerIndex = selectedAnswerIndex
            if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {
                userScore++
            } else {
                userScore = maxOf(0, userScore - 1)
            }
            _score.value = userScore

            viewModelScope.launch {
                questionRepository.updateQuestion(currentQuestion)
            }

            currentQuestionIndex++
            setCurrentQuestion()
        }
    }

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var time = DEFAULT_TIMER_VALUE
            while (time > 0) {
                delay(1000)
                time--
                _timerValue.value = time
            }
        }
    }

    fun cancelTimer() {
        timerJob?.cancel()
        _timerValue.value = 0
    }

    companion object {
        private const val DEFAULT_TIMER_VALUE = 10
    }
}
