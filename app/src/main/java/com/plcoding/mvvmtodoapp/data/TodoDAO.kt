package com.plcoding.mvvmtodoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert()

    @Query("SELECT * FROM Todo")
    suspend fun getTodos(): List<Todo>

    @Query("DELETE FROM todo WHERE id=:id")
    suspend fun deleteTodo(id: Int)

}