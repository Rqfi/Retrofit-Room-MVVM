package com.example.ro

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(character: List<Character>)

    @Query("SELECT * FROM character")
    fun showAllData():List<Character>

    @Query("SELECT * FROM character WHERE name LIKE :searchQuery")
    fun searchData(searchQuery: String): List<Character>

    @Query("DELETE FROM character")
    suspend fun deleteAllData()
}