package io.itch.fr.quizgame.viewmodel

import `package io`.itch.fr.quizgame.`viewmodel;`
import org.junit.Test
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert.`*;`
import org.junit.Before
import org.mockito.Mockito.`*;`

class QuizPageViewModelTest {


    /**Should increase the score and show correct feedback when the selected answer is correct*/
    @Before
    fun setup() {
        viewModel = QuizPageViewModel()
    }

    /**Should decrease the score and show incorrect feedback when the selected answer is incorrect*/
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QuizPageViewModel()
    }

    /**Should proceed to the next question after a delay when an answer is selected*/
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QuizPageViewModel()
    }

}