package com.wimank.pbfs.domain.datasource.authentication

import com.wimank.pbfs.room.dao.AuthenticationDao
import javax.inject.Inject

class AuthenticationLocalDataSource @Inject constructor(private val authenticationDao: AuthenticationDao)
