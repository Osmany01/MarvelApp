package com.example.domain.domain.repositories

import com.example.domain.domain.model.characterdetails.CharacterDetails
import kotlinx.coroutines.flow.Flow


interface CharacterDetailsRepository {

    fun getCharacterDetails(characterId: Int): Flow<CharacterDetails>

    suspend fun checkCharacterDetails(characterId: Int)
}