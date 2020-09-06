package com.wimank.pbfs.di

import android.content.Context
import androidx.room.Room
import com.wimank.pbfs.room.AppDataBase
import com.wimank.pbfs.room.dao.PlaylistDao
import com.wimank.pbfs.room.dao.SessionDao
import com.wimank.pbfs.room.dao.TracksDao
import com.wimank.pbfs.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext applicationContext: Context): AppDataBase {
        return Room.databaseBuilder(applicationContext, AppDataBase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(db: AppDataBase): SessionDao {
        return db.getSessionDao()
    }

    @Provides
    @Singleton
    fun providePlaylistDao(db: AppDataBase): PlaylistDao {
        return db.getPlaylistDao()
    }

    @Provides
    @Singleton
    fun provideTracksDao(db: AppDataBase): TracksDao {
        return db.getTracksDao()
    }

}
