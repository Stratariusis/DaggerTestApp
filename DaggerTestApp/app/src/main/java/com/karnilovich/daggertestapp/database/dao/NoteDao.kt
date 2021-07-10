package com.karnilovich.daggertestapp.database.dao

import androidx.room.*
import com.karnilovich.daggertestapp.database.entity.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: List<Note>): Completable

    @Update
    fun updateNotes(notes: List<Note>): Completable

    @Delete
    fun deleteNotes(notes: List<Note>): Completable

    @Query("SELECT * FROM note WHERE noteId = :id")
    fun loadNoteById(id: Int): Single<Note?>?

    @Query("SELECT * FROM note WHERE creatorId = :id")
    fun loadNotesByUserId(id: Int): Single<List<Note>>

}