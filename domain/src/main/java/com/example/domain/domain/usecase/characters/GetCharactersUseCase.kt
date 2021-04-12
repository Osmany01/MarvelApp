package com.example.domain.domain.usecase.characters

import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repo: CharactersRepository) {

    fun getCharacters(): Flow<List<Character>> = repo.getCharacters()

    suspend fun checkRequiredNewPage(lastVisible: Int) = repo.checkRequiredNewPage(lastVisible)
}