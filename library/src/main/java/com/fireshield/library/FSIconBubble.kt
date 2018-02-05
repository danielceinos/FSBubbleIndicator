package com.fireshield.library

import android.content.Context
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
  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.icon_indicator, this)

  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    val bubbleIndicator = findViewById<FSBubbleIndicator>(R.id.bubble_indicator)
    val params = bubbleIndicator.layoutParams
    params.height = (bottom - top) / 2
//    bubbleIndicator.textSize = pxToDp((bottom - top) / 2).toFloat()
    bubbleIndicator.requestLayout()

  }

  fun pxToDp(px: Int): Int {
    val displayMetrics = context.resources.displayMetrics
    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
  }
}