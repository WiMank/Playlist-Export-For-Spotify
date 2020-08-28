package com.wimank.pbfs.di

import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.repository.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
@Suppress("unused")
interface RepositoryModule {

    @Binds
    fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

}
