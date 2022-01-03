package com.plcoding.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {


    suspend fun insertTodo(todo: Todo)

//returns flow thefore no need for suspend
    fun getTodos(): Flow<List<Todo>>


    suspend fun deleteTodo(todo: Todo)


    suspend fun getTodoById(id: Int): Todo?
}