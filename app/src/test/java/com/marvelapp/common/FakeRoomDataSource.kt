package com.marvelapp.common

import com.example.data.local.LocalDataSource
import com.example.data.local.dao.MarvelDatabase
import com.example.data.toDomain
import com.example.data.toDomainCharacter
import com.example.data.toRoom
import com.example.data.toRoomCharacter
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characterdetails.ThumbnailCharacterDetails
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.model.characters.ThumbnailCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

open class FakeRoomDataSource(): LocalDataSource {

    private val characters = mutableListOf<Character>()
    private var characterDetails = CharacterDetails(1, "name", "description", ThumbnailCharacterDetails("path", "extention"))
    //region Characters
    override suspend fun size(): Int = characters.size

    override suspend fun saveCharacters(character: List<Character>) {

        this.characters += character
    }

    override fun getCharacters(): Flow<List<Character>> =
        flowOf(characters)
    //endregion

    //region CharacterDetails
    override suspend fun characterDetailsSize(): Int = characters.size

    override suspend fun saveCharacterDetails(characterDetails: CharacterDetails) {
        this.characterDetails = characterDetails
    }

    override fun getCharacterDetails(characterId: Int): Flow<CharacterDetails> =
        flowOf(characterDetails)
    //endregion
}