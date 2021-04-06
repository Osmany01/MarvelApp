package com.example.data.api

import com.example.domain.domain.model.Character

interface RemoteDataSource {
    suspend fun getMovies(page: Int): List<Character>
}