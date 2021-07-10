package com.karnilovich.daggertestapp.data.repo

import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single

interface UserLoginRepository {

    fun loginUser(login: String, password: String): Single<User>

}