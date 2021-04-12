package com.example.data.local.model.characterdetails

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacterDetails(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    @Embedded(prefix = "image") val thumbnail: RoomCharacterDetailsThumbnail? = null
)