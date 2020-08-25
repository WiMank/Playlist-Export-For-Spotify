package com.wimank.pbfs.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "authentication")
data class AuthenticationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo(name = "token")
    val token: String?

)
