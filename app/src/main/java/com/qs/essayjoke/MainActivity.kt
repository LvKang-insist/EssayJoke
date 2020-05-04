package com.qs.essayjoke

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.qs.baselibrary.CheckNet
import com.qs.baselibrary.OnClick
import com.qs.baselibrary.ViewById
import com.qs.baselibrary.ioc.ViewUtils

class MainActivity : AppCompatActivity() {

    @ViewById(R.id.test_tv)
    private lateinit var mTextTv: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewUtils.inject(this)

    }


    @OnClick([R.id.test_tv, R.id.test_iv])
    @CheckNet
    private fun onClick() {
        Toast.makeText(this, "-----", Toast.LENGTH_LONG).show()
    }
}
