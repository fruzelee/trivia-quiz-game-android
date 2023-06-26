package io.itch.fr.quizgame.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.utils.TestDispatcherProvider
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
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

    private val testDispatcherProvider = TestDispatcherProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = QuizPageViewModel(quizRepository, testDispatcherProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        testCoroutineScope.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test fetchQuizQuestions`() = testCoroutineScope.runBlockingTest {
        val questions = listOf(
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
        )

        `when`(quizRepository.getQuizQuestions()).thenReturn(questions)

        val observer = Observer<List<QuizQuestion>> {}
        viewModel.quizQuestions.observeForever(observer)

        viewModel.fetchQuizQuestions()

        assert(viewModel.quizQuestions.value == questions)
    }
}
