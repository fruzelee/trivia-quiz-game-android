package io.itch.fr.quizgame.screens

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import io.itch.fr.quizgame.R
import io.itch.fr.quizgame.data.QuizHistoryEntry
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun QuizPage(
    viewModel: QuizPageViewModel = hiltViewModel(),
    navController: NavController,
    onQuizFinished: (Int) -> Unit
) {
    val context = LocalContext.current
    val currentQuestion by viewModel.currentQuestion.observeAsState(null)
    val score by viewModel.score.observeAsState(0)
    val timerValue by viewModel.timer.observeAsState(0)
    val answerFeedback by viewModel.answerFeedback.observeAsState(null)
    val coroutineScope = rememberCoroutineScope()
    val quizCompleted by viewModel.quizCompleted.observeAsState(false)

    // Initialize the navController in the QuizPageViewModel
    viewModel.initNavController(navController)

    if (quizCompleted) {
        saveScoreToHistory(score, context)
        // Don't invoke onQuizFinished here, it will be invoked when navigating to the EndPage
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz Game") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (currentQuestion != null) {
                    // Show live score
                    Text(
                        text = "Score: $score",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    QuizQuestionCard(currentQuestion!!, answerFeedback) { selectedOption ->
                        coroutineScope.launch {
                            viewModel.onAnswerSelected(selectedOption)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    QuizTimer(timerValue)
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    QuizResult(score) {
                        onQuizFinished(score) // Invoke onQuizFinished when Play Again button is clicked
                    }
                }
            }
        }
    )
}

@Composable
fun QuizTimer(timerValue: Int) {
    Text(
        text = "Time Left: $timerValue seconds",
        style = MaterialTheme.typography.caption
    )
}

@Composable
fun QuizResult(score: Int, onPlayAgainClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Completed!",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Show final score
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_high_score),
                contentDescription = "Score Icon",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = { onPlayAgainClicked() }, // Invoke onPlayAgainClicked when Play Again button is clicked
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Play Again")
        }
    }
}

@Composable
fun QuizQuestionCard(
    question: QuizQuestion,
    answerFeedback: String?,
    onAnswerSelected: (QuizOption) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(question.questionText)
            question.options.forEach { option ->
                Button(
                    onClick = { onAnswerSelected(option) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(option.text)
                }
            }
            if (answerFeedback != null) {
                Text(
                    text = answerFeedback,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

private fun saveScoreToHistory(score: Int, context: Context) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPreferences.edit()
    val historyList = getHistoryListFromPreferences(context).toMutableList()
    historyList.add(QuizHistoryEntry(Date().toString(), score))
    val historyJson = Gson().toJson(historyList)
    editor.putString("history", historyJson)
    editor.apply()
}

private fun getHistoryListFromPreferences(context: Context): List<QuizHistoryEntry> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val historyJson = sharedPreferences.getString("history", null)
    return if (historyJson != null) {
        Gson().fromJson(historyJson, Array<QuizHistoryEntry>::class.java).toList()
    } else {
        emptyList()
    }
}
