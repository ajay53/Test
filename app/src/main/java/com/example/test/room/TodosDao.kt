package com.example.test.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.test.Todos

@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todos: Todos)
}