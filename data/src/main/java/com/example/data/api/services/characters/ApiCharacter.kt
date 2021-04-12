package com.example.data.api.services.characters

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ApiThumbnail?
)