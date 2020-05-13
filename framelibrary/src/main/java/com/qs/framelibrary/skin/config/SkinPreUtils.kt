package com.qs.framelibrary.skin.config

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object SkinPreUtils {

    var context: Context? = null
    fun init(context: Context) {
        this.context = context.applicationContext
    }


    /**
     * 报错当前皮肤路径
     */
    fun saveSkinPath(skinPath: String?) {
        context!!.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(SkinConfig.SKIN_PATH_NAME, skinPath)
            .apply()
    }

    /**
     * 返回当前皮肤路径
     */
    fun getSkinPath(): String? {
        return context!!.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .getString(SkinConfig.SKIN_PATH_NAME, null);
    }

    /**
     * 清空皮肤路径
     */
    fun clearSkinInfo() {
        saveSkinPath(null)
    }
}