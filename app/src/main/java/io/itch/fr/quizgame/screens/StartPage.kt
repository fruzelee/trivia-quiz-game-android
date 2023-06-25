package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StartPage(
    navController: NavController,
    onStartQuizClicked: () -> Unit,
    onViewHistoryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onStartQuizClicked,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Start Quiz")
        }

        Button(
            onClick = onViewHistoryClicked
        ) {
            Text(text = "View History")
        }
    }
}
