package io.itch.fr.quizgame.feature_quiz.data

data class QuizAnswer(
    val question: String,
    val selectedOption: String,
    val isCorrect: Boolean
)

interface QuizRepository {
    suspend fun submitQuizAnswers(answers: List<QuizAnswer>)
}
