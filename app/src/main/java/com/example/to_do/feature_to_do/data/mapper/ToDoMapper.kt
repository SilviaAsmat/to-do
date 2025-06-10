package com.example.to_do.feature_to_do.data.mapper

import com.example.to_do.feature_to_do.data.local.dto.LocalToDoItem
import com.example.to_do.feature_to_do.data.remote.dto.RemoteToDoItem
import com.example.to_do.feature_to_do.domain.model.ToDoItem

fun ToDoItem.toLocalToDoItem(): LocalToDoItem {
    return LocalToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun ToDoItem.toRemoteToDoItem(): RemoteToDoItem {
    return RemoteToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun LocalToDoItem.toToDoItem(): ToDoItem{
    return ToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun LocalToDoItem.toRemoteToDoItem(): RemoteToDoItem{
    return RemoteToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun RemoteToDoItem.toToDoItem(): ToDoItem{
    return ToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun RemoteToDoItem.toLocalToDoItem(): LocalToDoItem{
    return LocalToDoItem(
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed,
        archived = archived,
        id = id
    )
}

fun List<ToDoItem>.toLocalToDoItemList(): List<LocalToDoItem>{
    return this.map {toDo->
        LocalToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}

fun List<ToDoItem>.toRemoteToDoItemList(): List<RemoteToDoItem>{
    return this.map {toDo->
        RemoteToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}

fun List<LocalToDoItem>.toToDoItemListFromLocal(): List<ToDoItem>{
    return this.map {toDo->
        ToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}

fun List<LocalToDoItem>.toRemoteToDoItemListFromLocal(): List<RemoteToDoItem>{
    return this.map {toDo->
        RemoteToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}

fun List<RemoteToDoItem>.toToDoItemListFromRemote(): List<ToDoItem>{
    return this.map {toDo->
        ToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}

fun List<RemoteToDoItem>.toLocalToDoItemListFromRemote(): List<LocalToDoItem>{
    return this.map {toDo->
        LocalToDoItem(
            title = toDo.title,
            description = toDo.description,
            timestamp = toDo.timestamp,
            completed = toDo.completed,
            archived = toDo.archived,
            id = toDo.id
        )
    }
}


