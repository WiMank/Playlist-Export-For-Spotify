package com.wimank.pbfs.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class DbPlaylistsEntity(

    @PrimaryKey
    @ColumnInfo(name = "playlist_id")
    val playlistId: String,

    @ColumnInfo(name = "playlist_name")
    val playlistName: String,

    @ColumnInfo(name = "playlist_image")
    val playlistImage: String,

    @ColumnInfo(name = "tracks_url")
    val tracksUrl: String,

    @ColumnInfo(name = "is_public")
    val isPublic: Boolean,

    @ColumnInfo(name = "is_collaborative")
    val isCollaborative: Boolean

)
