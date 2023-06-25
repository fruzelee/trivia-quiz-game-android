package io.itch.fr.quizgame.repository

import io.itch.fr.quizgame.data.QuizHistoryEntry
import io.itch.fr.quizgame.data.QuizQuestion


interface QuizRepository {
    fun getQuizQuestions(): List<QuizQuestion>
    fun saveQuizHistory(entry: QuizHistoryEntry)
    fun getQuizHistory(): List<QuizHistoryEntry>
}
