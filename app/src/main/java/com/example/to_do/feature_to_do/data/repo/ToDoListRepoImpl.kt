package com.example.to_do.feature_to_do.data.repo

import android.util.Log
import com.example.to_do.feature_to_do.data.di.IoDispatcher
import com.example.to_do.feature_to_do.data.local.ToDoDao
import com.example.to_do.feature_to_do.data.mapper.toLocalToDoItem
import com.example.to_do.feature_to_do.data.mapper.toLocalToDoItemListFromRemote
import com.example.to_do.feature_to_do.data.mapper.toRemoteToDoItem
import com.example.to_do.feature_to_do.data.mapper.toToDoItem
import com.example.to_do.feature_to_do.data.mapper.toToDoItemListFromLocal
import com.example.to_do.feature_to_do.data.remote.ToDoAPI
import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.feature_to_do.domain.repo.ToDoListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class ToDoListRepoImpl(
    private val dao: ToDoDao,
    private val api: ToDoAPI,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ToDoListRepo {
    override suspend fun getAllToDos(): List<ToDoItem> {
        getAllToDosFromRemote()
        return dao.getAllToDoItems().toToDoItemListFromLocal()
    }

    override suspend fun getAllToDosFromLocalCache(): List<ToDoItem> {
        return dao.getAllToDoItems().toToDoItemListFromLocal()
    }

    override suspend fun getAllToDosFromRemote() {
        return withContext(dispatcher) {
            try {
                refreshRoomCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from Remote")
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device offline, not data from local Room cache")
                        }
                    }

                    else -> throw e
                }
            }
        }
    }

    private suspend fun refreshRoomCache() {
        val remoteBooks = api.getAllToDos().filterNotNull()
        dao.addAllToDoItems(remoteBooks.toLocalToDoItemListFromRemote())
    }

    private fun isCacheEmpty(): Boolean {
        var empty = true
        if (dao.getAllToDoItems().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getSingleToDoItemById(id: Int): ToDoItem? {
        return dao.getSingleToDoItemById(id)?.toToDoItem()
    }

    override suspend fun addToDoItem(todo: ToDoItem) {
        val newId = dao.addToDoItem(todo.toLocalToDoItem())
        val id = newId.toInt()
        val url = "todo/$id.json"
        api.addToDo(url, todo.toRemoteToDoItem().copy(id = id))
    }

    override suspend fun updateToDoItem(todo: ToDoItem) {
        dao.addToDoItem(todo.toLocalToDoItem())
        api.updateToDoItem(todo.id, todo.toRemoteToDoItem())
    }

    override suspend fun deleteToDoItem(todo: ToDoItem) {
        try {
            val response = api.deleteToDo(todo.id)
            if (response.isSuccessful) {
                Log.i("API_DELETE", "Response Successful")
            } else {
                Log.i("API_DELETE", "Response Unsuccessful")
                Log.i("API_DELETE", response.message())
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete")
                }
                else -> throw e
            }
        }
    }
}