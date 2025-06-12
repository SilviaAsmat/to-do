package com.example.to_do.feature_to_do.presentation.to_do_list.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.core.util.ToDoListStrings
import com.example.to_do.feature_to_do.data.di.IoDispatcher
import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.feature_to_do.domain.use_case.ToDoUseCaseResult
import com.example.to_do.feature_to_do.domain.use_case.ToDoUseCases
import com.example.to_do.feature_to_do.domain.util.ToDoItemOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class ToDoListViewModel@Inject constructor(
    private val toDoUseCases: ToDoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(ToDoListState())
    val state: State<ToDoListState> = _state

    private var undoToDoItem: ToDoItem? = null
    private var getToDoItemsJob: Job? = null
    private val errorHandler = CoroutineExceptionHandler{_, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )

    }

    fun onEvent(event: ToDoListEvent){
        when(event){
            is ToDoListEvent.Delete -> {
                viewModelScope.launch( dispatcher + errorHandler) {
                    toDoUseCases.deleteToDoItem(event.todo)
                    getToDoItems()
                    undoToDoItem = event.todo
                }

            }
            is ToDoListEvent.Sort -> {
                if( // Check if state order already matches event order
                    _state.value.todoItemOrder::class == event.toDoItemOrder::class &&
                    _state.value.todoItemOrder.showArchived == event.toDoItemOrder.showArchived &&
                    _state.value.todoItemOrder.sortingDirection == event.toDoItemOrder.sortingDirection
                ){
                    return
                }

                _state.value = _state.value.copy(
                        todoItemOrder = event.toDoItemOrder
                        )
                getToDoItems()
            }
            is ToDoListEvent.ToggleArchives -> {
                viewModelScope.launch( dispatcher + errorHandler) {
                    toDoUseCases.toggleArchiveToDoItem(todo = event.todo)
                    getToDoItems()
                }
            }
            is ToDoListEvent.ToggleCompleted -> {
                viewModelScope.launch( dispatcher + errorHandler) {
                    toDoUseCases.toggleCompleteToDoItem(todo = event.todo)
                    getToDoItems()
                }
            }
            ToDoListEvent.UndoDelete -> {
                viewModelScope.launch( dispatcher + errorHandler) {
                    toDoUseCases.addToDoItem(undoToDoItem?: return@launch)
                    undoToDoItem = null
                    getToDoItems()
                }
            }
        }
    }

    fun getToDoItems(){
        getToDoItemsJob?.cancel()

        getToDoItemsJob = viewModelScope.launch(dispatcher + errorHandler) {
            val result = toDoUseCases.getTodoItems(
                todoItemOrder = _state.value.todoItemOrder
            )
            when(result) {
                is ToDoUseCaseResult.Success -> {
                    _state.value = _state.value.copy(
                        todoItems = result.toDoItems,
                        todoItemOrder = _state.value.todoItemOrder,
                        isLoading = false
                    )
                }
                is ToDoUseCaseResult.Error -> {
                    _state.value = _state.value.copy(
                        error = ToDoListStrings.CANT_GET_TODOS,
                        isLoading = false
                    )
                }
            }
        }
    }

}