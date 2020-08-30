package com.wimank.pbfs.di

import com.wimank.pbfs.rest.PlaylistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    fun providePlaylistApi(retrofit: Retrofit): PlaylistsApi {
        return retrofit.create(PlaylistsApi::class.java)
    }

}
