package com.qs.essayjoke.server

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.qs.essayjoke.User
import com.qs.framelibrary.skin.SkinManager

class UserService : Service() {


    val user = object : User.Stub() {
        override fun getUserName(): String {
            return "345"
        }

        override fun getUserPwd(): String {
            return "123456"
        }

        override fun setSkin() {
            Handler(Looper.getMainLooper()).post {
                SkinManager.restoreDefault()
            }
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return user.asBinder()
    }
}