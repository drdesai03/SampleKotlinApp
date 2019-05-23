package com.example.jsonholderandroidapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonholderandroidapp.db.entity.AlbumEntity

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album ORDER BY title ASC")
    fun getAllAlbum(): LiveData<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(repositories: List<AlbumEntity>)
}