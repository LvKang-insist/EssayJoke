package com.qs.baselibrary.dialog

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.qs.baselibrary.R
import com.qs.baselibrary.base.BaseApplication
import com.qs.baselibrary.dialog.base.FastDialog
import java.lang.ref.WeakReference


object ToastDialog {

    lateinit var weakReference: WeakReference<Activity>

    private val fastDialog by lazy {
        FastDialog.Builder(weakReference.get()!!)
            .setContentView(R.layout.toast_dialog)
            .setAlpha(1f)
            .build()
    }


    fun loading() {
        fastDialog.setText(R.id.tv_toast_message, "加载中")
        fastDialog.getView<AppCompatImageView>(R.id.iv_toast_icon)?.visibility = View.GONE
        fastDialog.getView<ProgressView>(R.id.pw_progress)?.visibility = View.VISIBLE
        fastDialog.show()
    }

    fun unLoading() {
        fastDialog.dismiss()
    }

    fun error() {
        show("错误", R.drawable.ic_dialog_error)
    }

    fun warn() {
        show("警告", R.drawable.ic_dialog_warning)
    }

    fun finish() {
        show("完成", R.drawable.ic_dialog_finish)
    }

    private fun hideLoad() {
        fastDialog.getView<ProgressView>(R.id.pw_progress)?.visibility = View.GONE
        fastDialog.getView<AppCompatImageView>(R.id.iv_toast_icon)?.visibility = View.VISIBLE
    }

    fun show(text: String) {
        fastDialog.setText(R.id.tv_toast_message, text)
        fastDialog.getView<ProgressView>(R.id.pw_progress)?.visibility = View.GONE
        fastDialog.getView<AppCompatImageView>(R.id.iv_toast_icon)?.visibility = View.GONE
        fastDialog.show()
    }

    private fun show(text: String, imageResId: Int) {
        hideLoad()
        fastDialog.setText(R.id.tv_toast_message, text)
        fastDialog.getView<AppCompatImageView>(R.id.iv_toast_icon)
            ?.setBackgroundResource(imageResId)
        fastDialog.show()
    }


}