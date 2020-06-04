package com.qs.essayjoke

import android.app.Activity
import android.app.Application
import com.qs.baselibrary.dialog.ToastDialog
import com.qs.essayjoke.activity.HookStartActivityUtil

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        HookStartActivityUtil().hookStartActivity()

    }
}