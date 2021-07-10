package com.karnilovich.daggertestapp.database.dao

import androidx.room.*
import com.karnilovich.daggertestapp.database.entity.User
import com.karnilovich.daggertestapp.database.entity.UserWithNotes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>): Completable

    @Update
    fun updateUsers(users: List<User>): Completable

    @Delete
    fun deleteUsers(users: List<User>): Completable

    @Query("SELECT * FROM user WHERE userId = :id")
    fun loadUserById(id: Long): Single<User>

    @Transaction
    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUsersWithNotes(id: Long): Single<UserWithNotes>

    @Query("SELECT * FROM user WHERE login = :login AND password = :password")
    fun loadUserByLoginAndPassword(login: String, password: String): Single<List<User>>
}