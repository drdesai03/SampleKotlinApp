package com.example.jsonholderandroidapp.repository.album

import androidx.lifecycle.LiveData
import com.example.jsonholderandroidapp.db.MainDb
import com.example.jsonholderandroidapp.db.dao.AlbumDao
import com.example.jsonholderandroidapp.db.entity.AlbumEntity
import com.example.jsonholderandroidapp.helper.ResponseResources
import com.example.jsonholderandroidapp.network.album.AlbumService
import com.example.jsonholderandroidapp.network.support.NetwokBound
import com.example.jsonholderandroidapp.network.support.RequestBuilder
import com.example.jsonholderandroidapp.network.support.SaveResultCallBack
import com.example.jsonholderandroidapp.utils.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject constructor(
    private val executors: AppExecutors,
    private val db: MainDb,
    private val albumDao: AlbumDao,
    private val albumService: AlbumService
) {

    fun fetchAllAlbums(): LiveData<ResponseResources<List<AlbumEntity>>> {
        val requestBuilder = RequestBuilder(
            keyword = "album",
            executors = executors,
            dbSource = albumDao.getAllAlbum(),
            apiResponse = albumService.getAllAlbums()
        )

        val networkBuilder =
            NetwokBound.createNetworkBound<List<AlbumEntity>, List<AlbumEntity>>(
                requestBuilder,
                object : SaveResultCallBack<List<AlbumEntity>> {
                    override fun saveResult(result: List<AlbumEntity>) {
                        db.runInTransaction {
                            albumDao.insertAllAlbums(result)
                        }
                    }
                })
        return networkBuilder.asLiveData()
    }
}