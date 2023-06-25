package io.itch.fr.quizgame.feature_quiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.itch.fr.quizgame.feature_quiz.data.entities.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<Question>

    @Insert
    suspend fun insertQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)
}
