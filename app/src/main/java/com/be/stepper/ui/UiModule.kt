package com.be.stepper.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.be.stepper.core.AppViewModelFactory
import com.be.stepper.ui.view.home.HomeModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [HomeModule::class])
class UiModule {

    @Provides
    fun provideViewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>):
            ViewModelProvider.Factory = AppViewModelFactory(providers)

}