package com.example.data.api.services.characterdetails

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsService {

    companion object {
        const val CHARACTER =  "/v1/public/characters/{characterId}"
    }

    @GET(CHARACTER)
    suspend fun getCharacter(@Path("characterId") characterId: Int): ApiCharacterDetailsResponse

}