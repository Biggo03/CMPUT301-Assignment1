// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the MainMenuScreen composable with buttons
// for starting a game, viewing the attempt summary, and viewing the attempt log.
// Date: 2026-09-30
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
// Prompt/subject: Updated MainMenuScreen to use the shared
// RapidRecallBackground composable.
// Date: 2026-10-01
// Conversation: [link to full ChatGPT conversation]
@Composable
fun MainMenuScreen(gameSession: GameSession) {
    RapidRecallBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RapidRecallButton(
                text = "Start Game",
                onClick = {
                    gameSession.changeGameState(GameState.IN_GAME)
                }
            )

            RapidRecallButton(
                text = "Attempt Summary",
                onClick = {
                    gameSession.changeGameState(GameState.SUMMARY)
                }
            )

            RapidRecallButton(
                text = "Attempt Log",
                onClick = {
                    gameSession.changeGameState(GameState.ATTEMPT_LOG)
                }
            )
        }
    }
}
