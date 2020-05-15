package com.qs.baselibrary.dialog


import android.app.Dialog
import android.content.Context
import com.qs.baselibrary.R

/**
 * 自定义的万能 diaLog
 */
class AlertDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    private val mAlert = AlertController(this,window)

    class Builder {

        private var P: AlertController.AlertParams

        constructor(context: Context) : this(context, R.style.dialog)

        constructor(context: Context, themeResId: Int) {
            P = AlertController.AlertParams(context, themeResId)
        }

        fun create(): AlertDialog {
            // Context has already been wrapped with the appropriate theme.
            val dialog = AlertDialog(P.mContext, P.mThemeRedId)
            P.apply(dialog.mAlert)
            dialog.setCancelable(P.mCancelable)
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true)
            }
            dialog.setOnCancelListener(P.mOnCancelListener)
            dialog.setOnDismissListener(P.mOnDismissListener)
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener)
            }
            return dialog
        }

        fun show(): AlertDialog? {
            val dialog = create()
            dialog.show()
            return dialog
        }
    }
}