package com.example.jsonholderandroidapp.network.album

import androidx.lifecycle.LiveData
import com.example.jsonholderandroidapp.db.entity.AlbumEntity
import com.example.jsonholderandroidapp.helper.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("/albums")
    fun getAllAlbums(): LiveData<ApiResponse<List<AlbumEntity>>>

    @GET("/albums/{albumId}")
    fun getAlbumDetails(@Path("albumId") albumid : Int) : LiveData<ApiResponse<AlbumEntity>>
 }