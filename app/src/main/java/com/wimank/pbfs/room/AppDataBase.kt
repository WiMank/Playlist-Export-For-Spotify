package com.wimank.pbfs.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.pbfs.room.dao.AuthenticationDao
import com.wimank.pbfs.room.dao.DbUserDao
import com.wimank.pbfs.room.entity.AuthenticationEntity
import com.wimank.pbfs.room.entity.DbUserEntity

@Database(
    entities = [
        AuthenticationEntity::class,
        DbUserEntity::class
    ],
    version = 0
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getAuthenticationDao(): AuthenticationDao

    abstract fun getDbUserDao(): DbUserDao

}
