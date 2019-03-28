package com.be.stepper.ui.view

import com.be.stepper.R
import com.be.stepper.core.BaseFragment
import com.be.stepper.ui.component.StepperComponent
import kotlinx.android.synthetic.main.final_step_fragment.*

class FinalStepFragment : BaseFragment(), StepperComponent.StepperComponentListener {

    override fun onFinishedViewLoad() {
        finalStepperComponent.listener(this)
    }

    override fun onShake() {

    }

    override fun onStepperContinue() {

    }

    override fun onStepperBack() {

    }

    override fun isInjectable(): Boolean = false

    override fun isShakeable(): Boolean = true

    override fun fragmentLayout(): Int = R.layout.final_step_fragment

}