package com.wimank.pbfs.repository

import com.wimank.pbfs.rest.UserApi
import com.wimank.pbfs.room.dao.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi

) : UserRepository {

    override fun loadUserProfile() {

    }

}
