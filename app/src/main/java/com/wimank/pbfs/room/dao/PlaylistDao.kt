package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao : BaseDao<PlaylistsEntity> {

    @Query("SELECT * FROM playlists")
    fun flowPlaylists(): Flow<List<PlaylistsEntity>>

    @Query("SELECT * FROM playlists")
    fun getPlaylists(): List<PlaylistsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<PlaylistsEntity>)

}
