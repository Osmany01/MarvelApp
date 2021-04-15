package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.characterdetails.RoomCharacterDetails
import com.example.data.local.model.characters.RoomCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    //region Characters
    @Query("SELECT * FROM RoomCharacter")
    fun getAll(): Flow<List<RoomCharacter>>

    @Query("SELECT COUNT(id) FROM RoomCharacter")
    suspend fun charactersCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(roomCharacter: List<RoomCharacter>)
    //endregion

    //region CharacterDetails
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterDetails(roomCharacterDetails: RoomCharacterDetails)

    @Query("SELECT * FROM RoomCharacterDetails WHERE id = :characterId")
    fun getCharacterDetails(characterId: Int): Flow<RoomCharacterDetails>

    @Query("SELECT COUNT(id) FROM RoomCharacterDetails")
    suspend fun characterDetailsCount(): Int

    //endregion
}