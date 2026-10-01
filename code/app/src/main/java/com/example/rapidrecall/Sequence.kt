package com.example.rapidrecall

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the Sequence class, including random digit
// sequence generation and comparison with user input.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

import kotlin.random.Random

class Sequence(val sequenceLength: Int) {

    val sequence: String

    init {
        require(sequenceLength in 1..10) {
            "Sequence length must be between 1 and 10."
        }

        sequence = buildString {
            repeat(sequenceLength) {
                append(Random.nextInt(0, 10))
            }
        }
    }

    fun compare(input: String): Boolean {
        return input == sequence
    }
}