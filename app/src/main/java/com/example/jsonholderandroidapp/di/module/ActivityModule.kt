package com.example.jsonholderandroidapp.di.module

import com.example.jsonholderandroidapp.ui.album.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): HomeActivity
}