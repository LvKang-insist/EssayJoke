package com.qs.essayjoke

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * 正方形的 FrameLayout 容器
 *
 */
class SquareFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        //设置宽高一致
        setMeasuredDimension(width, width)
    }
}