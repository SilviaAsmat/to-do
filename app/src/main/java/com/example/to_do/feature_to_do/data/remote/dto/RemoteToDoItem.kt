package com.example.to_do.feature_to_do.data.remote.dto

import com.google.gson.annotations.SerializedName


data class RemoteToDoItem(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Timestamp")
    val timestamp: Long,
    @SerializedName("Completed")
    val completed: Boolean,
    @SerializedName("Archived")
    val archived: Boolean,
    @SerializedName("ID")
    val id: Int?
)

