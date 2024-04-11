package com.example.mytestquiz.domain

data class Quiz(
    val user: String,
    val questions: List<Question>
)