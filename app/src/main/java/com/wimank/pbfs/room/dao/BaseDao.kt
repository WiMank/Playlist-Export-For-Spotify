package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

/**
 * Simple dao with base operation [insert] and [update].
 */
@Dao
interface BaseDao<in T> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(insertData: T)

    @Update(onConflict = REPLACE)
    suspend fun update(updateData: T)

}
