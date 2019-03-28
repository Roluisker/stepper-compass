package com.be.stepper.ui.view.home

import androidx.lifecycle.Observer
import com.be.stepper.R
import com.be.stepper.core.BaseFragment
import com.be.stepper.ui.component.StepperComponent

import kotlinx.android.synthetic.main.home_fragment.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment(), StepperComponent.StepperComponentListener {

    @Inject
    lateinit var viewModel: HomeModel

    override fun onFinishedViewLoad() {
        stepperComponent.listener(this)
        inciteUserToContinueHandler()
    }

    private fun inciteUserToContinueHandler() {
        viewModel.liveData.observe(this, Observer<Boolean> {
            stepperComponent.drawContinueAttention()
        })
        viewModel.countToDrawAttention()
    }

    override fun onStepperContinue() {
        navController()!!.navigate(HomeFragmentDirections.actionFinalStep())
    }

    override fun onStepperBack() {}

    override fun onShake() {
        stepperComponent.drawGeneralAttention()
        Timber.i("Shake3")
    }

    override fun isShakeable(): Boolean = true

    override fun isInjectable(): Boolean = true

    override fun fragmentLayout(): Int = R.layout.home_fragment

}