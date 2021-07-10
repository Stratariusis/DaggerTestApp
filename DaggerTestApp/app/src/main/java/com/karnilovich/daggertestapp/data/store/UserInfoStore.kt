package com.karnilovich.daggertestapp.data.store

import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserInfoStore {

    fun getUserByLoginAndPassword(login: String, password: String): Single<User>

    fun registerNewUser(user: User): Completable

    fun getUserById(userId: Long): Single<User>

    fun updateUser(user: User): Completable

    fun getUserNotes(userId: Long): Single<List<Note>>

    fun addNotes(userId: Long, title: String, text: String): Completable
}