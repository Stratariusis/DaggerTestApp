package com.karnilovich.daggertestapp.data.repo.impl

import com.karnilovich.daggertestapp.data.repo.UserLoggedRepository
import com.karnilovich.daggertestapp.data.store.UserDataStore
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.di.LoggedUserScope
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@LoggedUserScope
class UserLoggedRepositoryImpl @Inject constructor(
    val userDataStore: UserDataStore,
    val userInfoStore: UserInfoStore
) : UserLoggedRepository {

    override fun logOut() {
        userDataStore.logOut()
    }

    override fun getUserNotes(): Single<List<Note>> {
        return userDataStore.getLoggedUserId()
            .flatMap { userInfoStore.getUserNotes(it) }
    }

    override fun addNotes(title: String, text: String): Completable {
        return userDataStore.getLoggedUserId()
            .flatMapCompletable { userInfoStore.addNotes(it, title, text) }
    }

}