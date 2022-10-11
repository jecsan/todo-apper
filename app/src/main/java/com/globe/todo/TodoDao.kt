package com.globe.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo: Todo) : Long

    @Delete
    suspend fun delete(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("SELECT * FROM Todo")
    suspend fun getAll(): List<Todo>

    @Query("SELECT * FROM Todo")
    fun getAllFlow(): Flow<List<Todo>>

}