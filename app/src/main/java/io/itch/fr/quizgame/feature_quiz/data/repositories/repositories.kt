package io.itch.fr.quizgame.feature_quiz.data.repositories

import io.itch.fr.quizgame.feature_quiz.data.entities.Question

interface QuestionRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun addQuestion(question: Question)
}