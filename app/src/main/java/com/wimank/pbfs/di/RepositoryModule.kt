package com.wimank.pbfs.di

import com.wimank.pbfs.repository.PlaylistsRepository
import com.wimank.pbfs.repository.PlaylistsRepositoryImpl
import com.wimank.pbfs.repository.SessionRepository
import com.wimank.pbfs.repository.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
@Suppress("unused")
interface RepositoryModule {

    @Binds
    @ActivityScoped
    fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    fun bindPlaylistRepository(playlistRepositoryImpl: PlaylistsRepositoryImpl): PlaylistsRepository

}