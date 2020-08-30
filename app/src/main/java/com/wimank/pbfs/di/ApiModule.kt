package com.wimank.pbfs.di

import com.wimank.pbfs.rest.ApiRefreshToken
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.util.BASE_TOKEN_ENDPOINT_URL
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

    //Special for refresh tokens request
    //Other base url endpoint
    @Provides
    fun provideApiRefreshToken(): ApiRefreshToken {
        return Retrofit.Builder()
            .baseUrl(BASE_TOKEN_ENDPOINT_URL)
            .build()
            .create(ApiRefreshToken::class.java)
    }

}
