package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Manages user data.
 */
interface UserRepository {

    /**
     * Loads user data from web.
     */
    suspend fun loadNetworkUser(token: String)

    /**
     * Asynchronous data stream for user.
     */
    suspend fun flowUser(): Flow<User>

    /**
     * Checking the time when the user's data was updated. Protects against frequent API calls.
     */
    suspend fun canUpdateProfile(): Boolean

    /**
     * Delete all user data.
     */
    suspend fun clearProfile()

}
