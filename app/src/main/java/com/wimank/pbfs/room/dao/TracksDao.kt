package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimank.pbfs.room.entity.TracksEntity

@Dao
abstract class TracksDao : BaseDao<TracksEntity> {

    @Query("DELETE FROM tracks")
    abstract suspend fun clearTracks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<TracksEntity>)

    @Query("SELECT * FROM tracks WHERE playlist_id = :playlistId")
    abstract suspend fun getTracks(playlistId: String): List<TracksEntity>

}
