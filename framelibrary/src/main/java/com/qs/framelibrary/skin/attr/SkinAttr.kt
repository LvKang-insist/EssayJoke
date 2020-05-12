package com.qs.framelibrary.skin.attr

import android.view.View

class SkinAttr( val resName: String, private val  type: SkinType) {

    fun skin(view: View) {
        type.skin(view,resName)
    }
}