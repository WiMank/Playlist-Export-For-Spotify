package com.wimank.pbfs.room.dao

import androidx.room.*
import com.wimank.pbfs.room.entity.TracksEntity

@Dao
abstract class TracksDao : BaseDao<TracksEntity> {

    @Query("DELETE FROM tracks")
    abstract suspend fun clearTracks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<TracksEntity>)

    @Query("SELECT * FROM tracks")
    abstract suspend fun getTracks(): List<TracksEntity>

    @Transaction
    open suspend fun clearAndInsertTracks(list: List<TracksEntity>) {
        clearTracks()
        insert(list)
    }
}
