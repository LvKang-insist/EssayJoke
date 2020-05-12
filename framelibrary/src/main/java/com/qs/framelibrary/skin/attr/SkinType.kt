package com.qs.framelibrary.skin.attr

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.qs.framelibrary.skin.SkinManager
import com.qs.framelibrary.skin.SkinResource

enum class SkinType(val resName: String) {

    TEXT_COLOR("textColor") {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun skin(view: View, resName: String) {
            val skinResource = getSkinResource()
            val color = skinResource.getColorByName(resName)
            if (color != null) {
                (view as AppCompatButton).setTextColor(color)
            }
        }
    },
    BACKGROUND("background") {
        @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun skin(view: View, resName: String) {
            //背景可能是图片，也可能是颜色
            Log.e("-----------",resName)
            val skinResource = getSkinResource()
            val drawable = skinResource.getDrawableByName(resName)
            if (drawable != null) {
                view.background = drawable
            } else {
                //可能是颜色
                val color = skinResource.getColorByName(resName)
                if (color != null) {
                    view.setBackgroundColor(color.defaultColor)
                }
            }
        }
    },
    SRC("src") {
        override fun skin(view: View, resName: String) {
            val skinResource = getSkinResource()
            val drawable = skinResource.getDrawableByName(resName)
            if (drawable != null) {
                (view as AppCompatImageView).setImageDrawable(drawable)
            }
        }
    };

    abstract fun skin(view: View, resName: String)

    fun getSkinResource(): SkinResource {
        return SkinManager.getSkinResource()
    }
}