package com.qs.baselibrary.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.qs.baselibrary.ioc.ViewUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(layout())

        val view = LayoutInflater.from(this).inflate(layout(), null)
        val decorView = window.decorView
        val frameLayout = decorView.findViewById<FrameLayout>(android.R.id.content)
        frameLayout.addView(view)

        ViewUtils.inject(view,this)

        initTitle()

        initView()

        initData()
    }

    abstract fun layout(): Int

    abstract fun initTitle()

    abstract fun initView()

    abstract fun initData()

    /**
     * 启动 activity
     */
    fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    /**
     * findViewById
     */
    fun <T : View> viewById(resId: Int): T {
        return findViewById(resId)
    }
}
