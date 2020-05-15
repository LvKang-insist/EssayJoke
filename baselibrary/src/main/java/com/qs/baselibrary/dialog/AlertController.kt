package com.qs.baselibrary.dialog

import android.content.Context
import android.content.DialogInterface
import android.view.Window

class AlertController(
    private val alertDialog: AlertDialog,
    private val window: Window?
) {

    /**
     * 获取 Dialog
     */
    fun getDialog(): AlertDialog {
        return alertDialog
    }

    /**
     * 获取 Dialog 的 Window
     */
    fun getWindow(): Window? {
        return window
    }

    class AlertParams(context: Context, themeResId: Int) {
        val mContext: Context = context
        val mThemeRedId: Int = themeResId

        //点击空白是否能够取消
        val mCancelable: Boolean = false

        // dialog cancel 监听
        val mOnCancelListener: DialogInterface.OnCancelListener? = null

        // dialog Dismiss 监听
        val mOnDismissListener: DialogInterface.OnDismissListener? = null

        // 按键监听
        val mOnKeyListener: DialogInterface.OnKeyListener? = null

        /**
         * 绑定和设置参数
         */
        fun apply(alert: AlertController) {

        }

    }

}