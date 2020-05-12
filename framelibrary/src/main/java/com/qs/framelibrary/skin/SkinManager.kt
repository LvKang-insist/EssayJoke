package com.qs.framelibrary.skin

import android.app.Activity
import android.app.Application
import android.content.Context
import com.qs.framelibrary.skin.attr.SkinView

object SkinManager {

    private var mContext: Application? = null

    private val mSkinViews = mutableMapOf<Activity, MutableList<SkinView>>()

    private var skinResource: SkinResource? = null

    fun init(context: Context) {
        this.mContext = context.applicationContext as Application?
    }

    /**
     * 加载皮肤
     */
    fun loadSkin(skinPath: String): Int {
        //检验签名

        //初始化资源管理
        skinResource = SkinResource(mContext!!, skinPath)

        //改变皮肤
        mSkinViews.forEach { maps ->
            val skinViews = maps.value
            skinViews.forEach {
                it.skin()
            }
        }
        return 0
    }

    /**
     * 恢复默认
     */
    fun restoreDefault(): Int {
        return 0
    }

    /**
     * 获取 List<SkinView> 通过 activity
     */
    fun getSkinViews(activity: Activity): MutableList<SkinView>? {
        return mSkinViews[activity]
    }

    /**
     * 注册
     */
    fun register(activity: Activity, skinViews: MutableList<SkinView>) {
        mSkinViews[activity] = skinViews
    }

    /**
     * 获取当前皮肤资源
     */
    fun getSkinResource(): SkinResource {
        return skinResource!!
    }

}
