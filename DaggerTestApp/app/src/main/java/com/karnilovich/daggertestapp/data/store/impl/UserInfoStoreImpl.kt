package com.karnilovich.daggertestapp.data.store.impl

import android.content.Context
import androidx.room.Room
import com.karnilovich.daggertestapp.data.store.UserInfoStore
import com.karnilovich.daggertestapp.data.store.throwable.NoSuchUserException
import com.karnilovich.daggertestapp.database.AppDatabase
import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.database.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class UserInfoStoreImpl @Inject constructor(context: Context) : UserInfoStore {

    private val db by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }

    override fun getUserByLoginAndPassword(login: String, password: String): Single<User> {
        return db.userDao().loadUserByLoginAndPassword(login, password)
            .flatMap {
                if (it.isNotEmpty()) {
                    Single.just(it[0])
                } else {
                    throw NoSuchUserException()
                }
            }
    }

    override fun registerNewUser(user: User): Completable {
        return db.userDao().insertUsers(listOf(user))
    }

    override fun getUserById(userId: Long): Single<User> {
        return db.userDao().loadUserById(userId)
    }

    override fun updateUser(user: User): Completable {
        return db.userDao().updateUsers(listOf(user))
    }

    override fun getUserNotes(userId: Long): Single<List<Note>> {
        return db.userDao().getUsersWithNotes(userId)
            .flatMap { Single.just(it.notes) }
    }

    override fun addNotes(userId: Long, title: String, text: String): Completable {
        return db.noteDao().insertNotes(
            listOf(
                Note(
                    System.currentTimeMillis(),
                    userId,
                    title,
                    text
                )
            )
        )
    }

}