package com.qs.essayjoke.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.qs.essayjoke.R
import com.qs.framelibrary.BaseSkinActivity
import kotlinx.android.synthetic.main.activity_test_image.*

class TestActivity : BaseSkinActivity() {
    val images = ArrayList<Uri>()
    override fun layout(): Int {
        return R.layout.activity_test_image
    }

    override fun initTitle() {

    }

    override fun initView() {
        select_image.setOnClickListener(::selectOnClick)
    }

    private fun selectOnClick(view: View) {
        val intent = Intent(this, SelectImageActivity::class.java)
        intent.putExtra(SelectImageActivity.EXTRA_SELECT_COUNT, 9)
        intent.putExtra(SelectImageActivity.EXTRA_SELECT_MODE, SelectImageActivity.MODE_MULTI)
        intent.putParcelableArrayListExtra(SelectImageActivity.EXTRA_DEFAULT_SELECTED_LIST, images)
        intent.putExtra(SelectImageActivity.EXTRA_SHOW_CAMERA, true)
        startActivityForResult(intent, 0)
    }

    override fun initData() {

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0 && data != null) {
                val list: ArrayList<Uri>? =
                    data.getParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT)
                Toast.makeText(this@TestActivity, "${list?.size}", Toast.LENGTH_LONG)
                    .show()
                images.clear()
                images.addAll(list!!)
            } else {
                Toast.makeText(this@TestActivity, "空", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}