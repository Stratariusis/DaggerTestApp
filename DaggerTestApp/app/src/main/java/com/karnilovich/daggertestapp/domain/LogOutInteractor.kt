package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserLoggedRepository
import javax.inject.Inject

class LogOutInteractor @Inject constructor(private val userLoggedRepository: UserLoggedRepository) {

    fun execute(){
        userLoggedRepository.logOut()
    }

}