package com.karnilovich.daggertestapp.data.repo.impl

import com.karnilovich.daggertestapp.Constants.NULLABLE_USER_ID
import com.karnilovich.daggertestapp.data.repo.UserRepository
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val userInfoStore: UserInfoStore
    ) : UserRepository {

    override fun isUserLoggedIn(): Single<Boolean> {
        return userDataStore.getLoggedUserId()
            .map { loggedUserId -> loggedUserId != NULLABLE_USER_ID }
    }

    override fun getUserById(userId: Long): Single<User> {
            return userInfoStore.getUserById(userId)
    }

    override fun getLoggedUserId(): Single<Long> {
        return userDataStore.getLoggedUserId()
    }

}