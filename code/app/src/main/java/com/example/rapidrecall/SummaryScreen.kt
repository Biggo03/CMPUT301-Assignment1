// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the attempt summary screen displaying total attempts,
// successful attempts, overall accuracy, and a button to return to the main menu.
// Date: 2026-10-01
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated SummaryScreen to use the shared
// RapidRecallBackground composable.
// Date: 2026-10-01
// Conversation: [link to full ChatGPT conversation]
@Composable
fun SummaryScreen(
    gameSession: GameSession,
    modifier: Modifier = Modifier
) {
    RapidRecallBackground(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Attempt Summary")

            Text("Total Attempts: ${gameSession.totalAttempts}")
            Text("Correct Attempts: ${gameSession.successfulAttempts}")
            Text("Accuracy: ${gameSession.successRate}%")

            RapidRecallButton(
                text = "Return to Menu",
                onClick = {
                    gameSession.changeGameState(GameState.MAIN_MENU)
                }
            )
        }
    }
}
