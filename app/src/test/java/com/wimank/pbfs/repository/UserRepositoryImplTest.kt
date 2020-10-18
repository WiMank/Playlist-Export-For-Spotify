package com.wimank.pbfs.repository

import com.wimank.pbfs.mapper.UserEntityMapper
import com.wimank.pbfs.mapper.UserMapper
import com.wimank.pbfs.rest.UserApi
import com.wimank.pbfs.room.dao.UserDao
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.utils.CoroutineRule
import com.wimank.pbfs.utils.MockUser.getNetworkUser
import com.wimank.pbfs.utils.MockUser.getUser
import com.wimank.pbfs.utils.MockUser.getUserEntity
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class UserRepositoryImplTest {

    private val userDao: UserDao = mockk()
    private val userApi: UserApi = mockk()
    private val userEntityMapper = UserEntityMapper()
    private val userMapper: UserMapper = UserMapper()
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    @get:Rule
    var coroutinesRule = CoroutineRule()

    @BeforeEach
    fun setUp() {
        userRepositoryImpl = UserRepositoryImpl(
            userDao,
            userApi,
            userEntityMapper,
            userMapper
        )
    }

    @Test
    fun loadNetworkUser() = runBlockingTest {
        coEvery { userApi.getUserData(any()) } returns getNetworkUser()
        coEvery { userDao.clearAndInsertUser(any()) } just Runs

        userRepositoryImpl.loadNetworkUser(EMPTY_STRING)

        coVerify { userApi.getUserData(any()) }
        coVerify { userDao.clearAndInsertUser(any()) }
    }

    @Test
    fun flowUser() = runBlockingTest {
        coEvery { userDao.flowUser() } returns flowOf(getUserEntity())

        userRepositoryImpl.flowUser().collect {
            assertEquals(getUser(), it)
        }

        coVerify { userDao.flowUser() }
    }

    @Test
    fun canUpdateProfile() = runBlockingTest {
        coEvery { userDao.getUpdateTime() } returns null
        assertTrue(userRepositoryImpl.canUpdateProfile())

        coEvery { userDao.getUpdateTime() } returns 1000 * System.currentTimeMillis()
        assertFalse(userRepositoryImpl.canUpdateProfile())

        coEvery { userDao.getUpdateTime() } returns 1000
        assertTrue(userRepositoryImpl.canUpdateProfile())

        coVerify(exactly = 3) { userDao.getUpdateTime() }
    }
}
