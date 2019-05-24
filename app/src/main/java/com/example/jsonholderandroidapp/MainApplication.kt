package com.example.jsonholderandroidapp

import android.app.Activity
import android.app.Application
import android.util.Log
import com.example.jsonholderandroidapp.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Log.d(MainApplication::class.simpleName, "Application is been created")
//
//        DaggerApplicationComponent
//            .builder()
//            .applicationContext(this)
//            .build()
//            .inject(this)

        AppInjector.init(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector


}
