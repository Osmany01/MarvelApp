package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.RoomCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Query("SELECT * FROM RoomCharacter")
    fun getAll(): Flow<List<RoomCharacter>>

    @Query("SELECT COUNT(id) FROM RoomCharacter")
    suspend fun charactersCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(roomCharacter: List<RoomCharacter>)

}