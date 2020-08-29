package com.wimank.pbfs.repository

import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.entity.SessionEntity
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
) : SessionRepository {

    override suspend fun insertSession(session: SessionEntity) {
        sessionDao.insert(session)
    }

    override suspend fun updateSession(session: SessionEntity) {
        sessionDao.update(session)
    }

    override suspend fun deleteSession(session: SessionEntity) {
        sessionDao.delete(session)
    }

    override suspend fun getSession(): SessionEntity {
        return sessionDao.getCurrentSession()
    }

    override suspend fun hasSession(): Boolean {
        return sessionDao.hasSession()
    }
}
