package com.qs.essayjoke.activity

import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qs.baselibrary.dialog.ToastDialog
import com.qs.essayjoke.R
import com.qs.framelibrary.BaseSkinActivity
import kotlinx.android.synthetic.main.activity_image_selector.*


class SelectImageActivity : BaseSkinActivity() {

    companion object {
        //多选
        const val MODE_MULTI = 0x001

        //单选
        const val MODE_SINGLE = 0x002

        //查询全部
        const val LOADER_TYPE = 0x003

        //KEY
        // 是否显示相机的EXTRA_KEY
        const val EXTRA_SHOW_CAMERA = "EXTRA_SHOW_CAMERA"

        // 总共可以选择多少张图片的EXTRA_KEY
        const val EXTRA_SELECT_COUNT = "EXTRA_SELECT_COUNT"

        // 原始的图片路径的EXTRA_KEY
        const val EXTRA_DEFAULT_SELECTED_LIST = "EXTRA_DEFAULT_SELECTED_LIST"

        // 选择模式的EXTRA_KEY
        const val EXTRA_SELECT_MODE = "EXTRA_SELECT_MODE"

        // 返回选择图片列表的EXTRA_KEY
        const val EXTRA_RESULT = "EXTRA_RESULT"

    }

    //单选或者多选
    var mMode =
        MODE_MULTI

    //是否显示拍照按钮
    var mShowCamera = true

    // int 类型的图片张数
    var mMaxCount = 8

    // List 选择好的图片列表
    var mResultList: ArrayList<String>? = null


    override fun initData() {
        // 1.获取传递过来的参数
        val intent = intent
        mMode = intent.getIntExtra(EXTRA_SELECT_MODE, mMode)
        mMaxCount = intent.getIntExtra(EXTRA_SELECT_COUNT, mMaxCount)
        mShowCamera = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, mShowCamera)
        mResultList = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST)
        if (mResultList == null) {
            mResultList = ArrayList()
        }

        // 2.初始化本地图片数据
        initImageList()

    }

    //读取 内存卡中图片
    private fun initImageList() {
        //id：在回调中根据 id 判断要加载那些图片
        LoaderManager.getInstance(this).initLoader(LOADER_TYPE, null, mLoaderCallback)
    }


    /**
     * 加载图片的CallBack
     */
    private val mLoaderCallback: LoaderManager.LoaderCallbacks<Cursor?> =
        object : LoaderManager.LoaderCallbacks<Cursor?> {
            private val IMAGE_PROJECTION = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media._ID
            )

            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor?> {
                // 查询数据库一样 语句
                return CursorLoader(
                    this@SelectImageActivity,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_PROJECTION,
                    IMAGE_PROJECTION[4] + ">0 AND " + IMAGE_PROJECTION[3] + "=? OR "
                            + IMAGE_PROJECTION[3] + "=? ",
                    arrayOf("image/jpeg", "image/png"),
                    IMAGE_PROJECTION[2] + " DESC"
                )
            }

            override fun onLoadFinished(loader: Loader<Cursor?>, data: Cursor?) {
                //解析：封装到集合中
                if (data != null && data.count > 0) {
                    val images: ArrayList<String> = ArrayList()
                    // 如果需要显示拍照，就在第一个位置上加一个空String
                    if (mShowCamera) {
                        images.add("")
                    }
                    // 不断的遍历循环
                    while (data.moveToNext()) {
                        // 只保存路径
                        val path: String = data.getString(
                            data.getColumnIndexOrThrow(
                                IMAGE_PROJECTION[0]
                            )
                        )
                        images.add(path)
                    }
                    // 显示列表数据
                    showImageList(images)
                }
            }

            override fun onLoaderReset(loader: Loader<Cursor?>) {

            }

        }

    /**
     * 3.展示获取到的图片显示到列表
     * @param images
     */
    private fun showImageList(images: ArrayList<String>) {
//        ToastDialog.show(
        Toast.makeText(this, "${images.size}", Toast.LENGTH_LONG).show()
        val listAdapter = SelectImageListAdapter(images)
        image_list_rv.layoutManager = GridLayoutManager(this, 4)
        image_list_rv.adapter = listAdapter
    }


    override fun layout(): Int {
        return R.layout.activity_image_selector
    }

    override fun initTitle() {

    }

    override fun initView() {

    }

    class SelectImageListAdapter(val images: ArrayList<String>) :
        RecyclerView.Adapter<SelectImageListAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image = view.findViewById<AppCompatImageView>(R.id.image)
            val selectImage = view.findViewById<AppCompatImageView>(R.id.media_selected_indicator)
            val camera_ll = view.findViewById<LinearLayoutCompat>(R.id.camera_ll)
            val mask = view.findViewById<View>(R.id.mask)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.media_chooser_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return images.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val path = images.get(position)
            if (TextUtils.isEmpty(path)) {
                holder.mask.visibility = View.VISIBLE
                holder.camera_ll.visibility = View.VISIBLE
                holder.image.visibility = View.GONE
                holder.selectImage.visibility = View.GONE
            } else {
                holder.image.visibility = View.VISIBLE
                holder.selectImage.visibility = View.VISIBLE
                holder.mask.visibility = View.GONE
                holder.camera_ll.visibility = View.GONE

                //显示图片
                Glide.with(holder.image.context)
                    .load(path)
                    .into(holder.image)

                holder.image.setOnClickListener {
                    holder.selectImage.setImageResource(R.drawable.media_chooser_ic_selected)
                }
            }
        }


    }


}