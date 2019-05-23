package com.example.jsonholderandroidapp.di.component

import android.app.Application
import com.example.jsonholderandroidapp.MainApplication
import com.example.jsonholderandroidapp.di.module.MainModule
import com.example.jsonholderandroidapp.di.module.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        ActivityModule::class
    ]
)
interface MainComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }

    fun inject(application: MainApplication)
}