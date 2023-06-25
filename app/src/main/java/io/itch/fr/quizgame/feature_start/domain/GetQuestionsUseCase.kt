package io.itch.fr.quizgame.feature_start.domain

import io.itch.fr.quizgame.feature_start.data.QuestionRepository


class GetQuestionsUseCase(private val questionRepository: QuestionRepository) {
    suspend operator fun invoke(): List<io.itch.fr.quizgame.feature_quiz.data.entities.Question> = questionRepository.getQuestions()
}
