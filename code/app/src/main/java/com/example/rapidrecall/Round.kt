// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated Round to contain an Attempt object rather than
// separately storing the target sequence, user input, and success result.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

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

// The object representing a round. This object is intended to model a single round of the game
// Having all the data related to a round be in one container was done so all storage, and
// activities related to a round could be done on one object. Once the round is done, the
// important persistant information can be extracted, and the remainder can be deleted, making it relatively clean
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
            "Sequence guessed correctly!"
        } else {
            "Sequence guessed incorrectly"
        }

        presentationState = PresentationState.FINISHED
    }
}