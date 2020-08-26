package com.wimank.pbfs.domain.datasource.user

import com.wimank.pbfs.rest.UserApi
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userApi: UserApi)