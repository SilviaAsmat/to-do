package com.example.to_do.feature_to_do.presentation.to_do_list.components

import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import com.example.to_do.core.util.ToDoListStrings
import com.example.to_do.feature_to_do.domain.util.SortingDirection
import com.example.to_do.feature_to_do.domain.util.ToDoItemOrder

@Composable
fun SortingDrawerOptions(
    toDoItemOrder: ToDoItemOrder,
    onOrderChange: (ToDoItemOrder) -> Unit
){
    val titleSelected = toDoItemOrder::class == ToDoItemOrder.Title::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = ToDoListStrings.TITLE,
                isChecked = titleSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(ToDoItemOrder.Title(toDoItemOrder.sortingDirection, toDoItemOrder.showArchived))
        }
    )
    val timeSelected = toDoItemOrder::class == ToDoItemOrder.Time::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = ToDoListStrings.TIME,
                isChecked = timeSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(ToDoItemOrder.Time(toDoItemOrder.sortingDirection, toDoItemOrder.showArchived))
        }
    )
    val completedSelected = toDoItemOrder::class == ToDoItemOrder.Completed::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = ToDoListStrings.Completed,
                isChecked = completedSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(ToDoItemOrder.Completed(toDoItemOrder.sortingDirection, toDoItemOrder.showArchived))
        }
    )
    HorizontalDivider()
    val sortDownSelected = toDoItemOrder.sortingDirection == SortingDirection.Down
    NavigationDrawerItem(
        label = {
            IconRow(
                text = ToDoListStrings.SORT_DOWN,
                isChecked = sortDownSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(toDoItemOrder.copy(SortingDirection.Down, toDoItemOrder.showArchived))
        }
    )

    
}