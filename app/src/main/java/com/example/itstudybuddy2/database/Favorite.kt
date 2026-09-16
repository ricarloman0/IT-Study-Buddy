package com.example.itstudybuddy2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    val topicName: String,

    val note: String = ""
)