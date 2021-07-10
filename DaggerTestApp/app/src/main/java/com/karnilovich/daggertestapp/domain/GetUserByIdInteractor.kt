package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserRepository
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserByIdInteractor @Inject constructor(
    private val userRepository: UserRepository
) {

    fun execute(userId: Long): Single<User> {
        return userRepository.getUserById(userId)
    }

}