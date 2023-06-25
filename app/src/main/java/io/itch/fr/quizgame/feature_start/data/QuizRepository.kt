package io.itch.fr.quizgame.feature_start.data

interface QuizRepository {
    suspend fun getQuizQuestions(): List<QuizQuestion>
}