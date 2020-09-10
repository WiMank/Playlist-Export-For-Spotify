package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.mapper.UserEntityMapper
import com.wimank.pbfs.mapper.UserMapper
import com.wimank.pbfs.rest.UserApi
import com.wimank.pbfs.room.dao.UserDao
import com.wimank.pbfs.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi,
    private val userEntityMapper: UserEntityMapper,
    private val userMapper: UserMapper,
) : UserRepository {

    override suspend fun loadNetworkUser(token: String) {
        val userResp = userApi.getUserData(token)
        saveResponse(userEntityMapper.map(userResp))
    }

    override suspend fun flowUser(): Flow<User> {
        return userDao.flowUser().map {
            userMapper.map(it)
        }
    }

    override suspend fun canUpdateProfile(): Boolean {
        val timeUpdate = userDao.getUpdateTime()
        return if (timeUpdate != null) {
            timeUpdate < System.currentTimeMillis()
        } else {
            true
        }
    }

    override suspend fun clearProfile() {
        userDao.clearUser()
    }

    /**
     * Save user data in database
     */
    private suspend fun saveResponse(userEntity: UserEntity) {
        userDao.clearAndInsertUser(userEntity)
    }
}
