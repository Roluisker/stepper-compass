package com.be.stepper.ui.view.home

import com.be.stepper.core.di.InjectViewModel
import com.be.stepper.core.di.ProvideViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ProvideViewModel::class])
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): HomeFragment

}