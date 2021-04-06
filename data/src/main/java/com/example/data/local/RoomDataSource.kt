package com.example.data.local

import com.example.data.local.dao.MarvelDatabase
import com.example.data.toDomainCharacter
import com.example.data.toRoomCharacter
import com.example.domain.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(db: MarvelDatabase): LocalDataSource {

    private val marvelDao = db.marvelDao()

    override suspend fun size(): Int = marvelDao.charactersCount()

    override suspend fun saveCharacters(character: List<Character>) {

        marvelDao.insertCharacter(character.map { it.toRoomCharacter() })
    }

    override fun getCharacters(): Flow<List<Character>> =
        marvelDao
            .getAll()
            .map { roomCharacter -> roomCharacter.map { it.toDomainCharacter() } }
}