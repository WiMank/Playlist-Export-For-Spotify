package com.wimank.pbfs.di

import android.content.Context
import com.wimank.pbfs.util.ConnectivityWatcher
import com.wimank.pbfs.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    @ActivityScoped
    fun provideConnectivityWatcher(@ActivityContext context: Context) = ConnectivityWatcher(context)


    @Provides
    @ActivityScoped
    fun provideNetworkManager(@ActivityContext context: Context) = NetworkManager(context)

}
