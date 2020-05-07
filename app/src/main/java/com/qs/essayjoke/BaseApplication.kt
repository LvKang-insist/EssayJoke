package com.qs.essayjoke

import android.app.Application

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //设置全局异常捕捉类
//        ExceptionCrashHandler.init(this)
    }
}