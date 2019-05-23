package com.example.jsonholderandroidapp.db.entity

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity (
    indices = [
        Index("albumId"),
        Index("id")],
    primaryKeys = ["id"],
    tableName = "album"
)
data class AlbumEntity(
    val id : Int,
    @field:SerializedName("albumId")
    val albumId : Int,
    @field:SerializedName("title")
    val title: String
)