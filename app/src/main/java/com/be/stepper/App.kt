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

import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG.takeIf { it }.apply { Timber.plant(Timber.DebugTree()) }
        component = buildDagger()
        component!!.inject(this)
    }

    private fun buildDagger(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
    }

    override fun supportFragmentInjector() = fragmentInjector

}