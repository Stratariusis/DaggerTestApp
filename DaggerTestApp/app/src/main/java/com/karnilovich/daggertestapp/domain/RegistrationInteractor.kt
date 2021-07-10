package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserRegistrationRepository
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegistrationInteractor @Inject constructor(private val userRegistrationRepository: UserRegistrationRepository) {

    fun execute(login: String, password: String): Single<User> {
        return userRegistrationRepository.registerNewUser(login, password)
    }

}