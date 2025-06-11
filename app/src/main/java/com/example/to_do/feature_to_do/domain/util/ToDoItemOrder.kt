package com.example.to_do.feature_to_do.domain.util

sealed class ToDoItemOrder(
    val sortingDirection: SortingDirection,
    val showArchived: Boolean
) {
    class Title(sortingDirection: SortingDirection, showArchived: Boolean): ToDoItemOrder(sortingDirection, showArchived)
    class Time(sortingDirection: SortingDirection, showArchived: Boolean): ToDoItemOrder(sortingDirection, showArchived)
    class Completed(sortingDirection: SortingDirection, showArchived: Boolean): ToDoItemOrder(sortingDirection, showArchived)

    fun copy(sortingDirection: SortingDirection, showArchived: Boolean): ToDoItemOrder {
        return when(this){
            is Title -> Title(sortingDirection,showArchived)
            is Time -> Title(sortingDirection,showArchived)
            is Completed -> Title(sortingDirection,showArchived)
        }
    }
}