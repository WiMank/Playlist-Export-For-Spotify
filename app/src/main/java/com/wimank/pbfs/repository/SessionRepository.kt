package com.wimank.pbfs.repository

import com.wimank.pbfs.room.entity.SessionEntity

interface SessionRepository {

    suspend fun insertSession(session: SessionEntity)

    suspend fun updateSession(session: SessionEntity)

    suspend fun deleteSession()

    suspend fun getSession(): SessionEntity

    suspend fun hasSession(): Boolean

}
