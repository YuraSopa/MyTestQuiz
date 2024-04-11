package com.example.mytestquiz.presentation

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mytestquiz.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    repository: QuizRepository
) : ViewModel() {

    val question = repository.getQuestion()
    var currentQuestionIndex = mutableIntStateOf(0)

    var selectedAnswerIndex = mutableIntStateOf(-1)
    var openDialog = mutableStateOf(false)
        private set

    var correctAnswers = mutableIntStateOf(0)
        private set

    fun nextQuestion() {
        if (selectedAnswerIndex.intValue == question[currentQuestionIndex.intValue].correctAnswerIndex) {
            correctAnswers.intValue++
        }
        if (currentQuestionIndex.intValue < question.size - 1) {
            currentQuestionIndex.intValue++
            selectedAnswerIndex.intValue = -1
        } else {
            openDialog.value = true
        }

    }

    fun selectAnswer(index: Int) {
        selectedAnswerIndex.intValue = index
    }

    fun getCurrentQuestion() = question[currentQuestionIndex.intValue]
}