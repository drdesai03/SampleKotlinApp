package com.example.jsonholderandroidapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonholderandroidapp.di.scope.ViewModelKey
import com.example.jsonholderandroidapp.vm.CustomViewModelFactory
import com.example.jsonholderandroidapp.vm.album.AlbumDetailsViewModel
import com.example.jsonholderandroidapp.vm.album.AlbumViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    abstract fun bindAlbumViewModel(userViewModel: AlbumViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun bindAlbumDetailViewModel(userViewModel: AlbumDetailsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}