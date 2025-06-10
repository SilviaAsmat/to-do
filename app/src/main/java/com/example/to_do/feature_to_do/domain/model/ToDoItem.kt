package com.example.to_do.feature_to_do.domain.model

data class ToDoItem(
    val title: String,
    val description: String,
    val timestamp: Long,
    val completed: Boolean,
    val archived: Boolean,
    val id: Int?
)