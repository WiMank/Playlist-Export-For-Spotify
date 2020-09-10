package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.room.entity.SessionEntity
import kotlinx.coroutines.flow.Flow

/**
 * Manages user session, access token and refresh token.
 */
interface SessionRepository {

    /**
     * Insert session in database.
     */
    suspend fun insertSession(session: SessionEntity)

    /**
     * Update session in database.
     */
    suspend fun updateSession(session: SessionEntity)

    /**
     * Delete session in database.
     */
    suspend fun deleteSession()

    /**
     * Take session from database.
     */
    suspend fun getSession(): SessionEntity

    /**
     * Asynchronous data stream for session.
     */
    suspend fun flowSession(): Flow<List<Session>>

    /**
     * Checking for a session.
     */
    suspend fun hasSession(): Boolean

    /**
     * Save new refresh token.
     */
    suspend fun refreshToken(): SessionEntity

}
