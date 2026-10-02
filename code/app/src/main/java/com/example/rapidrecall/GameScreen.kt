// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Generated the GameScreen state dispatcher, SetupScreen,
// SequenceDisplayScreen, and UserInputScreen for collecting and submitting guesses.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e

package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    round: Round,
    gameSession: GameSession,
    modifier: Modifier = Modifier
) {
    when (round.presentationState) {
        PresentationState.SETUP -> {
            SetupScreen(round)
        }

        PresentationState.SHOWING_SEQUENCE -> {
            SequenceDisplayScreen(round)
        }

        PresentationState.AWAITING_INPUT -> {
            UserInputScreen(round)
        }

        PresentationState.FINISHED -> {
            FinishedScreen(
                round = round,
                gameSession = gameSession
            )
        }
    }
}

@Composable
fun SetupScreen(
    round: Round,
    modifier: Modifier = Modifier
) {
    var sequenceLengthInput by remember {
        mutableStateOf("")
    }

    val sequenceLength = sequenceLengthInput.toIntOrNull()
    val validLength = sequenceLength != null && sequenceLength in 1..10

    RapidRecallBackground(
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Select a sequence length from 1 to 10")

            OutlinedTextField(
                value = sequenceLengthInput,
                onValueChange = { newInput ->
                    sequenceLengthInput = newInput
                },
                label = {
                    Text("Sequence Length")
                }
            )

            RapidRecallButton(
                text = "Start Round",
                onClick = {
                    if (sequenceLength != null) {
                        round.setupRound(sequenceLength)
                    }
                },
                enabled = validLength
            )
        }
    }

}

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Updated SequenceDisplayScreen timing to display a
// one-second blank interval between sequence digits.
// Date: 2026-10-02
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e
@Composable
fun SequenceDisplayScreen(
    round: Round,
    modifier: Modifier = Modifier
) {
    val sequence = round.attempt?.sequence?.sequence ?: return

    var currentIndex by remember(sequence) {
        mutableIntStateOf(0)
    }

    var showDigit by remember(sequence) {
        mutableStateOf(true)
    }

    LaunchedEffect(sequence) {
        for (index in sequence.indices) {
            currentIndex = index
            showDigit = true

            delay(2000)

            if (index != sequence.lastIndex) {
                showDigit = false
                delay(500)
            }
        }

        round.presentationState = PresentationState.AWAITING_INPUT
    }

    RapidRecallBackground(
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (showDigit) {
                    sequence[currentIndex].toString()
                } else {
                    ""
                },
                fontSize = 64.sp
            )
        }
    }
}

@Composable
fun UserInputScreen(
    round: Round,
    modifier: Modifier = Modifier
) {
    var userInput by remember {
        mutableStateOf("")
    }

    RapidRecallBackground(
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = userInput,
                onValueChange = { newInput ->
                    userInput = newInput
                },
                label = {
                    Text("Enter Sequence")
                }
            )

            RapidRecallButton(
                text ="Submit",
                onClick = {
                    round.submitInput(userInput)
                }
            )
        }
    }
}

// AI assistance: OpenAI, ChatGPT
// Prompt/subject: Added FinishedScreen for displaying round feedback,
// logging the completed attempt, and returning to the main menu.
// Date: 2026-09-30
// Conversation: https://chatgpt.com/share/6ac00678-87d4-83e8-b2d2-97a0757adc8e
@Composable
fun FinishedScreen(
    round: Round,
    gameSession: GameSession,
    modifier: Modifier = Modifier
) {
    RapidRecallBackground(
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(round.feedback)

            RapidRecallButton(
                text = "Return to Main Menu",
                onClick = {
                    gameSession.logAttempt(round)
                    gameSession.changeGameState(GameState.MAIN_MENU)
                }
            )
        }
    }
}
