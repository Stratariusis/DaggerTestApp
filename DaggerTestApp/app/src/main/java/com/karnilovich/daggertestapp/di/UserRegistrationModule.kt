package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.data.repo.UserRegistrationRepository
import com.karnilovich.daggertestapp.data.repo.impl.UserRegistrationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserRegistrationModule {

    @Binds
    abstract fun provideRegistrationRepository(repository: UserRegistrationRepositoryImpl): UserRegistrationRepository
}