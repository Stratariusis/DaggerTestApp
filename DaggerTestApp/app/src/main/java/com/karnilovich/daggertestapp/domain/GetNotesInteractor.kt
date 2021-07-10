package com.karnilovich.daggertestapp.domain

import com.karnilovich.daggertestapp.data.repo.UserLoggedRepository
import com.karnilovich.daggertestapp.database.entity.Note
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNotesInteractor @Inject constructor(private val userLoggedRepository: UserLoggedRepository) {

    fun execute(): Single<List<Note>> {
        return userLoggedRepository.getUserNotes()
    }

}