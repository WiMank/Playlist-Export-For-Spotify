package com.wimank.pbfs.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.dao.TracksDao
import com.wimank.pbfs.room.dao.UserDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.room.entity.TracksEntity
import com.wimank.pbfs.room.entity.UserEntity

/**
 * Application database, which stores data playlists, tracks, and current user.
 * */
@Database(
    entities = [
        UserEntity::class,
        PlaylistsEntity::class,
        SessionEntity::class,
        TracksEntity::class
    ],
    version = 3
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPlaylistDao(): PlaylistDao

    abstract fun getSessionDao(): SessionDao

    abstract fun getTracksDao(): TracksDao

}
