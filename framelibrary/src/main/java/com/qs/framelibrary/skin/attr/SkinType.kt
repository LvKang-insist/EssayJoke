package com.qs.framelibrary.skin.attr

import android.view.View

enum class SkinType(val resName: String) {

    TEXT_COLOR("testColor") {
        override fun skin(view: View, resName: String) {

        }
    },
    BACKGROUND("background"){
        override fun skin(view: View, resName: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    },
    SRC("src"){
        override fun skin(view: View, resName: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    };


    abstract fun skin(view: View, resName: String)

}