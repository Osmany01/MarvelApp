package com.example.domain.domain.repositories

import com.example.domain.domain.model.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<List<Character>>

    suspend fun checkRequiredNewPage(lastVisible: Int)
}