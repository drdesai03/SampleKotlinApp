package com.example.jsonholderandroidapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.jsonholderandroidapp.db.MainDb
import com.example.jsonholderandroidapp.db.dao.AlbumDao
import com.example.jsonholderandroidapp.helper.LiveDataCallAdapterFactory
import com.example.jsonholderandroidapp.network.album.AlbumService
import com.example.jsonholderandroidapp.utils.AppExecutors
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class MainModule {

    private val _dbName = "jsonholder.db"

//    @Singleton
//    @Provides
//    fun getDatabase(application: MainApplication): MainDb {
//        return Room.databaseBuilder(application, MainDb::class.java, _dbName)
//            .fallbackToDestructiveMigration().build()
//    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MainDb {
        return Room
            .databaseBuilder(app, MainDb::class.java, "jsonholder.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(db: MainDb): AlbumDao {
        return db.albumDao()
    }

    @Singleton
    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun albumService(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }
}