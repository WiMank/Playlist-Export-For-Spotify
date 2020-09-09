package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

   suspend fun loadNetworkUser(token: String)
   suspend fun flowUser(): Flow<User>
   suspend fun canUpdateProfile(): Boolean
   suspend fun clearProfile()

}
