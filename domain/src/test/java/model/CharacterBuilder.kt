package model

import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.model.characters.ThumbnailCharacter
import com.nhaarman.mockito_kotlin.description

internal fun aCharacter(): Character {
    return Character(
        id = 1,
        name = "name",
        description = "",
        thumbnail = null
    )
}


internal fun aCharacterList(): List<Character> {
    return listOf(aCharacter())
}

internal fun aCharacterDetails() =
    CharacterDetails(
        id = 1,
        name = "name",
        description = "description",
        thumbnail = null
    )

