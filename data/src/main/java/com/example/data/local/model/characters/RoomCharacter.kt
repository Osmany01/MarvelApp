package com.example.data.local.model.characters

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacter(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    @Embedded(prefix = "image") val thumbnail: RoomCharacterThumbnail? = null
)