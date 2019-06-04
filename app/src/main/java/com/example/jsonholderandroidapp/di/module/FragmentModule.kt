package com.example.jsonholderandroidapp.di.module

import com.example.jsonholderandroidapp.ui.album.fragment.AlbumDetailsFragment
import com.example.jsonholderandroidapp.ui.album.fragment.AlbumFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAlbumFrgament(): AlbumFragment

    @ContributesAndroidInjector
    abstract fun contributeAlbumDetails() : AlbumDetailsFragment
}