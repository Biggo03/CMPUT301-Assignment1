// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the Round class state structure and methods for
// round setup and submitting/comparing user input.
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
    var targetSequence: Sequence? = null

    var presentationState by mutableStateOf(PresentationState.SETUP)
    var currentUserInput by mutableStateOf("")
    var feedback by mutableStateOf("")

    var success: Boolean = false

    fun setupRound(sequenceLength: Int) {
        selectedSequenceLength = sequenceLength
        targetSequence = Sequence(sequenceLength)
        presentationState = PresentationState.SHOWING_SEQUENCE
    }

    fun submitInput(input: String) {
        val sequence = requireNotNull(targetSequence) {
            "Target sequence must exist before submitting input."
        }

        currentUserInput = input
        success = sequence.compare(input)

        feedback = if (success) {
            "Success, sequence guessed correctly!"
        } else {
            "Failure of grand proportions, sequence guessed incorrectly"
        }

        presentationState = PresentationState.FINISHED
    }
}