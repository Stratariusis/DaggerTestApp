package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetLoggedUserIdInteractor @Inject constructor(
    private val userRepository: UserRepository
) {

    fun execute(): Single<Long> {
        return userRepository.getLoggedUserId()
    }

}