package com.example.mytestquiz.domain

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)