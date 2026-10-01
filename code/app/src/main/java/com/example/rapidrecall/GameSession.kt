// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated GameSession to log the completed Attempt stored
// within a Round and update session statistics.
// Date: 2026-09-30
// Conversation: [link to full ChatGPT conversation]

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