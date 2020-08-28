package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.pbfs.room.entity.PlaylistsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao : BaseDao<PlaylistsEntity> {

    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<PlaylistsEntity>>

}
