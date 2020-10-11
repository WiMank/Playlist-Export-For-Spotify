package com.wimank.pbfs.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.room.dao.UserDao
import com.wimank.pbfs.room.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDaoTest : DataBaseTest() {

    private val userDao: UserDao by lazy { db.getUserDao() }
    private val userEntity = UserEntity(name = "n", avatar = "a", updateTime = 100)

    @Test
    fun flowUser() = runBlocking {
        userDao.insert(userEntity)
        assertThat(userDao.flowUser().first()).isEqualTo(userEntity)
    }

    @Test
    fun getUpdateTime() = runBlocking {
        userDao.insert(userEntity)
        assertThat(userDao.getUpdateTime()).isEqualTo(100)
    }

    @Test
    fun clearUser() = runBlocking {
        userDao.clearUser()
        assertThat(userDao.getUser()).isNull()
    }

    @Test
    fun clearAndInsertUser() = runBlocking {
        userDao.clearAndInsertUser(userEntity)
        assertThat(userDao.getUser()).isEqualTo(userEntity)
    }
}
