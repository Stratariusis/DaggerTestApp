package com.karnilovich.daggertestapp.data.repo

import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun isUserLoggedIn(): Single<Boolean>

    fun getUserById(userId: Long): Single<User>

    fun getLoggedUserId(): Single<Long>
}