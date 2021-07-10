package com.karnilovich.daggertestapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karnilovich.daggertestapp.database.dao.NoteDao
import com.karnilovich.daggertestapp.database.dao.UserDao
import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.database.entity.User

@Database(entities = [User::class, Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao
}