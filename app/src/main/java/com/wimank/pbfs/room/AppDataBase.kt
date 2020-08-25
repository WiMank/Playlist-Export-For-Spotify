package com.wimank.pbfs.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.pbfs.room.dao.AuthenticationDao
import com.wimank.pbfs.room.entity.AuthenticationEntity

@Database(entities = [AuthenticationEntity::class], version = 0)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getAuthenticationDao(): AuthenticationDao

}
