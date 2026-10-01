// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated Attempt to support an in-progress attempt lifecycle,
// including completion logic for user input, success, and timestamp.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

class Attempt(
    val sequence: Sequence
) {
    var userAttempt: String? = null
    var success: Boolean? = null
    var timestamp: Long? = null

    fun completeAttempt(input: String) {
        userAttempt = input
        success = sequence.compare(input)
        timestamp = System.currentTimeMillis()
    }
}