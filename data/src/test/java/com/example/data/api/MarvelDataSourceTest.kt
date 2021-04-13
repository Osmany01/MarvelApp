package com.example.data.api

import com.example.data.api.services.characters.ApiCharacter
import com.example.data.api.services.characters.ApiCharactersData
import com.example.data.api.services.characters.ApiCharactersResponse
import com.example.data.api.services.characters.ApiThumbnail
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MarvelDataSourceTest {

    private val fakeThanosThumbnail = ApiThumbnail("Thanospaht", "jpeg")
    private val fakePantherThumbnail = ApiThumbnail("Pantherpath", "jpeg")
    private val fakeIronManThumbnail = ApiThumbnail("IronManpath", "jpeg")

    private val fakeCharacters = listOf(
        ApiCharacter(1001, "Thanos", "this is thano", fakeThanosThumbnail),
        ApiCharacter(1002, "Panther", "this is Panther", fakePantherThumbnail),
        ApiCharacter(1003, "IronMan", "this is IronMan", fakeIronManThumbnail),
    )

    val fakeApiCharactersData = ApiCharactersData(
        3,
        3,
        1,
        fakeCharacters
    )

    val fakeApiCharacterResponse = ApiCharactersResponse(
        200,
        "completed",
        fakeApiCharactersData
    )
}