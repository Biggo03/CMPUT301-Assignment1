// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated Attempt so completed round success is provided
// directly rather than recalculated inside the Attempt object.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

class Attempt(
    val sequence: Sequence,
    val userAttempt: String,
    val success: Boolean,
    val timestamp: Long
)