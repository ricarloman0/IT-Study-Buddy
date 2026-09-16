package com.example.itstudybuddy2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<Favorite>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE topicName = :topicName)")
    suspend fun isFavorite(topicName: String): Boolean

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}