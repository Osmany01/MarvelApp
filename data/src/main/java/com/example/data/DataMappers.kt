package com.example.data

import com.example.data.api.services.characters.ApiCharacter
import com.example.data.api.services.characters.ApiThumbnail
import com.example.data.local.model.RoomCharacter
import com.example.data.local.model.RoomThumbnail
import com.example.domain.domain.model.Character
import com.example.domain.domain.model.Thumbnail

fun ApiCharacter.toDomain(): Character =
    Character(
        id,
        name,
        description,
        apiThumbnail?.toDomain()
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
        roomThumbnail?.toDomain()
    )

fun ApiThumbnail.toDomain(): Thumbnail =
    Thumbnail(
        path,
        extension)

fun Thumbnail.toRoomThumbnail(): RoomThumbnail =
    RoomThumbnail(
        path,
        extension
    )

fun RoomThumbnail.toDomain(): Thumbnail =
    Thumbnail(
        path,
        extension
    )