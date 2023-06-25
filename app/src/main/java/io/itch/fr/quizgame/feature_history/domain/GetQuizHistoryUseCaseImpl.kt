package io.itch.fr.quizgame.feature_history.domain

import io.itch.fr.quizgame.feature_history.data.QuizHistoryEntry
import io.itch.fr.quizgame.feature_history.data.QuizHistoryRepository

class GetQuizHistoryUseCaseImpl(private val repository: QuizHistoryRepository) :
    GetQuizHistoryUseCase {
    override fun getQuizHistory(): List<QuizHistoryEntry> {
        return repository.getQuizHistory()
    }
}