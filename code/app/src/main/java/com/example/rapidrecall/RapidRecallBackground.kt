// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated a reusable background composable using a subtle
// Material theme gradient for consistent RapidRecall screen styling.
// Date: 2026-10-01
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

package com.example.rapidrecall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun RapidRecallBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val accentColor = MaterialTheme.colorScheme.primaryContainer

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        accentColor,
                        backgroundColor
                    )
                )
            )
    ) {
        content()
    }
}