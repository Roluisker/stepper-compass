package com.be.stepper
/*
 * Luis Alfonso Bejarano Sanchez
 *
 * Licensed under GNU (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.gnu.org/philosophy/philosophy.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an GNU,
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import com.be.stepper.ui.UiModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class, UiModule::class])
@Singleton
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}