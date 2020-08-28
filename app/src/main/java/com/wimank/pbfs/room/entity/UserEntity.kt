package com.wimank.pbfs.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val name: String,
    val avatar: String,
    val isPremium: Boolean
)
