package com.be.stepper.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

const val SHAKE_THRESHOLD_GRAVITY = 2.7f
const val SHAKE_SLOP_TIME_MS = 500
const val SHAKE_COUNT_RESET_TIME_MS = 3000

class ShakeDetector : SensorEventListener {

    private var mShakeTimestamp: Long = 0
    private var mShakeCount: Int = 0

    private lateinit var shakeListener: OnShakeListener

    override fun onSensorChanged(event: SensorEvent?) {

        if (::shakeListener.isInitialized) {

            event?.let {

                val gX = it.values[0] / SensorManager.GRAVITY_EARTH.toDouble()
                val gY = it.values[1] / SensorManager.GRAVITY_EARTH.toDouble()
                val gZ = it.values[2] / SensorManager.GRAVITY_EARTH.toDouble()

                // gForce will be close to 1 when there is no movement.
                val gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ)

                if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                    val now = System.currentTimeMillis()
                    // ignore shake events too close to each other (500ms)
                    if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                        return
                    }

                    // reset the shake count after 3 seconds of no shakes
                    if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                        mShakeCount = 0
                    }

                    mShakeTimestamp = now
                    mShakeCount++

                    shakeListener.onShake()

                }

            }

        }

    }

    fun putShakeListener(shakeListener: OnShakeListener) {
        this.shakeListener = shakeListener
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    interface OnShakeListener {
        fun onShake()
    }

}