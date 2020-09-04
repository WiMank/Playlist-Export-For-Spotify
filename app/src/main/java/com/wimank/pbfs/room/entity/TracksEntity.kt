package com.wimank.pbfs.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "tracks",
    primaryKeys = ["track_id"],
    foreignKeys = [
        ForeignKey(
            entity = PlaylistsEntity::class,
            parentColumns = ["playlist_id"],
            childColumns = ["playlist_track"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TracksEntity(
    @ColumnInfo(name = "track_id")
    val trackId: String,
    @ColumnInfo(name = "playlist_track")
    val playlistTrack: String,
    val url: String,
    val name: String,
    val artists: String,
    val image: String
)
