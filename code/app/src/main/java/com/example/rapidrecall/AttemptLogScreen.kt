// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the AttemptLogScreen using a LazyColumn to
// display sequence length, target sequence, user input, success, and timestamp.
// Date: 2026-10-01
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AttemptLogScreen(
    gameSession: GameSession,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Attempt Log")

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(gameSession.attempts) { index, attempt ->
                AttemptRow(
                    attempt = attempt,
                    attemptNumber = index + 1
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        Button(
            onClick = {
                gameSession.changeGameState(GameState.MAIN_MENU)
            }
        ) {
            Text("Return to Menu")
        }
    }
}

@Composable
fun AttemptRow(
    attempt: Attempt,
    attemptNumber: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Attempt $attemptNumber")
        Text("Length: ${attempt.sequence.sequenceLength}")
        Text("Target: ${attempt.sequence.sequence}")
        Text("Input: ${attempt.userAttempt ?: "N/A"}")
        Text(
            "Success: ${
                when (attempt.success) {
                    true -> "Yes"
                    false -> "No"
                    null -> "N/A"
                }
            }"
        )
        Text("Timestamp: ${attempt.timestamp?.let { formatTimestamp(it) } ?: "N/A"}")
    }
}

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Added formatting for Attempt timestamps using
// YYYY-MM-DDTHH:MM:SS-style date/time display.
// Date: 2026-10-01
// Conversation: [link to full ChatGPT conversation]
fun formatTimestamp(timestamp: Long): String {
    val formatter = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss",
        Locale.getDefault()
    )

    return formatter.format(Date(timestamp))
}