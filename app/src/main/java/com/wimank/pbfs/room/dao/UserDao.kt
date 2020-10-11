package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.wimank.pbfs.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM user")
    abstract fun flowUser(): Flow<UserEntity>

    @Query("DELETE FROM user")
    abstract suspend fun clearUser()

    @Query("SELECT * FROM user")
    abstract suspend fun getUser(): UserEntity

    @Query("SELECT update_time FROM user")
    abstract suspend fun getUpdateTime(): Long?

    @Transaction
    open suspend fun clearAndInsertUser(userEntity: UserEntity) {
        clearUser()
        insert(userEntity)
    }
}
