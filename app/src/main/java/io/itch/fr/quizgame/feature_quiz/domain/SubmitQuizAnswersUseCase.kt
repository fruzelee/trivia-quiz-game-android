package io.itch.fr.quizgame.feature_quiz.domain

import io.itch.fr.quizgame.feature_quiz.data.QuizAnswer

interface SubmitQuizAnswersUseCase {
    suspend fun submitQuizAnswers(answers: List<QuizAnswer>)
}
