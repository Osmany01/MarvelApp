package com.example.data.local

import com.example.domain.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun size(): Int
    suspend fun saveCharacters(character: List<Character>)
    fun getCharacters(): Flow<List<Character>>
}