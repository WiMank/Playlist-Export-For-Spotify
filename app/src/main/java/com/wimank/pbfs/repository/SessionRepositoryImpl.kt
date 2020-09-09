package com.wimank.pbfs.repository

import com.wimank.pbfs.CLIENT_ID
import com.wimank.pbfs.domain.model.Session
import com.wimank.pbfs.mapper.SessionEntityMapper
import com.wimank.pbfs.rest.ApiRefreshToken
import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.REFRESH_TOKEN_GRANT_TYPE
import com.wimank.pbfs.util.SESSION_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao,
    private val apiRefreshToken: ApiRefreshToken,
    private val sessionEntityMapper: SessionEntityMapper
) : SessionRepository {

    override suspend fun insertSession(session: SessionEntity) {
        sessionDao.insert(session)
    }

    override suspend fun updateSession(session: SessionEntity) {
        sessionDao.update(session)
    }

    override suspend fun deleteSession() {
        sessionDao.deleteSession()
    }

    override suspend fun getSession(): SessionEntity {
        return sessionDao.getCurrentSession()
    }

    override suspend fun flowSession(): Flow<List<Session>> {
        return sessionDao.flowCurrentSession().map { flowList ->
            flowList.map { sessionEntityMapper.map(it) }
        }
    }

    override suspend fun hasSession(): Boolean {
        return sessionDao.hasSession()
    }

    override suspend fun refreshToken(): SessionEntity {
        return apiRefreshToken.refreshToken(
            REFRESH_TOKEN_GRANT_TYPE,
            sessionDao.getCurrentSession().refreshToken,
            CLIENT_ID
        ).run {
            SessionEntity(
                sessionId = SESSION_ID,
                accessToken = accessToken ?: EMPTY_STRING,
                tokenType = tokenType ?: EMPTY_STRING,
                expiresIn = (expiresIn?.times(1000)?.plus(System.currentTimeMillis()) ?: 0L),
                refreshToken = refreshToken ?: EMPTY_STRING,
                scope = scope ?: EMPTY_STRING
            )
        }
    }
}
