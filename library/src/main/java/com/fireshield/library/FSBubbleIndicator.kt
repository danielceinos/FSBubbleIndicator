package com.fireshield.library

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Created by Daniel S on 04/02/2018.
 */
class FSBubbleIndicator(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  init {
    initialize(context!!)


  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.bubble_indicator, this)
  }


}