package com.wimank.pbfs.repository

import com.wimank.pbfs.domain.datasource.session.SessionLocalDataSource
import com.wimank.pbfs.room.entity.SessionEntity
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource
) : SessionRepository {

    override suspend fun insertSession(session: SessionEntity) {
        sessionLocalDataSource.insertSession(session)
    }

    override suspend fun updateSession(session: SessionEntity) {
        sessionLocalDataSource.updateSession(session)
    }

    override suspend fun deleteSession(session: SessionEntity) {
        sessionLocalDataSource.deleteSession(session)
    }

    override suspend fun getSession(): SessionEntity {
        return sessionLocalDataSource.getSession()
    }

}
