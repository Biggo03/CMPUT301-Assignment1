// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated a reusable Compose button that changes
// container color while being pressed.
// Date: 2026-10-01
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

package com.example.rapidrecall

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun RapidRecallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPressed) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    ) {
        Text(text)
    }
}