package com.wimank.pbfs.repository

import com.wimank.pbfs.room.entity.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun insertSession(session: SessionEntity)

    suspend fun updateSession(session: SessionEntity)

    suspend fun deleteSession()

    suspend fun getSession(): SessionEntity

    suspend fun flowSession(): Flow<List<SessionEntity>>

    suspend fun hasSession(): Boolean

    suspend fun refreshToken(): SessionEntity

}
