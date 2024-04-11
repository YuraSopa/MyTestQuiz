package com.example.mytestquiz.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val currentQuestion = viewModel.getCurrentQuestion()

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = currentQuestion.question)
        Spacer(modifier = Modifier.height(12.dp))
        currentQuestion.options.forEachIndexed { index, option ->
            OptionButton(
                option = option,
                onClick = { viewModel.selectAnswer(index) }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { viewModel.nextQuestion() },
            enabled = viewModel.selectedAnswerIndex.intValue != -1
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun OptionButton(
    option: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = option)
    }
}