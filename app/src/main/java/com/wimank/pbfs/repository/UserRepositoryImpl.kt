package com.wimank.pbfs.repository

import com.wimank.pbfs.room.AppDataBase
import com.wimank.pbfs.room.dao.DbUserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(roomDatabase: AppDataBase) : UserRepository {

    private val userDao: DbUserDao = roomDatabase.getDbUserDao()


    override fun loadUserProfile() {

    }

}
