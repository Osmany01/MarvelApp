package com.example.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.model.characterdetails.RoomCharacterDetails
import com.example.data.local.model.characters.RoomCharacter

@Database(entities = [RoomCharacter::class, RoomCharacterDetails::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun marvelDao(): MarvelDao
}