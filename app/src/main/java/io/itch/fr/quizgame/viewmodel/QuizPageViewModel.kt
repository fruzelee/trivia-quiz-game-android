package io.itch.fr.quizgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.utils.DispatcherProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizPageViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
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

    // for test cases
    private val _quizQuestions = MutableLiveData<List<QuizQuestion>>()
    val quizQuestions: LiveData<List<QuizQuestion>> = _quizQuestions

    fun fetchQuizQuestions() {
        viewModelScope.launch(dispatcherProvider.io) {
            val questions = quizRepository.getQuizQuestions()
            _quizQuestions.postValue(questions)
        }
    }

    private val predefinedQuestions = listOf(
        QuizQuestion(
            questionId = 1,
            questionText = "What is the capital of Togo?",
            options = listOf(
                QuizOption(1, "Bogota"),
                QuizOption(2, "Berlin"),
                QuizOption(3, "Lome"),
                QuizOption(4, "Rome")
            ),
            correctOptionId = 3
        ),
        QuizQuestion(
            questionId = 2,
            questionText = "Who is the father of muslim nation?",
            options = listOf(
                QuizOption(1, "Ibrahim(as)"),
                QuizOption(2, "Ishmael(as)"),
                QuizOption(3, "Isa(as)"),
                QuizOption(4, "Adam(as)")
            ),
            correctOptionId = 1
        ),
        QuizQuestion(
            questionId = 3,
            questionText = "Who is the first man in Muslim religion?",
            options = listOf(
                QuizOption(1, "Ibrahim(as)"),
                QuizOption(2, "Ishmael(as)"),
                QuizOption(3, "Isa(as)"),
                QuizOption(4, "Adam(as)")
            ),
            correctOptionId = 4
        ),
        QuizQuestion(
            questionId = 4,
            questionText = "Which is the final religion?",
            options = listOf(
                QuizOption(1, "Islam"),
                QuizOption(2, "Christianity"),
                QuizOption(3, "Buddhism"),
                QuizOption(4, "Judaism")
            ),
            correctOptionId = 1
        ),
        QuizQuestion(
            questionId = 5,
            questionText = "How many years will Islam last?",
            options = listOf(
                QuizOption(1, "2000"),
                QuizOption(2, "1000"),
                QuizOption(3, "1500"),
                QuizOption(4, "1900")
            ),
            correctOptionId = 3
        ),
        QuizQuestion(
            questionId = 6,
            questionText = "Who is the last imam of the world?",
            options = listOf(
                QuizOption(1, "Imam Abu Hanifa"),
                QuizOption(2, "Imam Malik"),
                QuizOption(3, "Imam Shafi"),
                QuizOption(4, "Imam Al Mahdi")
            ),
            correctOptionId = 4
        ),
        QuizQuestion(
            questionId = 7,
            questionText = "What is the significance of fasting on the 9th day of Dhul Hijjah, the day of Arafah?",
            options = listOf(
                QuizOption(1, "It is a highly recommended act of worship"),
                QuizOption(2, "It is a day of forgiveness where 2 years' worth of sins are forgiven"),
                QuizOption(3, "It was practiced by Prophet Muhammad (saw) and encouraged by him"),
                QuizOption(4, "All of the above")
            ),
            correctOptionId = 4
        )
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
