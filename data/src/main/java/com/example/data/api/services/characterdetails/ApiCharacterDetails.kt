package com.example.data.api.services.characterdetails

data class ApiCharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ApiCharacterThumbnail?
)