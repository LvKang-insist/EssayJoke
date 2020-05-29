package com.qs.essayjoke.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.qs.baselibrary.dialog.ToastDialog
import com.qs.baselibrary.dialog.base.FastDialog
import com.qs.baselibrary.ioc.OnClick
import com.qs.essayjoke.R
import com.qs.framelibrary.BaseSkinActivity
import com.qs.framelibrary.skin.SkinManager
import com.qs.framelibrary.skin.SkinResource
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : BaseSkinActivity() {


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
        startActivity(Intent(this, TestActivity::class.java))
        finish()
    }


    @OnClick([R.id.start_add])
    fun add(view: View) {
        LiveDataBus.with().observe(this, Observer {
            Log.e("------->", "我是消息")
        })
    }

    override fun onDestroy() {
        Log.e("------->", "销毁")
        super.onDestroy()
    }

    @OnClick([R.id.send_event])
    fun sendEvent() {
        LiveDataBus.with().postValue(Any())
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
        val dialog = FastDialog.Builder(this)
            .setContentView(R.layout.dialog)
            .setText(R.id.dialog_text, "发送")
            .setWidth(0.7f)
            .show()

        val edit = dialog.getView<AppCompatEditText>(R.id.dialog_edit)
        dialog.setOnClickListener(R.id.dialog_text) {
            dialog.dismiss()
            Toast.makeText(this, edit?.text.toString(), Toast.LENGTH_LONG).show()
        }
        ToastDialog.warn(this)
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

