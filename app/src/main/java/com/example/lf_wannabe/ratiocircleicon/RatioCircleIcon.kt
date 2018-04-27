package com.example.lf_wannabe.ratiocircleicon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by lf_wannabe on 14/10/2017.
 */
class RatioCircleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var color: Int = Color.BLACK
    private var processBackgroundColor: Int = Color.WHITE
    private var startAngle: Float = -90f
    private var rectF: RectF = RectF()
    private var progressRadius: Float = 0f
    private var progressPaint: Paint
    private var progressBackgroundPaint: Paint

    private var bgImg: Drawable? = null

    var progress: Float = 0f
        set(value) {
            field = (3.6f * value)
            invalidate()
        }

    init {
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.mission_icon, 0, 0)

        try {
            progress = typedArray.getFloat(R.styleable.mission_icon_progress, progress)
            color = typedArray.getColor(R.styleable.mission_icon_progress_color, color)
            processBackgroundColor = typedArray.getColor(R.styleable.mission_icon_progress_bg_color, processBackgroundColor)
            bgImg = typedArray.getDrawable(R.styleable.mission_icon_progress_bg_img)
        } finally {
            typedArray.recycle()
        }

        progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressPaint.color = color

        progressBackgroundPaint= Paint(Paint.ANTI_ALIAS_FLAG)
        progressBackgroundPaint.color = color
        progressBackgroundPaint.alpha = 50

        bgImg?.let {
            progressRadius = (if(it.intrinsicWidth > it.intrinsicHeight) it.intrinsicWidth else it.intrinsicHeight).toFloat() * 1.2f
        }

        rectF.set(0f, 0f, progressRadius, progressRadius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?. let {
            it.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width()/2, progressBackgroundPaint)

            it.drawArc(rectF, startAngle, progress, true, progressPaint)
        }

        bgImg?.let {
            it.setBounds((rectF.centerX() - it.intrinsicWidth / 2).toInt(),
                (rectF.centerY() - it.intrinsicHeight / 2).toInt(),
                (rectF.centerX() - it.intrinsicWidth / 2).toInt() + it.intrinsicWidth,
                (rectF.centerY() - it.intrinsicHeight / 2).toInt() + it.intrinsicHeight)
            it.draw(canvas)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)

//        rectF.set(0f, 0f, min.toFloat(), min.toFloat())
    }
}
