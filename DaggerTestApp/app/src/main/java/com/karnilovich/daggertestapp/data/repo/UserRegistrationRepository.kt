package com.karnilovich.daggertestapp.data.repo

import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

interface UserRegistrationRepository {

    fun registerNewUser(login: String, password: String): Single<User>

    fun acceptTosForUser(UserId: Long): Completable
}