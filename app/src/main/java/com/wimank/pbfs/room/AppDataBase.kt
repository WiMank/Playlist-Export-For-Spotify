package com.wimank.pbfs.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.dao.UserDao
import com.wimank.pbfs.room.entity.PlaylistsEntity
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.room.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PlaylistsEntity::class,
        SessionEntity::class,
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDbUserDao(): UserDao

    abstract fun getPlaylistDao(): PlaylistDao

    abstract fun getSessionDao(): SessionDao

}
