package com.be.stepper.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.be.stepper.R

const val START_ANGLE = 135f
const val ANGLE = 360F
const val STROKE_WIDTH = 25f
const val TWO_AS_DIVIDER = 2

class CompassComponent(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = (canvas.width / 3).toFloat()
        val x = (canvas.width / TWO_AS_DIVIDER).toFloat()
        val y = (canvas.height / TWO_AS_DIVIDER).toFloat()
        val oval = RectF()

        var path = Path()
        var paint = Paint()

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = STROKE_WIDTH

        oval.set(x - radius, y - radius, x + radius, y + radius)

        // Draw circle
        paint.color = ResourcesCompat.getColor(resources, R.color.colorPrimary, context.theme)
        canvas.drawArc(oval, START_ANGLE, ANGLE, false, paint)

        paint.style = Paint.Style.FILL
        paint.color = ResourcesCompat.getColor(resources, R.color.colorPrimary, context.theme)

        val a = ANGLE * Math.PI.toFloat() / 90

        //Draw arrow
        drawTriangle(
            canvas,
            paint,
            x + Math.cos(a.toDouble()).toFloat() * radius,
            y + Math.sin(a.toDouble()).toFloat() * radius,
            70
        )
        canvas.drawPath(path, paint)
    }

    private fun drawTriangle(canvas: Canvas, paint: Paint, x: Float, y: Float, width: Int) {
        val halfWidth = width / TWO_AS_DIVIDER
        val path = Path()
        path.moveTo(x, y - halfWidth) // Top
        path.lineTo(x - halfWidth, y + halfWidth) // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth) // Bottom right
        path.lineTo(x, y - halfWidth) // Back to Top
        canvas.drawPath(path, paint)
    }

}