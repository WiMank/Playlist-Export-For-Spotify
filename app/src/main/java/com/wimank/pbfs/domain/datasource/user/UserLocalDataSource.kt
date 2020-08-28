package com.wimank.pbfs.domain.datasource.user

import com.wimank.pbfs.room.dao.UserDao
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao)
