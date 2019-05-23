package com.example.jsonholderandroidapp.vm.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonholderandroidapp.db.entity.AlbumEntity
import com.example.jsonholderandroidapp.helper.ResponseResources
import com.example.jsonholderandroidapp.repository.album.AlbumRepository
import javax.inject.Inject

class AlbumViewModel @Inject constructor(val albumRepository: AlbumRepository) : ViewModel() {

    private val _result: MutableLiveData<List<AlbumEntity>> = MutableLiveData();

    open fun getResult(): LiveData<ResponseResources<List<AlbumEntity>>> {
        return albumRepository.fetchAllAlbums()
    }
}