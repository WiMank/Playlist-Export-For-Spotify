package com.wimank.pbfs.domain.usecase

import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Session manager. Checking, deleting, and updating tokens.
 */
class SessionManager @Inject constructor(private val sessionRepository: SessionRepository) {

    /**
     * Checking for a session.
     */
    suspend fun hasSession(): Boolean {
        return with(sessionRepository.getSession()) {
            accessToken.isNotEmpty() && (refreshToken.isNotEmpty())
        }
    }

    /**
     * Observe to session update.
     */
    suspend fun flowSession(): Flow<List<Session>> {
        return sessionRepository.flowSession()
    }

    /**
     * Verification of the access token.
     */
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

    /**
     * Save session in database.
     */
    suspend fun writeSession(session: SessionEntity) {
        if (sessionRepository.hasSession()) {
            sessionRepository.updateSession(session)
        } else {
            sessionRepository.insertSession(session)
        }
    }

    /**
     * Deleting session.
     */
    suspend fun clearSession() {
        sessionRepository.deleteSession()
    }

    /**
     * Checking the token age.
     */
    private suspend fun checkAccessTokenNotExpired(): Boolean {
        return sessionRepository.getSession().expiresIn > System.currentTimeMillis()
    }
}
