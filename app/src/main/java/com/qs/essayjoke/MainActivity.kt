package com.qs.essayjoke

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.qs.baselibrary.ioc.ViewById
import com.qs.framelibrary.BaseSkinActivity
import java.io.File


class MainActivity : BaseSkinActivity() {


    @ViewById(R.id.test_iv)
    lateinit var mImageView: ImageView

    @ViewById(R.id.test_btn)
    lateinit var mBtnView: Button

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun initTitle() {

    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun initView() {

        LayoutInflater.from(this).inflate(R.layout.activity_main,null,false) //2
        verifyStoragePermissions(this)
        Log.e("------------", mImageView.toString())
        mBtnView.setOnClickListener {
            //读取本地 .skin 里面的资源
            val superRes = resources
            //创反射创建 AssetManager
            val assetManager = AssetManager::class.java.newInstance()
            //添加本地下载好的资源apk
            val method =
                AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            //反射执行方法
            method.invoke(
                assetManager,
                "${Environment.getExternalStorageDirectory().absolutePath}${File.separator}red.skin"
            )


            val resources = Resources(assetManager, superRes.displayMetrics, superRes.configuration)

            val drawableId = resources.getIdentifier("image_src", "drawable", "com.qs.redskin")
            val drawable = resources.getDrawable(drawableId)

            mImageView.setImageDrawable(drawable)
        }

    }


    override fun initData() {

    }

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE"
    )


    fun verifyStoragePermissions(activity: Activity) {

        try {
            //检测是否有写的权限
            val permission = ActivityCompat.checkSelfPermission(
                activity,
                "android.permission.WRITE_EXTERNAL_STORAGE"
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
