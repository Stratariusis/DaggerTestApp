package com.karnilovich.daggertestapp.data.store

import com.karnilovich.daggertestapp.di.LoggedUserComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserDataStore {
    fun getLoggedUserId(): Single<Long>

    fun setLoggedUserId(userId: Long): Completable

    fun getLoggedUserComponent(): LoggedUserComponent?

    fun logOut()
}