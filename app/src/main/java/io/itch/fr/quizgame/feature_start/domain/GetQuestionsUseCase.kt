package io.itch.fr.quizgame.feature_start.domain

import io.itch.fr.quizgame.feature_quiz.data.entities.Question
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository

class GetQuestionsUseCase(private val questionRepository: QuestionRepository) {
    suspend operator fun invoke(): List<Question> {
        return questionRepository.getQuestions()
    }
}