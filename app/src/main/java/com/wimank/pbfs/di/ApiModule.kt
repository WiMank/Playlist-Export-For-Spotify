package com.wimank.pbfs.di

import com.wimank.pbfs.rest.ApiRefreshToken
import com.wimank.pbfs.rest.PlaylistsApi
import com.wimank.pbfs.rest.TracksApi
import com.wimank.pbfs.rest.UserApi
import com.wimank.pbfs.util.BASE_TOKEN_ENDPOINT_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    fun providePlaylistApi(retrofit: Retrofit): PlaylistsApi {
        return retrofit.create(PlaylistsApi::class.java)
    }

    @Provides
    fun provideTracksApi(retrofit: Retrofit): TracksApi {
        return retrofit.create(TracksApi::class.java)
    }

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    //Special for refresh tokens request
    //Other base url endpoint
    @Provides
    fun provideApiRefreshToken(okHttpClient: OkHttpClient): ApiRefreshToken {
        return Retrofit.Builder()
            .baseUrl(BASE_TOKEN_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiRefreshToken::class.java)
    }

}
