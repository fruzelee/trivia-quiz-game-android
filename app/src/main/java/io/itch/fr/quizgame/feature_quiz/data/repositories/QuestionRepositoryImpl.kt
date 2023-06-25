package io.itch.fr.quizgame.feature_quiz.data.repositories

import io.itch.fr.quizgame.feature_quiz.data.dao.QuestionDao
import io.itch.fr.quizgame.feature_quiz.data.entities.Question

class QuestionRepositoryImpl(private val questionDao: QuestionDao) : QuestionRepository {
    override suspend fun getQuestions(): List<Question> {
        return questionDao.getAllQuestions()
    }

    override suspend fun addQuestion(question: Question) {
        questionDao.insertQuestion(question)
    }
}