package com.example.to_do.feature_to_do.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.to_do.feature_to_do.data.local.dto.LocalToDoItem

@Database(
    entities = [LocalToDoItem::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val dao: ToDoDao

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}