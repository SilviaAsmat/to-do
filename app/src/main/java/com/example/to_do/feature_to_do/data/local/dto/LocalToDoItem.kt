package com.example.to_do.feature_to_do.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "todo")
data class LocalToDoItem(
    val title: String,
    val description: String,
    val timestamp: Long,
    val completed: Boolean,
    val archived: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int?
)
