package com.example.data.repositories.characterDetails

import com.example.data.api.RemoteDataSource
import com.example.data.local.LocalDataSource
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.repositories.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource,
                                                         private val remoteDataSource: RemoteDataSource
): CharacterDetailsRepository {
    override fun getCharacterDetails(characterId: Int): Flow<CharacterDetails> {

        return localDataSource.getCharacterDetails(characterId)
    }


    override suspend fun checkCharacterDetails(characterId: Int) {
        val size = localDataSource.characterDetailsSize()
        if (size == 0) {

            val characterDetail = withTimeout(5_000) { remoteDataSource.getCharacter(characterId) }
            localDataSource.saveCharacterDetails(characterDetail)
        }
    }
}