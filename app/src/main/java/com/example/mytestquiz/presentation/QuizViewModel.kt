package com.example.mytestquiz.presentation

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.mytestquiz.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    repository: QuizRepository
) : ViewModel() {

    private val question = repository.getQuestion()
    var currentQuestionIndex = mutableIntStateOf(0)
        private set
    var selectedAnswerIndex = mutableIntStateOf(-1)
        private set

    fun nextQuestion() {
        if (currentQuestionIndex.intValue < question.size - 1) {
            currentQuestionIndex.intValue++
            selectedAnswerIndex.intValue = -1
        }
    }

    fun selectAnswer(index: Int) {
        selectedAnswerIndex.intValue = index
    }

    fun getCurrentQuestion() = question[currentQuestionIndex.intValue]
}