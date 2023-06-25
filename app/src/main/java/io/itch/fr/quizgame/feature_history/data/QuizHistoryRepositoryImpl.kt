package io.itch.fr.quizgame.feature_history.data

class QuizHistoryRepositoryImpl : QuizHistoryRepository {
    override fun getQuizHistory(): List<QuizHistoryEntry> {
        // Retrieve quiz history from local storage (e.g., SharedPreferences)
        // Return a static list of quiz history entries for now
        return listOf(
            QuizHistoryEntry(date = "2023-06-25", score = 7),
            QuizHistoryEntry(date = "2023-06-23", score = 5),
            QuizHistoryEntry(date = "2023-06-20", score = 8)
        )
    }
}