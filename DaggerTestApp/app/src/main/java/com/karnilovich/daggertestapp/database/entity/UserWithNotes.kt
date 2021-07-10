package com.karnilovich.daggertestapp.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithNotes (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "creatorId"
    )
    val notes: List<Note>
)