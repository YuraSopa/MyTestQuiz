package com.example.mytestquiz.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val currentQuestion = viewModel.getCurrentQuestion()


    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentQuestion.question,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        currentQuestion.options.forEachIndexed { index, option ->
            OptionButton(
                option = option,
                onClick = {
                    viewModel.selectAnswer(index)

                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                viewModel.nextQuestion()

            },
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
    var selected by remember { mutableStateOf(false) } //need to fix

    Button(
        onClick = {
            onClick()
//            selected = true
        },
        modifier = Modifier
            .fillMaxWidth(),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = if (selected) Color.Blue else Color.Gray
//        ),
    ) {
        Text(text = option)
    }
}

@Composable
fun CustomDialog(openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        Dialog(onDismissRequest = { }) {
            CustomDialogUI(openDialog = openDialog)
        }
    }

}

@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
    openDialog: MutableState<Boolean>
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Результат",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Загальна кількість запитань: ${viewModel.question.size}." +
                            "\nДали вірну відповідь: ${viewModel.correctAnswers.intValue}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextButton(
                    onClick = {
                        openDialog.value = false
                        viewModel.currentQuestionIndex.intValue = 0
                        viewModel.correctAnswers.intValue = 0
                    }) {
                    Text(
                        "Спробувати знову",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}