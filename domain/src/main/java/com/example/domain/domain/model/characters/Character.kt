package com.example.domain.domain.model.characters

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailCharacter?
)