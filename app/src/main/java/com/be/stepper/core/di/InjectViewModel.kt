package com.be.stepper.core.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.be.stepper.ui.view.home.HomeFragment
import com.be.stepper.ui.view.home.HomeModel
import dagger.Module
import dagger.Provides

@Module
class InjectViewModel {

    @Provides
    fun provideHomeViewModel(
        factory: ViewModelProvider.Factory,
        target: HomeFragment
    ): HomeModel = ViewModelProviders.of(target, factory).get(HomeModel::class.java)

}