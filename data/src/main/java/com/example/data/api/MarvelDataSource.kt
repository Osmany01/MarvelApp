package com.example.data.api

import com.example.data.api.services.characterdetails.CharacterDetailsService
import com.example.data.api.services.characters.CharactersService
import com.example.data.toDomain
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character

class MarvelDataSource(private val characterService: CharactersService,
                        private val characterDetailsService: CharacterDetailsService): RemoteDataSource {

    override suspend fun getCharacters(offset: Int): List<Character> =
        characterService.getCharacters(offset = offset).data.results.map { it.toDomain() }

    override suspend fun getCharacter(characterId: Int): CharacterDetails =
        characterDetailsService.getCharacter(characterId).data.results.toDomain()
}