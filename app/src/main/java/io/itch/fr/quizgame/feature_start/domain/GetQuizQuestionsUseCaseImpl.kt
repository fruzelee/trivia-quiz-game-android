package io.itch.fr.quizgame.feature_start.domain

import io.itch.fr.quizgame.feature_start.data.QuizQuestion
import io.itch.fr.quizgame.feature_start.data.QuizRepository

class GetQuizQuestionsUseCaseImpl(private val repository: QuizRepository) :
    GetQuizQuestionsUseCase {
    override suspend fun getQuizQuestions(): List<QuizQuestion> {
        return repository.getQuizQuestions()
    }
}