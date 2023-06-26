package io.itch.fr.quizgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun EndPage(
    navController: NavController,
    score: Int,
    playAgain: () -> Unit,
    onViewHistoryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Score: $score",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp),
            fontFamily = Jost
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = playAgain,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Play Again",
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

        Text(
            text = "Made with ❤️ by Fazle Rabbi",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 16.dp),
            fontFamily = Jost
        )
        Text(
            text = "fazlerabbicse@gmail.com",
            style = MaterialTheme.typography.caption,
            fontFamily = Jost
        )
    }
}
