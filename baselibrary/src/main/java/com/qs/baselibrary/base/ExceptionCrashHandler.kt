@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.qs.baselibrary.base

import android.app.Application
import android.util.Log

object ExceptionCrashHandler : Thread.UncaughtExceptionHandler {

    private const val TAG = "ExceptionCrashHandler"
    private lateinit var context: Application
    private lateinit var mDefaultExceptionHandler: Thread.UncaughtExceptionHandler

    fun init(context: Application) {
        ExceptionCrashHandler.context = context
        //设置全局的异常类为 当前类
        Thread.currentThread().uncaughtExceptionHandler = this
        mDefaultExceptionHandler = Thread.currentThread().uncaughtExceptionHandler
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e(TAG, "异常")

        //让系统默认处理
//        mDefaultExceptionHandler.uncaughtException(t, e)
    }

}