package com.example.to_do.feature_to_do.domain.use_case

import com.example.to_do.core.util.ToDoUseCaseStrings
import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.feature_to_do.domain.repo.ToDoListRepo
import com.example.to_do.feature_to_do.domain.util.InvalidToDoItemException
import com.example.to_do.feature_to_do.domain.util.SortingDirection
import com.example.to_do.feature_to_do.domain.util.ToDoItemOrder
import javax.inject.Inject

class ToDoUseCases @Inject constructor (
    private val repo: ToDoListRepo
){
    suspend fun addToDoItem(todo: ToDoItem){
        if(todo.title.isBlank() || todo.description.isBlank()){
            throw InvalidToDoItemException(ToDoUseCaseStrings.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.addToDoItem(todo)
    }
    suspend fun updateToDoItem(todo: ToDoItem){
        if(todo.title.isBlank() || todo.description.isBlank()){
            throw InvalidToDoItemException(ToDoUseCaseStrings.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.updateToDoItem(todo)
    }

    suspend fun deleteToDoItem(todo: ToDoItem){
        repo.deleteToDoItem(todo)
    }

    suspend fun toggleCompleteToDoItem(todo: ToDoItem){
        repo.updateToDoItem(todo.copy(completed = !todo.completed))
    }

    suspend fun toggleArchiveToDoItem(todo: ToDoItem){
        repo.updateToDoItem(todo.copy(archived = !todo.archived))
    }

    suspend fun getToDoItemById(id: Int): ToDoItem?{
        return repo.getSingleToDoItemById(id)
    }

    suspend fun getTodoItems(
        todoItemOrder: ToDoItemOrder,// = TodoItemOrder.Time(SortingDirection.Down, true)
    ): ToDoUseCaseResult{
        var todos = repo.getAllToDosFromLocalCache()

        if(todos.isEmpty()){
            todos = repo.getAllToDos()
        }

        val filteredTodos = if (todoItemOrder.showArchived){
            todos
        } else {
            todos.filter{ !it.archived }
        }

        return when (todoItemOrder.sortingDirection){
            is SortingDirection.Down -> {
                when (todoItemOrder) {
                    is ToDoItemOrder.Title -> ToDoUseCaseResult.Success(filteredTodos.sortedByDescending { it.title.lowercase() })
                    is ToDoItemOrder.Time -> ToDoUseCaseResult.Success(filteredTodos.sortedByDescending { it.timestamp })
                    is ToDoItemOrder.Completed -> ToDoUseCaseResult.Success(filteredTodos.sortedByDescending { it.completed })
                }
            }
            is SortingDirection.Up -> {
                when (todoItemOrder) {
                    is ToDoItemOrder.Title -> ToDoUseCaseResult.Success(filteredTodos.sortedBy { it.title.lowercase() })
                    is ToDoItemOrder.Time -> ToDoUseCaseResult.Success(filteredTodos.sortedBy { it.timestamp })
                    is ToDoItemOrder.Completed -> ToDoUseCaseResult.Success(filteredTodos.sortedBy { it.completed })
                }
            }
        }
    }

}

sealed class ToDoUseCaseResult{
    data class Success(val toDoItems: List<ToDoItem>) : ToDoUseCaseResult()
    data class Error(val message: String) : ToDoUseCaseResult()
}