package com.example.data.repositories.characterDetails

import com.example.data.api.RemoteDataSource
import com.example.data.local.LocalDataSource
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.repositories.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CharacterDetailsRepository {


    override suspend fun checkCharacterDetails(characterId: Int): Flow<CharacterDetails> {
        val size = localDataSource.characterDetailsSize()

        return if (size == 0) {
            val characterDetail = withTimeout(5_000) { remoteDataSource.getCharacter(characterId) }
            localDataSource.saveCharacterDetails(characterDetail)
            flowOf(characterDetail)
        } else localDataSource.getCharacterDetails(characterId)
    }
}