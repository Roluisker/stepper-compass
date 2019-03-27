package com.be.stepper.ui.component

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.be.stepper.R
import kotlinx.android.synthetic.main.stepper_layout.view.*

class StepperComponent(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.stepper_layout, this)
    }

    private fun putStepDone() {
        secondStep.setBackgroundResource(R.drawable.ic_done_step)
        secondTitle.typeface = Typeface.DEFAULT_BOLD
    }

}