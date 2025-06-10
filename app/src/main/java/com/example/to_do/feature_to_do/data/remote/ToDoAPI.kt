package com.example.to_do.feature_to_do.data.remote

import com.example.to_do.feature_to_do.data.remote.dto.RemoteToDoItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface ToDoAPI {
    @GET("todo.json")
    suspend fun getAllToDos(): List<RemoteToDoItem>

    @GET("todo.json?orderBy=\"ID\"")
    suspend fun getSingleToDoById(@retrofit2.http.Query("equalTo")id: Int?): Map<String, RemoteToDoItem>

//    @POST("todo.json")
//    sus pend fun addToDo(@Body url:String, @Body updatedToDo: RemoteToDoItem): Response<Unit>

    @PUT
    suspend fun addToDo(@Url url: String, @Body updatedToDo: RemoteToDoItem): Response<Unit>

    @DELETE("todo/{id}.json")
    suspend fun deleteToDo(@Path("id") id :Int?) : Response<Unit>

    @PUT("todo/{id}.json")
    suspend fun updateToDoItem(@Path("id") id: Int?, @Body toDoItem: RemoteToDoItem) : Response<Unit>

}