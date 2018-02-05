package com.fireshield.library

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
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
  var textSize: Float = 0F
    set(value) {
      field = value
      tvCount?.setTextSize(TypedValue.COMPLEX_UNIT_SP, value / 2)
      tvCount?.setPadding((value / 2).toInt(), 0, (value / 2).toInt(), 0)

    }
  private val paint: Paint
  private var rect: RectF
  private val tvCount: TextView?

  init {
    initialize(context!!)

    setWillNotDraw(false)
    tvCount = findViewById(R.id.tv_count)

    rect = RectF(0F, 0F, 0F, 0F)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
    textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 0F)
    val bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSBubbleIndicator_textColor, Color.WHITE)
    val shadowColor = ta.getColor(R.styleable.FSBubbleIndicator_shadowColor, Color.argb(120, 0, 0, 0))

    paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = bubbleColor
    paint.setShadowLayer(dpToPx(5), 0F, dpToPx(1), shadowColor)
    setLayerType(LAYER_TYPE_SOFTWARE, paint)

    count = ta.getInteger(R.styleable.FSBubbleIndicator_count, 0)

    ta.recycle()

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
      val margin = dpToPx(5)
      if (tvCount.height == 0 || tvCount.width == 0) {
        params.width = bottom - top
        params.height = right - left
        textSize = pxToDp(bottom - top).toFloat()
      } else if (tvCount.height > tvCount.width) {
        params.width = (Math.max(tvCount.height, tvCount.width) + margin).toInt()
        params.height = (tvCount.height + margin).toInt()
      } else {
        params.width = (tvCount.width + margin).toInt()
        params.height = (tvCount.height + margin).toInt()
      }

      tvCount.requestLayout()

      rect.left = margin
      rect.top = margin
      rect.right = tvCount.width.toFloat() - margin
      rect.bottom = tvCount.height.toFloat() - margin

    }
  }

  fun dpToPx(dp: Int): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
  }
  fun pxToDp(px: Int): Int {
    val displayMetrics = context.resources.displayMetrics
    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
  }
}