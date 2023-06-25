package io.itch.fr.quizgame.feature_history.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HistoryPage(viewModel: HistoryPageViewModel = hiltViewModel()) {
    val history: List<QuizHistoryItem> by viewModel.history.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadQuizHistory()
    }

    LazyColumn {
        items(history) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = item.score.toString(),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
