package com.qs.framelibrary.skin.attr

import android.view.View

class SkinAttr {
    private lateinit var mResName: String

    private lateinit var mType: SkinType

    fun skin(view: View) {
        mType.skin(view,mResName)
    }

}