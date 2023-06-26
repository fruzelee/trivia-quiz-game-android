package io.itch.fr.quizgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizPageViewModel @Inject constructor() : ViewModel() {
    private val _currentQuestion = MutableLiveData<QuizQuestion>()
    val currentQuestion: LiveData<QuizQuestion> get() = _currentQuestion

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    private val _timer = MutableLiveData<Int>()
    val timer: LiveData<Int> get() = _timer

    private val _answerFeedback = MutableLiveData<String?>()
    val answerFeedback: LiveData<String?> get() = _answerFeedback

    private val _quizCompleted = MutableLiveData<Boolean>()
    val quizCompleted: LiveData<Boolean> get() = _quizCompleted

    private lateinit var navController: NavController


    private val predefinedQuestions = listOf(
        QuizQuestion(
            questionId = 1,
            questionText = "What is the capital of France?",
            options = listOf(
                QuizOption(1, "Paris"),
                QuizOption(2, "London"),
                QuizOption(3, "Berlin"),
                QuizOption(4, "Rome")
            ),
            correctOptionId = 1
        ),
        QuizQuestion(
            questionId = 2,
            questionText = "Who painted the Mona Lisa?",
            options = listOf(
                QuizOption(1, "Leonardo da Vinci"),
                QuizOption(2, "Pablo Picasso"),
                QuizOption(3, "Vincent van Gogh"),
                QuizOption(4, "Michelangelo")
            ),
            correctOptionId = 1
        ),
        // Add more predefined quiz questions here
    )

    private var currentQuestionIndex = 0
    private var timerJob: Job? = null

    init {
        startQuiz()
    }

    private fun startQuiz() {
        _score.value = 0
        _timer.value = QUESTION_TIME_LIMIT
        currentQuestionIndex = 0
        _currentQuestion.value = predefinedQuestions[currentQuestionIndex]
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            repeat(QUESTION_TIME_LIMIT) {
                _timer.value = QUESTION_TIME_LIMIT - it
                delay(1000)
            }
            onTimerExpired()
        }
    }

    suspend fun onAnswerSelected(option: QuizOption) {
        val currentQuestion = currentQuestion.value ?: return

        if (currentQuestionIndex >= predefinedQuestions.size) {
            val score = _score.value ?: 0
            navController.navigate("end/$score")
        } else {
            // ... existing code ...
            if (option.optionId == currentQuestion.correctOptionId) {
                _score.value = (_score.value ?: 0) + 1
                _answerFeedback.value = "Correct!"
            } else {
                _score.value = (_score.value ?: 0) - 1
                _answerFeedback.value = "Incorrect!"
            }
            delay(DELAY_BETWEEN_QUESTIONS)
            showNextQuestion()
        }

    }

    fun initNavController(navController: NavController) {
        this.navController = navController
    }

    private suspend fun onTimerExpired() {
        _score.value = (_score.value ?: 0) - 1
        _answerFeedback.value = "Time's up! Incorrect!"
        delay(DELAY_BETWEEN_QUESTIONS)
        showNextQuestion()
    }

    private fun showNextQuestion() {
        _answerFeedback.value = null
        currentQuestionIndex++
        if (currentQuestionIndex < predefinedQuestions.size) {
            _currentQuestion.value = predefinedQuestions[currentQuestionIndex]
            startTimer()
        } else {
            // Quiz completed
            _quizCompleted.value = true
        }
    }

    fun restartQuiz() {
        startQuiz()
    }

    companion object {
        private const val QUESTION_TIME_LIMIT = 10 // in seconds
        private const val DELAY_BETWEEN_QUESTIONS = 2000L // in milliseconds
    }
}
