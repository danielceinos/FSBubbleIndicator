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
class FSBubbleIndicator(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  var count: Int

  init {
    initialize(context!!)

    setWillNotDraw(false)
    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 20F)
    val bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
    count = ta.getInteger(R.styleable.FSBubbleIndicator_count, 0)

    ta.recycle()

    val tvCount = findViewById<TextView>(R.id.tv_count)
    tvCount.text = count.toString()
    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / 2)
    tvCount.setPadding((textSize / 2).toInt(), 0, (textSize / 2).toInt(), 0)

  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.bubble_indicator, this)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    val paint = Paint()
    paint.color = Color.RED

    val tvCount = findViewById<TextView>(R.id.tv_count)

    val rect = RectF(0F, 0F, tvCount.width.toFloat(), tvCount.height.toFloat())
    canvas?.drawRoundRect(rect, tvCount.height / 2F, tvCount.height / 2F, paint)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    val tvCount = findViewById<TextView>(R.id.tv_count)
    val params = tvCount.layoutParams
    if (tvCount.height > tvCount.width) {
      params.width = Math.max(tvCount.height, tvCount.width)
    }
    tvCount.requestLayout()
  }

}