package com.qs.framelibrary.skin.attr

import android.view.View

class SkinView(
    private val view: View,
    private val skinAttrs: List<SkinAttr>
) {

    fun skin() {
        skinAttrs.forEach {
            it.skin(view)
        }
    }

}