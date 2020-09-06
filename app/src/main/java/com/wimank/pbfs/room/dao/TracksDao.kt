package com.wimank.pbfs.room.dao

import androidx.room.*
import com.wimank.pbfs.room.entity.TracksEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TracksDao : BaseDao<TracksEntity> {

    @Query("DELETE FROM tracks")
    abstract suspend fun clearTracks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<TracksEntity>)

    @Query("SELECT * FROM tracks WHERE playlist_track = :playlistId")
    abstract suspend fun getTracks(playlistId: String): List<TracksEntity>

    @Query("SELECT * FROM tracks")
    abstract fun flowTracks(): Flow<List<TracksEntity>>

    @Transaction
    open suspend fun clearAndInsertTracks(list: List<TracksEntity>) {
        clearTracks()
        insert(list)
    }
}
