package com.wimank.pbfs.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.pbfs.room.entity.SessionEntity
import com.wimank.pbfs.util.SESSION_ID

@Dao
interface SessionDao : BaseDao<SessionEntity> {

    @Query("SELECT * FROM session WHERE session_id = :sessionId")
    suspend fun getCurrentSession(sessionId: Long = SESSION_ID): SessionEntity

    @Query("SELECT EXISTS(SELECT * FROM session WHERE session_id = :id)")
    suspend fun hasSession(id: Long = SESSION_ID): Boolean

    @Query("DELETE FROM session WHERE session_id = :id")
    suspend fun deleteSession(id: Long = SESSION_ID)

}
