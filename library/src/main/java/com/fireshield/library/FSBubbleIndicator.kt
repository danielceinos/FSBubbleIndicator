package com.fireshield.library

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

/**
 * Created by Daniel S on 04/02/2018.
 */
class FSBubbleIndicator(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var count: Int = 0
        set(value) {
            field = value
            tvCount.text = value.toString()
        }
    var textSize: Float = 0F
        set(value) {
            field = value
            tvCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, value / 2)
            tvCount.setPadding((value / 2).toInt(), 0, (value / 2).toInt(), 0)
        }
    var bubbleColor: Int = Color.RED
        set(value) {
            field = value
            paint.color = bubbleColor
        }
    var textColor: Int = Color.WHITE
        set(value) {
            field = value
            tvCount.setTextColor(value)
        }

    private val paint: Paint
    private var rect: RectF
    private val tvCount: TextView

    init {
        View.inflate(context, R.layout.bubble_indicator, this)

        setWillNotDraw(false)
        tvCount = findViewById(R.id.tv_count)
        rect = RectF(0F, 0F, 0F, 0F)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        setLayerType(LAYER_TYPE_SOFTWARE, paint)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
        textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 0F)
        bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
        textColor = ta.getColor(R.styleable.FSBubbleIndicator_textColor, Color.WHITE)
        count = ta.getInteger(R.styleable.FSBubbleIndicator_count, 0)

        ta.recycle()

        tvCount.setTextColor(textColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(rect, rect.bottom / 2F, rect.bottom / 2F, paint)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val params = tvCount.layoutParams

        params.width = (Math.max(tvCount.height, tvCount.width))
        rect.right = tvCount.width.toFloat()
        rect.bottom = tvCount.height.toFloat()
    }
}