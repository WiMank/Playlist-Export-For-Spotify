package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.repository.UserRepository
import com.wimank.pbfs.util.AccessTokenException
import javax.inject.Inject

class UserManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) {

    suspend fun loadNetworkUser() {
        sessionManager.checkSessionBeforeRequest().run {
            if (isNotEmpty()) {
                TODO("loadNetworkUser")
            } else {
                throw AccessTokenException()
            }
        }
    }

    suspend fun loadLocalUser(): List<Track> {
        TODO("loadLocalUser")
    }
}
