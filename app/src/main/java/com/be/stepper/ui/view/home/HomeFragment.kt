package com.be.stepper.ui.view.home

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
    }

    override fun onStepperContinue() {
        Timber.i("onStepperContinue")
    }

    override fun onStepperBack() {
        Timber.i("onStepperBack")
    }

    override fun onShake() {
        stepperComponent.drawAttention()
        Timber.i("Shake3")
    }

    override fun isShakeable(): Boolean = true

    override fun isInjectable(): Boolean = true

    override fun fragmentLayout(): Int = R.layout.home_fragment

}