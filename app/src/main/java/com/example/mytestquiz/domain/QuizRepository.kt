package com.example.mytestquiz.domain

import android.content.Context
import com.example.mytestquiz.R
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val context: Context
) {

    fun getQuestion(): List<Question> {

        return listOf(
            Question(
                question = context.getString(R.string.question_1),
                options = context.resources.getStringArray(R.array.options_question_1).asList(),
                correctAnswerIndex = 2
            ),
            Question(
                question = context.getString(R.string.question_2),
                options = context.resources.getStringArray(R.array.options_question_2).asList(),
                correctAnswerIndex = 1
            ),
            Question(
                question = context.getString(R.string.question_3),
                options = context.resources.getStringArray(R.array.options_question_3).asList(),
                correctAnswerIndex = 0
            )
        )
    }
}