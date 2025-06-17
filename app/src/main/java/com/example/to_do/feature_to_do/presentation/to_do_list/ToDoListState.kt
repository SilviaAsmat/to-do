package com.example.to_do.feature_to_do.presentation.to_do_list.components

import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.feature_to_do.domain.util.SortingDirection
import com.example.to_do.feature_to_do.domain.util.ToDoItemOrder

data class ToDoListState(
    val todoItems: List<ToDoItem> = emptyList(),
    val todoItemOrder: ToDoItemOrder = ToDoItemOrder.Time(SortingDirection.Down, true),
    val isLoading: Boolean = true,
    val error: String? = null
    )
