package com.wimank.pbfs.domain.datasource.playlist

import com.wimank.pbfs.room.dao.PlaylistDao
import javax.inject.Inject

class PlaylistsLocalDataSource @Inject constructor(private val playlistDao: PlaylistDao)
