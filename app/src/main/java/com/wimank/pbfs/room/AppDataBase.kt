package com.wimank.pbfs.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.pbfs.room.dao.DbUserDao
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.entity.DbPlaylistsEntity
import com.wimank.pbfs.room.entity.DbUserEntity

@Database(
    entities = [
        DbUserEntity::class,
        DbPlaylistsEntity::class,
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDbUserDao(): DbUserDao

    abstract fun getPlaylistDao(): PlaylistDao

}
