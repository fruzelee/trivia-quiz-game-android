package io.itch.fr.quizgame.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class QuizPageViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var quizRepository: QuizRepository

    private lateinit var viewModel: QuizPageViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testCoroutineScope = TestCoroutineScope()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = QuizPageViewModel(quizRepository)
    }

    @After
    fun tearDown() {
        testCoroutineScope.cleanupTestCoroutines()
        // Reset main dispatcher after the test
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchQuizQuestions`() = testCoroutineScope.runBlockingTest {
        val questions = listOf(
            QuizQuestion(
                questionId = 1,
                questionText = "What is the capital of France?",
                options = listOf(
                    QuizOption(1, "Paris"),
                    QuizOption(2, "London"),
                    QuizOption(3, "Berlin"),
                    QuizOption(4, "Madrid")
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
                    QuizOption(4, "Claude Monet")
                ),
                correctOptionId = 1
            )
        )

        `when`(quizRepository.getQuizQuestions()).thenReturn(questions)

        val observer = Observer<List<QuizQuestion>> {}
        viewModel.quizQuestions.observeForever(observer)

        viewModel.fetchQuizQuestions()

        assert(viewModel.quizQuestions.value == questions)
    }
}
