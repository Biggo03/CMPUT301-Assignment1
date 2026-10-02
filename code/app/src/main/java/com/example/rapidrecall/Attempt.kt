// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated Attempt to support an in-progress attempt lifecycle,
// including completion logic for user input, success, and timestamp.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

package com.example.rapidrecall

// This object models a given attempt. It has the actual sequence that is trying to be guessed,
// and then the other required fields, being whether it was successful or not, and when it was completed.
// This model was made as it can easily be stored and retrieved later for the attempt log, as it has
// All required information. Having it also made it easy to store all info once an attempt was complete.
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