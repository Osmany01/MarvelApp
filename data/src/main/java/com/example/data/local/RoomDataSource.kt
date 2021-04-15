package com.example.data.local

import com.example.data.local.dao.MarvelDatabase
import com.example.data.toDomain
import com.example.data.toDomainCharacter
import com.example.data.toRoom
import com.example.data.toRoomCharacter
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character
import kotlinx.coroutines.flow.*

open class RoomDataSource(db: MarvelDatabase): LocalDataSource {

    private val marvelDao = db.marvelDao()

    //region Characters
    override suspend fun size(): Int = marvelDao.charactersCount()

    override suspend fun saveCharacters(character: List<Character>) {

        marvelDao.insertCharacter(character.map { it.toRoomCharacter() })
    }

    override fun getCharacters(): Flow<List<Character>> =
        marvelDao
            .getAll()
            .map { roomCharacter -> roomCharacter.map { it.toDomainCharacter() } }
    //endregion

    //region CharacterDetails
    override suspend fun characterDetailsSize(): Int = marvelDao.characterDetailsCount()

    override suspend fun saveCharacterDetails(characterDetails: CharacterDetails) {
        marvelDao.insertCharacterDetails(characterDetails.toRoom())
    }

    override fun getCharacterDetails(characterId: Int): Flow<CharacterDetails> =
        marvelDao.getCharacterDetails(characterId).map { it.toDomain() }
    //endregion
}