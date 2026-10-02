// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated GameSession to log the completed Attempt stored
// within a Round and update session statistics.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

package com.example.rapidrecall

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class GameState {
    MAIN_MENU,
    IN_GAME,
    ATTEMPT_LOG,
    SUMMARY
}

// This is the top level class that models the whole session. It essentially stores all info that needs
// to be persistant throughout runtime, and handles changing the state of the game based on user input.
// so it could also be seen as a bit of a controller.

// This provided a clean way to have all the top level information, and interface with the composable functions.
class GameSession {

    var gameState by mutableStateOf(GameState.MAIN_MENU)

    val attempts: MutableList<Attempt> = mutableListOf()

    var totalAttempts: Int = 0
    var successfulAttempts: Int = 0
    var failedAttempts: Int = 0

    var successRate: Float = 0.0f

    fun changeGameState(newState: GameState) {
        gameState = newState
    }

    fun logAttempt(round: Round) {
        val attempt = requireNotNull(round.attempt) {
            "A completed round must contain an attempt."
        }

        val success = requireNotNull(attempt.success) {
            "Attempt must be completed before it can be logged."
        }

        attempts.add(attempt)

        totalAttempts = attempts.size

        if (success) {
            successfulAttempts++
        } else {
            failedAttempts++
        }

        successRate = successfulAttempts.toFloat() / totalAttempts * 100
    }
}