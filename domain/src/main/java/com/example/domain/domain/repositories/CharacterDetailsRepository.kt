package com.example.domain.domain.repositories

import com.example.domain.domain.model.characterdetails.CharacterDetails
import kotlinx.coroutines.flow.Flow


interface CharacterDetailsRepository {

    suspend fun checkCharacterDetails(characterId: Int): Flow<CharacterDetails?>
}