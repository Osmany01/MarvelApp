package com.example.data.local

import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    //region Characters
    suspend fun size(): Int
    suspend fun saveCharacters(character: List<Character>)
    fun getCharacters(): Flow<List<Character>>
    //endregion

    //region CharacterDetails
    suspend fun characterDetailsSize(): Int
    suspend fun saveCharacterDetails(characterDetails: CharacterDetails)
    fun getCharacterDetails(characterId: Int): Flow<CharacterDetails>
    //endregion
}