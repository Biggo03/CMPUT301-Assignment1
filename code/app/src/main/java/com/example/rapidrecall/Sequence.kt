package com.example.rapidrecall

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the Sequence class, including random digit
// sequence generation and comparison with user input.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

import kotlin.random.Random

// This is a simple class modelling the sequence. It was made so that the sequence could have
// built in constraints, and a method to make comparison clear. Another bonus is that it abstracted
// the sequence building away to this file. This simplified putting it into the Attempt object
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