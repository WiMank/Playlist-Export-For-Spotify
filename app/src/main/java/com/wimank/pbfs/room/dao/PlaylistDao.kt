package com.wimank.pbfs.room.dao

import androidx.room.*
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlaylistDao : BaseDao<PlaylistsEntity> {

    @Query("SELECT * FROM playlists")
    abstract fun flowPlaylists(): Flow<List<PlaylistsEntity>>

    @Query("SELECT * FROM playlists")
    abstract suspend fun getPlaylists(): List<PlaylistsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<PlaylistsEntity>)

    @Query("DELETE FROM playlists")
    abstract suspend fun clearPlaylists()

    @Transaction
    open suspend fun clearAndInsertPlaylists(list: List<PlaylistsEntity>) {
        clearPlaylists()
        insert(list)
    }
}
