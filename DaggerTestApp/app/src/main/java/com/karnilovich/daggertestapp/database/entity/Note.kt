package com.karnilovich.daggertestapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey val noteId: Long,
    val creatorId: Long,
    val title: String,
    val text: String
)