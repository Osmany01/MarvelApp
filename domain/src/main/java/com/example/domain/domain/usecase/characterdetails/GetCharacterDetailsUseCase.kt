package com.example.domain.domain.usecase.characterdetails

import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.repositories.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailsUseCase(private val repo: CharacterDetailsRepository) {

    fun getCharacterDetails(characterId: Int): Flow<CharacterDetails> = repo.getCharacterDetails(characterId)

    suspend fun checkCharacterDetails(characterId: Int) = repo.checkCharacterDetails(characterId)
}