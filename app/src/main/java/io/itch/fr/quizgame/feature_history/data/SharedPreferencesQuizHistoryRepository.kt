package io.itch.fr.quizgame.feature_history.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SharedPreferencesQuizHistoryRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences) :
    QuizHistoryRepository {

    private val historyKey = "quiz_history"

    override suspend fun getQuizHistory(): List<QuizHistory> {
        val historyJson = sharedPreferences.getString(historyKey, null)
        return if (historyJson != null) {
            val type = object : TypeToken<List<QuizHistory>>() {}.type
            Gson().fromJson(historyJson, type)
        } else {
            emptyList()
        }
    }

    override suspend fun saveQuizHistory(quizHistory: QuizHistory) {
        val historyList = getQuizHistory().toMutableList()
        historyList.add(quizHistory)

        val historyJson = Gson().toJson(historyList)
        sharedPreferences.edit {
            putString(historyKey, historyJson)
        }
    }
}
