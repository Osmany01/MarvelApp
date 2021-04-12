package com.example.data

import com.example.data.api.services.characterdetails.ApiCharacterDetails
import com.example.data.api.services.characterdetails.ApiCharacterThumbnail
import com.example.data.api.services.characters.ApiCharacter
import com.example.data.api.services.characters.ApiThumbnail
import com.example.data.local.model.characterdetails.RoomCharacterDetails
import com.example.data.local.model.characterdetails.RoomCharacterDetailsThumbnail
import com.example.data.local.model.characters.RoomCharacter
import com.example.data.local.model.characters.RoomCharacterThumbnail
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characterdetails.ThumbnailCharacterDetails
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.model.characters.ThumbnailCharacter

fun ApiCharacter.toDomain(): Character =
    Character(
        id,
        name,
        description,
        thumbnail?.toDomain()
    )

fun Character.toRoomCharacter(): RoomCharacter =
    RoomCharacter(
        id,
        name,
        description,
        thumbnail?.toRoomThumbnail()
    )

fun RoomCharacter.toDomainCharacter(): Character =
    Character(
        id,
        name,
        description,
        thumbnail?.toDomain()
    )

fun ApiThumbnail.toDomain(): ThumbnailCharacter =
    ThumbnailCharacter(
        path,
        extension)

fun ThumbnailCharacter.toRoomThumbnail(): RoomCharacterThumbnail =
    RoomCharacterThumbnail(
        path,
        extension
    )

fun RoomCharacterThumbnail.toDomain(): ThumbnailCharacter =
    ThumbnailCharacter(
        path,
        extension
    )

fun ApiCharacterDetails.toDomain(): CharacterDetails =
    CharacterDetails(
        id,
        name,
        description,
        thumbnail?.toDomain()
    )

fun ApiCharacterThumbnail.toDomain(): ThumbnailCharacterDetails =
    ThumbnailCharacterDetails(
        path,
        extension
    )

fun CharacterDetails.toRoom(): RoomCharacterDetails =
    RoomCharacterDetails(
        id,
        name,
        description,
        thumbnail?.toRoom()
    )

fun ThumbnailCharacterDetails.toRoom(): RoomCharacterDetailsThumbnail =
    RoomCharacterDetailsThumbnail(
        path,
        extension
    )

fun RoomCharacterDetails.toDomain(): CharacterDetails =
    CharacterDetails(
        id,
        name,
        description,
        thumbnail?.toDomain()
    )

fun RoomCharacterDetailsThumbnail.toDomain(): ThumbnailCharacterDetails =
    ThumbnailCharacterDetails(
        path,
        extension
    )
