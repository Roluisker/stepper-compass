package com.be.stepper.ui.component

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.be.stepper.R
import kotlinx.android.synthetic.main.stepper_layout.view.*
import android.view.animation.AnimationUtils


class StepperComponent(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var componentListener: StepperComponentListener

    init {
        View.inflate(context, R.layout.stepper_layout, this)
    }

    fun listener(componentListener: StepperComponentListener) {
        this.componentListener = componentListener
        setListeners()
    }

    private fun putStepDone() {
        secondStep.setBackgroundResource(R.drawable.ic_done_step)
        secondTitle.typeface = Typeface.DEFAULT_BOLD
    }

    private fun setListeners() {
        ::componentListener?.isInitialized.takeIf { true }?.apply {
            stepperContinueButton.setOnClickListener { componentListener.onStepperContinue() }
            stepperBackButton.setOnClickListener { componentListener.onStepperBack() }
        }
    }

    fun drawAttention() {
        val shake = AnimationUtils.loadAnimation(context, R.anim.shake)
        startAnimation(shake)
    }

    interface StepperComponentListener {
        fun onStepperContinue()
        fun onStepperBack()
    }

}