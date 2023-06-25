package io.itch.fr.quizgame.feature_quiz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    var userAnswerIndex: Int? = null
)