package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserLoginRepository
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val userLoginRepository: UserLoginRepository) {

    fun execute(login: String, password: String): Single<User> {
        return userLoginRepository.loginUser(login, password)
    }

}