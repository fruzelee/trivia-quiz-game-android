package io.itch.fr.quizgame.feature_history.data

interface QuizHistoryRepository {
    fun getQuizHistory(): List<QuizHistoryEntry>
}
