package com.fireshield.library

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout


/**
 * Created by Daniel S on 05/02/2018.
 */
class FSIconBubble(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  init {
    initialize(context!!)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 0F)
    val bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSBubbleIndicator_textColor, Color.WHITE)
    val shadowColor = ta.getColor(R.styleable.FSBubbleIndicator_shadowColor, Color.argb(120, 0, 0, 0))
    ta.recycle()

    val bubble = findViewById<FSBubbleIndicator>(R.id.bubble_indicator)
    bubble.textSize = textSize
    bubble.bubbleColor = bubbleColor
    bubble.textColor = textColor
    bubble.shadowColor = shadowColor
  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.icon_indicator, this)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    val bubbleIndicator = findViewById<FSBubbleIndicator>(R.id.bubble_indicator)
    val params = bubbleIndicator.layoutParams
    params.height = (bottom - top) / 2
    bubbleIndicator.requestLayout()

  }
}