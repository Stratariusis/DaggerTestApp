package com.karnilovich.daggertestapp.data.repo

import com.karnilovich.daggertestapp.database.entity.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserLoggedRepository {

    fun logOut()

    fun getUserNotes(): Single<List<Note>>

    fun addNotes(title: String, text: String): Completable

}