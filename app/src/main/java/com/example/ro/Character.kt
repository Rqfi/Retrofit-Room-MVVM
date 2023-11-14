package com.example.ro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val height: String?,
    val birthYear: String?,
    val gender: String?
)
