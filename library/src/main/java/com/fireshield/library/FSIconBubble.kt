package com.fireshield.library

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnticipateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.ImageView

/**
 * Created by Daniel S on 05/02/2018.
 */
class FSIconBubble(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var animationDuration: Int = 200

    private val bubbleIndicator: FSBubbleIndicator
        get() = findViewById(R.id.bubble_indicator)

    private val update: Animation by lazy {
        ScaleAnimation(1f, 1.3f, 1f, 1.3f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = animationDuration.toLong()
            repeatMode = Animation.REVERSE
            repeatCount = 1
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private val show: Animation by lazy {
        ScaleAnimation(0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = animationDuration.toLong()
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private val hide: Animation by lazy {
        ScaleAnimation(1f, 0f, 1f, 0f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = animationDuration.toLong()
            interpolator = AnticipateInterpolator()
        }
    }

    init {
        View.inflate(context, R.layout.icon_indicator, this)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.FSBubbleIndicator, 0, 0)
        val textSize = ta.getDimension(R.styleable.FSBubbleIndicator_textSize, 0F)
        val bubbleColor = ta.getColor(R.styleable.FSBubbleIndicator_bubbleColor, Color.RED)
        val textColor = ta.getColor(R.styleable.FSBubbleIndicator_textColor, Color.WHITE)
        val count = ta.getInteger(R.styleable.FSBubbleIndicator_count, 0)

        ta.recycle()

        val ta2 = context.obtainStyledAttributes(attrs, R.styleable.FSIconBubble, 0, 0)
        val icon = ta2.getDrawable(R.styleable.FSIconBubble_icon)
        ta2.recycle()

        val bubble = findViewById<FSBubbleIndicator>(R.id.bubble_indicator)
        bubble.textSize = textSize
        bubble.bubbleColor = bubbleColor
        bubble.textColor = textColor
        bubble.count = count

        val ivIcon = findViewById<ImageView>(R.id.iv_icon)
        ivIcon.setImageDrawable(icon)

        setCount(count)
    }

    fun setCount(count: Int) {
        bubbleIndicator.startAnimation(update)
        bubbleIndicator.count = count
    }

    fun hideBubbleCount() {
        bubbleIndicator.startAnimation(hide)
    }

    fun showBubbleCount() {
        bubbleIndicator.startAnimation(show)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val bubbleIndicator = findViewById<FSBubbleIndicator>(R.id.bubble_indicator)
        val params = bubbleIndicator.layoutParams
        params.height = (bottom - top) / 2

        val ivIcon = findViewById<ImageView>(R.id.iv_icon)
        val padding = (bottom - top) / 6
        ivIcon.setPadding(0, padding, padding, padding)
    }
}