package com.qs.framelibrary.skin.attr

import android.util.Log
import android.view.View

class SkinView(
    private val view: View,
    private val skinAttrs: List<SkinAttr>
) {

    fun skin() {
        Log.e("SkinType ", "${skinAttrs.size}")
        skinAttrs.forEach {
            it.skin(view)
        }
    }

}