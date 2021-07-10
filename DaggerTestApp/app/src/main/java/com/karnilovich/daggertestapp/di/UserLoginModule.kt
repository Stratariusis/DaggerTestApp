package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.data.repo.UserLoginRepository
import com.karnilovich.daggertestapp.data.repo.UserRepository
import com.karnilovich.daggertestapp.data.repo.impl.UserLoginRepositoryImpl
import com.karnilovich.daggertestapp.data.repo.impl.UserRepositoryImpl
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.data.store.impl.UserDataStoreImpl
import com.karnilovich.daggertestapp.data.store.impl.UserInfoStoreImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserLoginModule {

    @Binds
    abstract fun provideLoginRepository(repository: UserLoginRepositoryImpl): UserLoginRepository
}