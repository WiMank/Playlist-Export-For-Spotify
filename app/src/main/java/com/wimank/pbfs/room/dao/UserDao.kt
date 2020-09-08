package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM user")
    suspend fun getUser(): User

    @Query("SELECT * FROM user")
    fun flowUser(): Flow<User>

}
