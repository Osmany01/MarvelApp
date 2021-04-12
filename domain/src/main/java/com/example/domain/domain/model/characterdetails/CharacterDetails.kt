package com.example.domain.domain.model.characterdetails

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailCharacterDetails?
)