package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.data.repo.UserRepository
import com.karnilovich.daggertestapp.data.repo.impl.UserRepositoryImpl
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.data.store.impl.UserDataStoreImpl
import com.karnilovich.daggertestapp.data.store.impl.UserInfoStoreImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserStorageModule {

    @Binds
    abstract fun provideUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideUserDataStore(dataStore: UserDataStoreImpl): UserDataStore

    @Binds
    abstract fun provideUserInfoStore(dataStore: UserInfoStoreImpl): UserInfoStore

}