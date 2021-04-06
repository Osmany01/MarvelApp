package com.example.data.api.services.characters

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CharactersService {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
        const val OFFSET = 20
        const val LIMIT = 50
    }

    @GET(CHARACTERS)
    suspend fun getCharacters(@Query("offset")offset: Int): ApiCharactersResponse
}