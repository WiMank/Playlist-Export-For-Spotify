package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(insertData: T)


    @Update(onConflict = REPLACE)
    suspend fun update(updateData: T)

    @Delete
    suspend fun delete(deleteData: T)

}
