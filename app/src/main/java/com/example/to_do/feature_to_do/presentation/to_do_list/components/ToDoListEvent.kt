package com.example.to_do.feature_to_do.presentation.to_do_list.components

import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.feature_to_do.domain.util.ToDoItemOrder

sealed class ToDoListEvent {
    data class Sort(val toDoItemOrder: ToDoItemOrder): ToDoListEvent()
    data class Delete(val todo: ToDoItem): ToDoListEvent()
    data class ToggleCompleted(val todo: ToDoItem): ToDoListEvent()
    data class ToggleArchives(val todo: ToDoItem): ToDoListEvent()
    object UndoDelete: ToDoListEvent()
}