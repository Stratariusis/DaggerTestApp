package com.karnilovich.daggertestapp

import android.app.Application
import com.karnilovich.daggertestapp.di.AppComponent
import com.karnilovich.daggertestapp.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}