package com.example.jsonholderandroidapp.vm.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jsonholderandroidapp.db.entity.AlbumEntity
import com.example.jsonholderandroidapp.helper.ResponseResources
import com.example.jsonholderandroidapp.repository.album.AlbumRepository
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(val albumRepository: AlbumRepository) : ViewModel() {

    fun getAlbumDetails(albumId: Int): LiveData<ResponseResources<AlbumEntity>> {
        return albumRepository.fetchSingleResponse(albumId)
    }
}