package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.EMPTY_STRING
import javax.inject.Inject

class SessionManager @Inject constructor(private val sessionRepository: SessionRepository) {

    suspend fun hasSession(): Boolean {
        return with(sessionRepository.getSession()) {
            accessToken.isNotEmpty() && (refreshToken.isNotEmpty())
        }
    }

    suspend fun getSession(): SessionEntity {
        return sessionRepository.getSession()
    }

    suspend fun checkSessionBeforeRequest(): String {
        if (sessionRepository.hasSession()) {
            return if (checkAccessTokenNotExpired()) {
                sessionRepository.getSession().accessToken
            } else {
                sessionRepository.refreshToken().run {
                    writeSession(this)
                    accessToken
                }
            }
        }
        return EMPTY_STRING
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

    private suspend fun checkAccessTokenNotExpired(): Boolean {
        return sessionRepository.getSession().expiresIn > System.currentTimeMillis()
    }
}