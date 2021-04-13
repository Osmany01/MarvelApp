package com.example.data.api

import com.example.data.api.services.characterdetails.CharacterDetailsService
import com.example.data.api.services.characters.ApiCharacter
import com.example.data.api.services.characters.CharactersService
import com.example.data.toDomain
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character
import kotlinx.coroutines.delay

class FakeMarvelDataSource(
    private val characters: List<Character> = listOf(),
    private val characterDetails: CharacterDetails,
    private val delay: Long = 0

) : RemoteDataSource {

    override suspend fun getCharacters(offset: Int): List<Character> {
        delay(delay)
        return characters
    }


    override suspend fun getCharacter(characterId: Int): CharacterDetails =
        characterDetails
}