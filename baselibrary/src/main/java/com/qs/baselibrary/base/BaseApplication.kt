package com.qs.baselibrary.base

import android.app.Activity
import android.app.Application
import android.os.Bundle

class BaseApplication : Application() {
    var mActivity: Activity? = null
    override fun onCreate() {
        super.onCreate()

        //设置全局异常捕捉类
//        ExceptionCrashHandler.init(this)

        registerActivityLifecycleCallbacks(object : StartCallBack() {
            override fun onActivityStarted(activity: Activity) {
                mActivity = activity
            }
        })

    }
}