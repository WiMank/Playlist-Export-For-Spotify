package com.wimank.pbfs.di

import androidx.lifecycle.ViewModelProvider
import com.wimank.pbfs.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(ActivityComponent::class)
@Suppress("unused")
interface ViewModelFactoryModule {

    @Binds
    @FragmentScoped
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
