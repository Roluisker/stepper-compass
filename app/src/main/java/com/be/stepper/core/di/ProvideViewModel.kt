package com.be.stepper.core.di

import androidx.lifecycle.ViewModel
import com.be.stepper.core.annotation.ViewModelKey
import com.be.stepper.ui.view.home.HomeModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProvideViewModel {

    @Provides
    @IntoMap
    @ViewModelKey(HomeModel::class)
    fun provideHomeViewModel(): ViewModel = HomeModel()

}