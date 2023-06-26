package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.itch.fr.quizgame.ui.theme.Jost

@Composable
fun StartPage(
    navController: NavController,
    onStartQuizClicked: () -> Unit,
    onViewHistoryClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStartQuizClicked,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Start Quiz",
                    fontFamily = Jost
                )
            }

            Button(
                onClick = onViewHistoryClicked
            ) {
                Text(
                    text = "View History",
                    fontFamily = Jost
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Made with ❤️ by Fazle Rabbi",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Jost
            )

            Text(
                text = "fazlerabbicse@gmail.com",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Jost
            )
        }
    }
}
