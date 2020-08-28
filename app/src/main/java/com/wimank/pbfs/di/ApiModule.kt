package com.wimank.pbfs.di

import com.wimank.pbfs.rest.PlaylistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class ApiModule {

    @Provides
    @FragmentScoped
    fun providePlaylistApi(retrofit: Retrofit): PlaylistsApi {
        return retrofit.create(PlaylistsApi::class.java)
    }

}
