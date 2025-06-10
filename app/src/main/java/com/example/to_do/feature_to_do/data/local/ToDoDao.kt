package com.example.to_do.feature_to_do.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.to_do.feature_to_do.data.local.dto.LocalToDoItem

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    fun getAllToDoItems(): List<LocalToDoItem>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getSingleToDoItemById(id: Int): LocalToDoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllToDoItems(todos: List<LocalToDoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDoItem(todo: LocalToDoItem): Long

    @Delete
    suspend fun deleteToDoItem(todo: LocalToDoItem)
}