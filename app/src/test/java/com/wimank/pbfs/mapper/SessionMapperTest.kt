package com.wimank.pbfs.mapper

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.utils.MockSession.getRefreshTokenResponse
import com.wimank.pbfs.utils.MockSession.getSessionEntity
import com.wimank.pbfs.utils.MockSession.getSessionList
import org.junit.Test

class SessionMapperTest {

    private val sessionMapper = SessionMapper()
    private val sessionEntityMapper = SessionEntityMapper()
    private val refreshTokenResponseMapper = RefreshTokenResponseMapper()

    @Test
    fun sessionMapper() {
        val session = getSessionList().first()
        val mapResult = sessionMapper.map(session)

        assertThat(session.sessionId).isEqualTo(mapResult.sessionId)
        assertThat(session.accessToken).isEqualTo(mapResult.accessToken)
        assertThat(session.tokenType).isEqualTo(mapResult.tokenType)
        assertThat(session.expiresIn).isEqualTo(mapResult.expiresIn)
        assertThat(session.refreshToken).isEqualTo(mapResult.refreshToken)
        assertThat(session.scope).isEqualTo(mapResult.scope)
    }

    @Test
    fun sessionEntityMapper() {
        val sessionEntity = getSessionEntity()
        val mapResult = sessionEntityMapper.map(sessionEntity)

        assertThat(sessionEntity.sessionId).isEqualTo(mapResult.sessionId)
        assertThat(sessionEntity.accessToken).isEqualTo(mapResult.accessToken)
        assertThat(sessionEntity.tokenType).isEqualTo(mapResult.tokenType)
        assertThat(sessionEntity.expiresIn).isEqualTo(mapResult.expiresIn)
        assertThat(sessionEntity.refreshToken).isEqualTo(mapResult.refreshToken)
        assertThat(sessionEntity.scope).isEqualTo(mapResult.scope)
    }

    @Test
    fun refreshTokenResponseMapper() {
        val refreshTokenResponse = getRefreshTokenResponse()
        val mapResult = refreshTokenResponseMapper.map(refreshTokenResponse)

        assertThat(refreshTokenResponse.accessToken).isEqualTo(mapResult.accessToken)
        assertThat(refreshTokenResponse.tokenType).isEqualTo(mapResult.tokenType)
        assertThat(mapResult.expiresIn).isGreaterThan(45)
        assertThat(refreshTokenResponse.refreshToken).isEqualTo(mapResult.refreshToken)
        assertThat(refreshTokenResponse.scope).isEqualTo(mapResult.scope)
    }
}
