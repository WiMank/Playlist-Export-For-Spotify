package com.wimank.pbfs.di

import android.content.Context
import androidx.room.Room
import com.wimank.pbfs.DATABASE_NAME
import com.wimank.pbfs.room.AppDataBase
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
}
