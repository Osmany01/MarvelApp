package com.example.data.api.services.characters

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CharactersService {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
    }

    @GET(CHARACTERS)
    suspend fun getCharacters(@Query("offset")offset: Int): ApiCharactersResponse
}