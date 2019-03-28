package com.be.stepper.core

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.caliwood.core.rx.DisposeOnLifecycleFragment
import com.app.caliwood.core.rx.LifecycleDisposables
import com.be.stepper.utils.ShakeDetector

import dagger.android.support.AndroidSupportInjection
import timber.log.Timber

abstract class BaseFragment : Fragment(), DisposeOnLifecycleFragment, ShakeDetector.OnShakeListener {

    override val lifecycleDisposables = LifecycleDisposables()
    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isInjectable()) {
            inject()
        }
    }

    private fun putShakeListener() {
        try {
            context?.let {
                (it.getSystemService(Context.SENSOR_SERVICE) as (SensorManager)).let { ss ->
                    var mAccelerometer = ss.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                    mAccelerometer?.let { so ->
                        shakeDetector = ShakeDetector()
                        shakeDetector.putShakeListener(this)
                        ss.registerListener(shakeDetector, so, SensorManager.SENSOR_DELAY_UI)
                        sensorManager = ss
                    }
                }
            }
        } catch (error: Exception) {
            Timber.e(error)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFinishedViewLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragmentLayout(), container, false)

    override fun onPause() {
        super<Fragment>.onPause()
        super<DisposeOnLifecycleFragment>.onPause()
    }

    override fun onStop() {
        super<Fragment>.onStop()
        super<DisposeOnLifecycleFragment>.onStop()
        if (isSensorAvailable()) {
            sensorManager?.apply { unregisterListener(shakeDetector) }
        }
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        super<DisposeOnLifecycleFragment>.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        if (isShakeable()) {
            putShakeListener()
        }
    }

    override fun onDestroy() {
        super<Fragment>.onDestroy()
        super<DisposeOnLifecycleFragment>.onDestroy()
        if (isSensorAvailable()) {
            sensorManager?.apply { unregisterListener(shakeDetector) }
        }
    }

    @LayoutRes
    abstract fun fragmentLayout(): Int

    abstract fun isInjectable(): Boolean

    abstract fun isShakeable(): Boolean

    abstract fun onFinishedViewLoad()

    private fun inject() {
        AndroidSupportInjection.inject(this)
    }

    protected fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }

    private fun isSensorAvailable(): Boolean = ::sensorManager.isInitialized

}