package io.itch.fr.quizgame.feature_start.domain

import io.itch.fr.quizgame.feature_start.data.Question
import io.itch.fr.quizgame.feature_start.data.QuestionRepository


class GetQuestionsUseCase(private val questionRepository: QuestionRepository) {
    suspend operator fun invoke(): List<Question> = questionRepository.getQuestions()
}
