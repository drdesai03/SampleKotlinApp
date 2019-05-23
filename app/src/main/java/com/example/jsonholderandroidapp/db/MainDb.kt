package com.example.jsonholderandroidapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jsonholderandroidapp.db.dao.AlbumDao
import com.example.jsonholderandroidapp.db.entity.AlbumEntity

@Database(
    entities = [
        AlbumEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MainDb : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
}