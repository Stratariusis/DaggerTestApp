package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserRegistrationRepository
import com.karnilovich.daggertestapp.data.repo.UserRepository
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AcceptTosInteractor @Inject constructor(
    private val userRegistrationRepository: UserRegistrationRepository
) {

    fun execute(userId: Long): Completable {
        return userRegistrationRepository.acceptTosForUser(userId)
    }

}