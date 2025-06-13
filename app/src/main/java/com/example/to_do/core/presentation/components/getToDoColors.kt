package com.example.to_do.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.to_do.feature_to_do.domain.model.ToDoItem

data class ToDoItemColors(
    val backgroundColor: Color,
    val textColor: Color,
    val archiveIconColor: Color,
    val checkColor: Color
)

@Composable
fun getToDoColors(todo: ToDoItem): ToDoItemColors{
    return if(todo.archived){
        ToDoItemColors(
            backgroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
            textColor = MaterialTheme.colorScheme.onSecondary,
            archiveIconColor = MaterialTheme.colorScheme.onSecondary,
            checkColor = if(todo.completed) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.onSecondary
        )
    } else {
        ToDoItemColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            archiveIconColor = MaterialTheme.colorScheme.secondary,
            checkColor = if(todo.completed) MaterialTheme.colorScheme.tertiaryContainer
            else MaterialTheme.colorScheme.secondary
        )
    }
}