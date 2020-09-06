package com.wimank.pbfs.di

import com.wimank.pbfs.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
@Suppress("unused")
interface RepositoryModule {

    @Binds
    fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    fun bindPlaylistRepository(playlistRepositoryImpl: PlaylistsRepositoryImpl): PlaylistsRepository


    @Binds
    fun bindTracksRepository(tracksRepositoryImpl: TracksRepositoryImpl): TracksRepository

}
