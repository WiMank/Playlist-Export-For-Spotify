package com.wimank.pbfs.domain.datasource.session

import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.entity.SessionEntity
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor(private val sessionDao: SessionDao) {

    suspend fun insertSession(session: SessionEntity) {
        sessionDao.insert(session)
    }

    suspend fun updateSession(session: SessionEntity) {
        sessionDao.update(session)
    }

    suspend fun deleteSession(session: SessionEntity) {
        sessionDao.delete(session)
    }

    suspend fun getSession(): SessionEntity {
        return sessionDao.getCurrentSession()
    }

}
