package io.itch.fr.quizgame.feature_start.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)
