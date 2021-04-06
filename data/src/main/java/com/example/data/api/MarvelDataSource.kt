package com.example.data.api

import com.example.data.api.services.characters.CharactersService
import com.example.data.toDomain
import com.example.domain.domain.model.Character

class MarvelDataSource(private val service: CharactersService): RemoteDataSource {

    override suspend fun getMovies(offset: Int): List<Character> =
        service.getCharacters(offset = offset).data.results.map { it.toDomain() }

}