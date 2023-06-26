package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.itch.fr.quizgame.data.QuizHistoryEntry
import io.itch.fr.quizgame.ui.theme.Jost

@Composable
fun HistoryPage(
    navController: NavController,
    historyList: List<QuizHistoryEntry>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Quiz History",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp),
            fontFamily = Jost
        )

        LazyColumn {
            items(historyList) { entry ->
                Text(
                    text = "Date: ${entry.quizDate}, Score: ${entry.score}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontFamily = Jost
                )
            }
        }
    }
}
