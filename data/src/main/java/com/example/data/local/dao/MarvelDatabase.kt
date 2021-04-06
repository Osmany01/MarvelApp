package com.example.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.model.RoomCharacter

@Database(entities = [RoomCharacter::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun marvelDao(): MarvelDao
}