package io.itch.fr.quizgame.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.itch.fr.quizgame.data.QuizHistoryEntry
import io.itch.fr.quizgame.data.QuizQuestion

class QuizRepositoryImpl(private val context: Context) : QuizRepository {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("QuizApp", Context.MODE_PRIVATE)
    }

    override fun getQuizQuestions(): List<QuizQuestion> {
        // Here, you can fetch the quiz questions from SharedPreferences or any other data source
        // and return a list of QuizQuestion objects.
        // Example:
        val questionsJson = sharedPreferences.getString("quiz_questions", null)
        return questionsJson?.let {
            val type = object : TypeToken<List<QuizQuestion>>() {}.type
            Gson().fromJson(it, type)
        } ?: emptyList()
    }

    override fun saveQuizHistory(entry: QuizHistoryEntry) {
        val history = getQuizHistory().toMutableList()
        history.add(entry)
        val historyJson = Gson().toJson(history)
        sharedPreferences.edit {
            putString("quiz_history", historyJson)
        }
    }

    override fun getQuizHistory(): List<QuizHistoryEntry> {
        val historyJson = sharedPreferences.getString("quiz_history", null)
        return historyJson?.let {
            val type = object : TypeToken<List<QuizHistoryEntry>>() {}.type
            Gson().fromJson(it, type)
        } ?: emptyList()
    }
}
