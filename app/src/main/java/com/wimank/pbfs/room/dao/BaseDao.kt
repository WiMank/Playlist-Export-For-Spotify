package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = REPLACE)
    fun insert(insertData: T)


    @Update(onConflict = REPLACE)
    fun update(updateData: T)

    @Delete
    fun delete(deleteData: T)

}
