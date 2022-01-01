package com.plcoding.mvvmtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    //get realtime update in case database changes
    @Query("SELECT * FROM Todo")
    suspend fun getTodos(): Flow<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)

    //return nullable Todos if the id doesn't exist instead of crashing the app
    @Query("SELECT * FROM Todo WHERE id = :id")
    suspend fun getTodoById(id: Int):Todo?


}