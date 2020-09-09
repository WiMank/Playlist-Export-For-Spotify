package com.wimank.pbfs.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val name: String,
    val avatar: String,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
)
