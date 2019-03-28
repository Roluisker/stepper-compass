package com.be.stepper.ui.view.home

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.be.stepper.core.BaseViewModel

const val MILLIS_FUTURE_TO_ATTENTION = 5000L
const val COUNT_DOWN_INTERVAL_TO_ATTENTION = 1000L

class HomeModel : BaseViewModel() {

    val liveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun countToDrawAttention() {
        object : CountDownTimer(MILLIS_FUTURE_TO_ATTENTION, COUNT_DOWN_INTERVAL_TO_ATTENTION) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                liveData.postValue(true)
                start()
            }}.start()
    }

}