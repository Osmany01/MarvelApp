package com.example.data.repositories.characters

import com.example.data.api.RemoteDataSource
import com.example.data.local.LocalDataSource
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


open class CharactersRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {

    companion object {

        private const val PAGE_THRESHOLD = 10
    }

    override  fun getCharacters(): Flow<List<Character>> = localDataSource.getCharacters()

    override suspend fun checkRequiredNewPage(lastVisible: Int) {
        val size = localDataSource.size()
        if (lastVisible >= size - PAGE_THRESHOLD) {

            val character = withTimeout(5_000) { remoteDataSource.getCharacters(size) }
            localDataSource.saveCharacters(character)
        }
    }
}