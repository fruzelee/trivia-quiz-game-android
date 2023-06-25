package io.itch.fr.quizgame.data

data class QuizQuestion(
    val questionId: Int,
    val questionText: String,
    val options: List<QuizOption>,
    val correctOptionId: Int
)