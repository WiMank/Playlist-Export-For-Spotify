package com.wimank.pbfs.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class SessionEntity(

    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: Long,

    @ColumnInfo(name = "access_token")
    val accessToken: String,

    @ColumnInfo(name = "token_type")
    val tokenType: String,

    @ColumnInfo(name = "expires_in")
    val expiresIn: Long,

    @ColumnInfo(name = "refresh_token")
    val refreshToken: String,

    @ColumnInfo(name = "scope")
    val scope: String

)
