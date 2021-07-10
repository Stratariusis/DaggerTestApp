package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserLoggedRepository
import com.karnilovich.daggertestapp.database.entity.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AddNoteInteractor @Inject constructor(private val userLoggedRepository: UserLoggedRepository) {

    fun execute(title: String, text: String): Completable {
        return userLoggedRepository.addNotes(title, text)
    }

}