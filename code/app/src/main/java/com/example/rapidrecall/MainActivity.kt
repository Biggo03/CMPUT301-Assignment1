// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Replaced the default Android Compose template with
// top-level RapidRecall application scaffolding that selects screens
// based on the current GameSession state.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rapidrecall.ui.theme.RapidRecallTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RapidRecallTheme {
                val gameSession = remember {
                    GameSession()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    RapidRecallApp(
                        gameSession = gameSession,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the top-level RapidRecall application state
// scaffolding and updated it to create one remembered Round for each game.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]
@Composable
fun RapidRecallApp(
    gameSession: GameSession,
    modifier: Modifier = Modifier
) {
    when (gameSession.gameState) {

        GameState.MAIN_MENU -> {
            MainMenuScreen(gameSession)
        }

        GameState.IN_GAME -> {
            val round = remember {
                Round()
            }

            GameScreen(
                round = round,
                gameSession = gameSession
            )
        }

        GameState.ATTEMPT_LOG -> {
            AttemptLogScreen(gameSession)
        }

        GameState.SUMMARY -> {
            SummaryScreen(gameSession)
        }
    }
}