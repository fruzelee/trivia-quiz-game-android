package io.itch.fr.quizgame.feature_history.domain

import io.itch.fr.quizgame.feature_history.data.QuizHistoryEntry

interface GetQuizHistoryUseCase {
    fun getQuizHistory(): List<QuizHistoryEntry>
}