package com.example.to_do.feature_to_do.domain.repo

import com.example.to_do.feature_to_do.domain.model.ToDoItem

interface ToDoListRepo {
    suspend fun getAllToDos(): List<ToDoItem>
    suspend fun getAllToDosFromLocalCache(): List<ToDoItem>
    suspend fun getAllToDosFromRemote()
    suspend fun getSingleToDoItemById(id: Int): ToDoItem?
    suspend fun addToDoItem(todo: ToDoItem)
    suspend fun updateToDoItem(todo: ToDoItem)
    suspend fun deleteToDoItem(todo: ToDoItem)
}