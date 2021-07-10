package com.karnilovich.daggertestapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val userId: Long,
    val login: String,
    val password: String,
    val isTosAccepted: Boolean
)