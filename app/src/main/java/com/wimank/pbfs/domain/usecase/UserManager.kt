package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.repository.UserRepository
import com.wimank.pbfs.util.AccessTokenException
import com.wimank.pbfs.util.bearer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) {

    suspend fun loadUser(): Flow<User> {
        if (userRepository.canUpdateProfile()) {
            sessionManager.checkSessionBeforeRequest().run {
                if (isNotEmpty()) {
                    userRepository.loadNetworkUser(bearer())
                } else {
                    throw AccessTokenException()
                }
            }
        }
        return userRepository.flowUser()
    }

    suspend fun logout() {
        sessionManager.clearSession()
        userRepository.clearProfile()
    }
}
