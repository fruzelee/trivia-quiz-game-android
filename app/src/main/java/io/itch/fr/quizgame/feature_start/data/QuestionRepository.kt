package io.itch.fr.quizgame.feature_start.data

interface QuestionRepository {
    suspend fun getQuestions(): List<Question>
}

data class Question(val id: Int, val question: String, val options: List<String>, val correctAnswer: Int)
