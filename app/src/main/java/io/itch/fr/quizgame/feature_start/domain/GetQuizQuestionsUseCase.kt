package io.itch.fr.quizgame.feature_start.domain

import io.itch.fr.quizgame.feature_start.data.QuizQuestion

interface GetQuizQuestionsUseCase {
    suspend fun getQuizQuestions(): List<QuizQuestion>
}


