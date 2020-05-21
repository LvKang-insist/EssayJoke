package com.qs.essayjoke.activity

import android.content.Intent
import android.view.View
import com.qs.essayjoke.R
import com.qs.framelibrary.BaseSkinActivity
import kotlinx.android.synthetic.main.activity_test_image.*

class TestActivity : BaseSkinActivity() {
    val images = ArrayList<String>()
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
        intent.putExtra(SelectImageActivity.EXTRA_DEFAULT_SELECTED_LIST, images)
        intent.putExtra(SelectImageActivity.EXTRA_SHOW_CAMERA, true)
        startActivity(intent)
    }

    override fun initData() {

    }

}