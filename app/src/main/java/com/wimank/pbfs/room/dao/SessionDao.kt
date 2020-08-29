package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.pbfs.SESSION_ID
import com.wimank.pbfs.room.entity.SessionEntity

@Dao
interface SessionDao : BaseDao<SessionEntity> {

    @Query("SELECT * FROM session WHERE session_id = :sessionId")
    suspend fun getCurrentSession(sessionId: Long = SESSION_ID): SessionEntity

    @Query("SELECT EXISTS(SELECT * FROM session WHERE session_id = :id)")
    suspend fun hasSession(id: Long = SESSION_ID): Boolean

}
