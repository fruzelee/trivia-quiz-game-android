package io.itch.fr.quizgame.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.itch.fr.quizgame.R
import io.itch.fr.quizgame.data.QuizOption
import io.itch.fr.quizgame.data.QuizQuestion
import io.itch.fr.quizgame.viewmodel.QuizPageViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuizPage(viewModel: QuizPageViewModel = hiltViewModel(), navController: NavController) {
    val currentQuestion by viewModel.currentQuestion.observeAsState(null)
    val score by viewModel.score.observeAsState(0)
    val timerValue by viewModel.timer.observeAsState(0)
    val answerFeedback by viewModel.answerFeedback.observeAsState(null)
    val coroutineScope = rememberCoroutineScope()
    val quizCompleted by viewModel.quizCompleted.observeAsState(false)

    if (quizCompleted) {
        navController.navigate("end")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz Game") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (currentQuestion != null) {
                    QuizQuestionCard(currentQuestion!!, answerFeedback) { selectedOption ->
                        coroutineScope.launch {
                            viewModel.onAnswerSelected(selectedOption)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Timer(timerValue)
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    QuizResult(score)
                }
            }
        }
    )
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

@Composable
fun Timer(timerValue: Int) {
    Text(
        text = "Time Left: $timerValue seconds",
        style = MaterialTheme.typography.caption
    )
}

@Composable
fun QuizResult(score: Int) {
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Score Icon",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
