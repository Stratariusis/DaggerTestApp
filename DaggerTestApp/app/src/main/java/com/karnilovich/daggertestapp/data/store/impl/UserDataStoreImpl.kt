package com.karnilovich.daggertestapp.data.store.impl

import android.content.Context
import android.content.SharedPreferences
import com.karnilovich.daggertestapp.Constants.NULLABLE_USER_ID
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.di.LoggedUserComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStoreImpl @Inject constructor(
    context: Context,
    private val loggedUserComponentFactory: LoggedUserComponent.Factory
): UserDataStore {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(APPLICATION_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    var userComponent: LoggedUserComponent? = null
        private set

    override fun getLoggedUserId(): Single<Long> {
        val userId = prefs.getLong(LOGGED_USER_ID, NULLABLE_USER_ID)
        updateComponent(userId != NULLABLE_USER_ID)
        return Single.just(userId)
    }

    override fun setLoggedUserId(userId: Long): Completable {
        return Single.just(userId)
            .flatMapCompletable {
                updateComponent(it != NULLABLE_USER_ID)
                with(prefs.edit()){
                    putLong(LOGGED_USER_ID, userId)
                    apply()
                }
                return@flatMapCompletable Completable.complete()
            }
    }

    override fun getLoggedUserComponent(): LoggedUserComponent? {
        return userComponent
    }

    override fun logOut() {
        setLoggedUserId(NULLABLE_USER_ID)
    }

    private fun updateComponent(isInit: Boolean) {
        userComponent = if (isInit) {
            loggedUserComponentFactory.create()
        } else {
            null
        }
    }

    companion object {
        private const val APPLICATION_PREFERENCES_NAME = "APPLICATION_PREFERENCES_NAME"
        private const val LOGGED_USER_ID = "LOGGED_USER_ID"
    }
}