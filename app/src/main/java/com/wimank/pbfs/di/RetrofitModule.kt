package com.wimank.pbfs.di

import com.wimank.pbfs.BASE_SPOTIFY_URL
import com.wimank.pbfs.BuildConfig
import com.wimank.pbfs.util.AppPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
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
    fun provideOkHttp(
        interceptor: HttpLoggingInterceptor,
        appPreferencesManager: AppPreferencesManager
    ): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request =
                    chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer ${appPreferencesManager.getToken()}")
                        .build()
                return chain.proceed(request)
            }
        }).addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }).apply {
            if (BuildConfig.DEBUG)
                setLevel(HttpLoggingInterceptor.Level.BODY)
            else
                setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }
}