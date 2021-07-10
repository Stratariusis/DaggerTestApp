package com.karnilovich.daggertestapp.di

import android.content.Context
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.view.activity.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserStorageModule::class, AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun userDataStore(): UserDataStore

    fun logInComponent(): LogInComponent.Factory

    fun registrationComponent(): RegistrationComponent.Factory

    fun inject(activity: SplashActivity)
}