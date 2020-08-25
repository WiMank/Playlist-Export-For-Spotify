package com.wimank.pbfs.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(insertData: T)


    @Update(onConflict = REPLACE)
    fun update(updateData: T)

    @Delete
    fun delete(deleteData: T)

}
