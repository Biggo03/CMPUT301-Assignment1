// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated GameSession so gameState uses Compose-observable
// state, allowing UI recomposition when the application state changes.
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

    // AI assistance: OpenAI, ChatGPT
    // Prompt/subject: Updated GameSession logging so a completed Round is
    // converted into an Attempt and added to the session statistics.
    // Date: 2026-09-30
    // Conversation: [link to full ChatGPT conversation]
    fun logAttempt(round: Round) {
        val sequence = requireNotNull(round.targetSequence) {
            "A completed round must contain a target sequence."
        }

        val attempt = Attempt(
            sequence = sequence,
            userAttempt = round.currentUserInput,
            success = round.success,
            timestamp = System.currentTimeMillis()
        )

        attempts.add(attempt)

        totalAttempts = attempts.size

        if (attempt.success) {
            successfulAttempts++
        } else {
            failedAttempts++
        }

        successRate = successfulAttempts.toFloat() / totalAttempts * 100
    }
}