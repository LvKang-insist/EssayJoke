package com.qs.baselibrary.ioc

import android.app.Activity
import android.view.View

/**
 * findViewById 的辅助类
 */
class ViewFinder {

    private var mActivity: Activity? = null
    private var mView: View? = null

    constructor(activity: Activity) {
        this.mActivity = activity
    }

    constructor(view: View) {
        this.mView = view
    }

    fun findViewById(viewId: Int): View? {
        return mActivity?.findViewById(viewId) ?: mView?.findViewById(viewId)!!
    }
}