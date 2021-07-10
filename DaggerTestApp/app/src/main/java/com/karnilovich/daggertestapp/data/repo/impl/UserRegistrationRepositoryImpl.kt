package com.karnilovich.daggertestapp.data.repo.impl

import com.karnilovich.daggertestapp.data.repo.UserRegistrationRepository
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.database.entity.User
import com.karnilovich.daggertestapp.di.RegistrationScope
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@RegistrationScope
class UserRegistrationRepositoryImpl @Inject constructor(
    private val userInfoStore: UserInfoStore,
    private val userDataStore: UserDataStore
) : UserRegistrationRepository {

    override fun registerNewUser(login: String, password: String): Single<User> {
        return Single.just(
            User(
                System.currentTimeMillis(),
                login,
                password,
                false
            )
        )
            .flatMap {
                userInfoStore.registerNewUser(it)
                    .andThen(Single.just(it))
            }
            .flatMap {
                userDataStore.setLoggedUserId(it.userId)
                    .andThen(Single.just(it))
            }
    }

    override fun acceptTosForUser(userId: Long): Completable {
        return userInfoStore.getUserById(userId)
            .flatMapCompletable {
                userInfoStore.updateUser(
                    User(
                        it.userId,
                        it.login,
                        it.password,
                        true
                    )
                )
            }
    }

}