package com.qs.framelibrary.skin.attr

import android.view.View

class SkinView {
    private lateinit var mView: View

    private var mAttrs: MutableList<SkinAttr>? = null

    fun skin() {
        mAttrs?.forEach {
            it.skin(mView)
        }
    }

}