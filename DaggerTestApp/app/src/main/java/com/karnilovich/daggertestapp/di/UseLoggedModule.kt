package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.data.repo.UserLoggedRepository
import com.karnilovich.daggertestapp.data.repo.impl.UserLoggedRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseLoggedModule {

    @Binds
    abstract fun provideUserLoggedRepository(repository: UserLoggedRepositoryImpl): UserLoggedRepository

}