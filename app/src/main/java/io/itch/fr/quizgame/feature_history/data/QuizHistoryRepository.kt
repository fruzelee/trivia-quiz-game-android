package io.itch.fr.quizgame.feature_history.data

interface QuizHistoryRepository {
    suspend fun saveQuizHistory(quizHistory: QuizHistory)
    suspend fun getQuizHistory(): List<QuizHistory>
}
