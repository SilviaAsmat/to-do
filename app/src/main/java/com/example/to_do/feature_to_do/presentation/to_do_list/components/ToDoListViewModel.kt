package com.example.to_do.feature_to_do.presentation.to_do_list.components

import androidx.lifecycle.ViewModel
import com.example.to_do.feature_to_do.domain.use_case.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel@Inject constructor(
    private val toDoUseCases: ToDoUseCases,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
}