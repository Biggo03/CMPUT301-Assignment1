// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated Round to contain an Attempt object rather than
// separately storing the target sequence, user input, and success result.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

package com.example.rapidrecall

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class PresentationState {
    SETUP,
    SHOWING_SEQUENCE,
    AWAITING_INPUT,
    FINISHED
}

class Round {

    var selectedSequenceLength: Int? = null
    var attempt: Attempt? = null

    var presentationState by mutableStateOf(PresentationState.SETUP)
    var feedback by mutableStateOf("")

    fun setupRound(sequenceLength: Int) {
        selectedSequenceLength = sequenceLength

        attempt = Attempt(
            sequence = Sequence(sequenceLength)
        )

        presentationState = PresentationState.SHOWING_SEQUENCE
    }

    fun submitInput(input: String) {
        val currentAttempt = requireNotNull(attempt) {
            "Attempt must exist before submitting input."
        }

        currentAttempt.completeAttempt(input)

        feedback = if (currentAttempt.success == true) {
            "Success, sequence guessed correctly!"
        } else {
            "Failure of grand proportions, sequence guessed incorrectly"
        }

        presentationState = PresentationState.FINISHED
    }
}