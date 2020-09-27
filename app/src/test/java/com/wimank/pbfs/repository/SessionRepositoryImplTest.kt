package com.wimank.pbfs.repository

import com.wimank.pbfs.mapper.RefreshTokenResponseMapper
import com.wimank.pbfs.mapper.SessionEntityMapper
import com.wimank.pbfs.rest.ApiRefreshToken
import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockSessionRepository.getFlowSessionEntity
import com.wimank.pbfs.utils.MockSessionRepository.getRefreshTokenResponse
import com.wimank.pbfs.utils.MockSessionRepository.getSessionEntity
import com.wimank.pbfs.utils.MockSessionRepository.getSessionList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class SessionRepositoryImplTest {

    private val sessionDao: SessionDao = mockk()
    private val apiRefreshToken: ApiRefreshToken = mockk()
    private val sessionEntityMapper = SessionEntityMapper()
    private val refreshTokenResponseMapper = RefreshTokenResponseMapper()
    private lateinit var sessionRepositoryImpl: SessionRepositoryImpl

    @get:Rule
    var coroutinesRule = CoroutineRule()

    @BeforeEach
    fun setUp() {
        sessionRepositoryImpl =
            SessionRepositoryImpl(
                sessionDao,
                apiRefreshToken,
                sessionEntityMapper,
                refreshTokenResponseMapper
            )
    }

    @Test
    fun getSession() = runBlockingTest {
        coEvery { sessionRepositoryImpl.getSession() } returns getSessionEntity()

        Assert.assertEquals(getSessionEntity(), sessionRepositoryImpl.getSession())

    }

    @Test
    fun flowSession() = runBlockingTest {
        coEvery { sessionDao.flowCurrentSession() } returns flowOf(getFlowSessionEntity())

        sessionRepositoryImpl.flowSession().collect {
            Assert.assertEquals(getSessionList(), it)
        }

        verify { sessionDao.flowCurrentSession() }
    }

    @Test
    fun hasSession() = runBlockingTest {
        coEvery { sessionDao.hasSession() } returns true
        Assert.assertEquals(true, sessionDao.hasSession())

        coEvery { sessionDao.hasSession() } returns false
        Assert.assertEquals(false, sessionDao.hasSession())

        coVerify(exactly = 2) { sessionDao.hasSession() }
    }

    @Test
    fun refreshToken() = runBlockingTest {
        val sessionEntity = getSessionEntity()

        coEvery {
            apiRefreshToken.refreshTokens(any(), any(), any())
        } returns getRefreshTokenResponse()

        coEvery { sessionDao.getCurrentSession() } returns sessionEntity

        with(sessionRepositoryImpl.refreshToken()) {
            Assert.assertEquals(accessToken, sessionEntity.accessToken)
            Assert.assertEquals(refreshToken, sessionEntity.refreshToken)
            Assert.assertEquals(scope, sessionEntity.scope)
            Assert.assertEquals(sessionId, sessionEntity.sessionId)
            Assert.assertEquals(tokenType, sessionEntity.tokenType)
        }

        coVerify { apiRefreshToken.refreshTokens(any(), any(), any()) }
        coVerify { sessionDao.getCurrentSession() }
    }
}
