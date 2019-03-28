package com.be.stepper.ui.view

import com.be.stepper.R
import com.be.stepper.core.BaseFragment
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.welcome_fragment.*

const val MILLIS_FUTURE = 2500L
const val COUNT_DOWN_INTERVAL = 1000L

class WelcomeFragment : BaseFragment() {

    override fun onFinishedViewLoad() {
        welcomeAnimation.playAnimation()
        object : CountDownTimer(MILLIS_FUTURE, COUNT_DOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                navController()!!.navigate(WelcomeFragmentDirections.actionWelcomeSteps())
            }}.start()
    }

    override fun isInjectable(): Boolean = false

    override fun isShakeable(): Boolean = false

    override fun onShake() {}

    override fun fragmentLayout(): Int = R.layout.welcome_fragment

}