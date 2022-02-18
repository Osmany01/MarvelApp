package com.example.data.repositories.characterDetails

import com.example.data.api.RemoteDataSource
import com.example.data.local.LocalDataSource
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.repositories.CharacterDetailsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CharacterDetailsRepository {

    override suspend fun checkCharacterDetails(characterId: Int): Flow<CharacterDetails> {
        return withTimeout(5_000) {
            flowOf(remoteDataSource.getCharacter(characterId))
        }
    }
}