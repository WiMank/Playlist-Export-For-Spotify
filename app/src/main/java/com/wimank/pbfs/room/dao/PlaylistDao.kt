package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.pbfs.room.entity.DbPlaylistsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao : BaseDao<DbPlaylistsEntity> {

    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<DbPlaylistsEntity>>

}
