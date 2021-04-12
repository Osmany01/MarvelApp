package com.example.data.api

import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character

interface RemoteDataSource {
    suspend fun getCharacters(offset: Int): List<Character>
    suspend fun getCharacter(characterId: Int): CharacterDetails
}