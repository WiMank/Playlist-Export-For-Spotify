package com.wimank.pbfs.di

import com.wimank.pbfs.BASE_SPOTIFY_URL
import com.wimank.pbfs.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_SPOTIFY_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        })

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return logging
    }
}
