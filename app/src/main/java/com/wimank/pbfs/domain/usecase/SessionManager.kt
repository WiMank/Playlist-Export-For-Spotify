package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.room.entity.SessionEntity
import javax.inject.Inject

class SessionManager @Inject constructor(private val sessionRepository: SessionRepository) {

    suspend fun hasSession(): Boolean {
        return with(sessionRepository.getSession()) {
            accessToken.isNotEmpty() && (refreshToken.isNotEmpty())
        }
    }

    suspend fun checkSessionBeforeRequest(): Boolean {
        if (sessionRepository.hasSession())
            if (checkAccessTokenExpire()) {
                writeSession(sessionRepository.refreshToken())
                return true
            }
        return false
    }

    suspend fun writeSession(session: SessionEntity) {
        if (sessionRepository.hasSession()) {
            sessionRepository.updateSession(session)
        } else {
            sessionRepository.insertSession(session)
        }
    }

    suspend fun clearSession() {
        sessionRepository.deleteSession()
    }

    private suspend fun checkAccessTokenExpire(): Boolean {
        return sessionRepository.getSession().expiresIn < System.currentTimeMillis()
    }

}
