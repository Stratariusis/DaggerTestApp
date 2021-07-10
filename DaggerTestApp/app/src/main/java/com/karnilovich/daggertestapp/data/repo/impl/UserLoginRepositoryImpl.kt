package com.karnilovich.daggertestapp.data.repo.impl

import com.karnilovich.daggertestapp.data.repo.UserLoginRepository
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.database.entity.User
import com.karnilovich.daggertestapp.di.LogInScope
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@LogInScope
class UserLoginRepositoryImpl @Inject constructor(
    private val userInfoStore: UserInfoStore,
    private val userDataStore: UserDataStore
) : UserLoginRepository {

    override fun loginUser(login: String, password: String): Single<User> {
        return userInfoStore.getUserByLoginAndPassword(login, password)
            .flatMap {
                userDataStore.setLoggedUserId(it.userId)
                    .andThen(Single.just(it))
            }
    }

}