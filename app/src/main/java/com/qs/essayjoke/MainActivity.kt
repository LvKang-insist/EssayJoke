package com.qs.essayjoke

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.qs.baselibrary.ioc.OnClick
import com.qs.essayjoke.server.UserService
import com.qs.framelibrary.BaseSkinActivity
import com.qs.framelibrary.skin.SkinManager
import com.qs.framelibrary.skin.SkinResource
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : BaseSkinActivity() {


    @SuppressLint("DiscouragedPrivateApi")


    @OnClick([R.id.test_btn])
    fun onBtn(view: View) {
        val skinPath =
            Environment.getExternalStorageDirectory().absolutePath + File.separator + "red.skin"
        val result: Int = SkinManager.loadSkin(skinPath)
    }

    @OnClick([R.id.test_defalut])
    fun onDefault(view: View) {
        //恢复默认
        val result: Int = SkinManager.restoreDefault()
    }

    @OnClick([R.id.test_start])
    fun onStart(view: View) {
        //跳转
        startActivity(Intent(this, MainActivity::class.java))
    }


    override fun changeSkin(skinResource: SkinResource) {
        Toast.makeText(this, "换", Toast.LENGTH_LONG).show()
    }


    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun initTitle() {

    }

    override fun initView() {
        verifyStoragePermissions(this)
    }


    override fun initData() {
        start_service.setOnClickListener {
            startService(Intent(this, UserService::class.java))
            Toast.makeText(this, "启动成功", Toast.LENGTH_LONG).show()
        }
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
