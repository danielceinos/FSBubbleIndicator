package com.fireshield.library

import android.content.Context
import android.content.res.Resources
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

  var count: Int = 0
    set(value) {
      field = value
      tvCount?.text = value.toString()
    }
  private val paint: Paint
  private var rect: RectF
  private val tvCount: TextView?

  init {
    initialize(context!!)

    setWillNotDraw(false)
    rect = RectF(0F, 0F, 0F, 0F)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 20F)
    val bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSBubbleIndicator_textColor, Color.WHITE)

    paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = bubbleColor
    paint.setShadowLayer(dpToPx(5), 0F, dpToPx(1), Color.argb(120, 0, 0, 0))
    setLayerType(LAYER_TYPE_SOFTWARE, paint)

    count = ta.getInteger(R.styleable.FSBubbleIndicator_count, 0)

    ta.recycle()

    tvCount = findViewById(R.id.tv_count)
    tvCount.text = count.toString()
    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / 2)
    tvCount.setPadding((textSize / 2).toInt(), 0, (textSize / 2).toInt(), 0)
    tvCount.setTextColor(textColor)
  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.bubble_indicator, this)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawRoundRect(rect, rect.bottom / 2F, rect.bottom / 2F, paint)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    if (tvCount != null) {
      val params = tvCount.layoutParams
      if (tvCount.height > tvCount.width) {
        params.width = Math.max(tvCount.height, tvCount.width)
      }
      tvCount.requestLayout()
      val margin = dpToPx(5)

      rect.left = margin
      rect.top = margin
      rect.right = tvCount.width.toFloat() - margin
      rect.bottom = tvCount.height.toFloat() - margin

    }
  }

  fun dpToPx(dp: Int): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
  }
}