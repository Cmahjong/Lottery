package com.example.android.sn.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.android.sn.Data
import com.example.android.sn.R
import com.example.android.sn.util.GlideUtil
import kotlinx.android.synthetic.main.item1.view.*

/**
 *  类描述：
 *  作者： YinJin
 *  创建时间：2018/5/12.23:21
 */
class Base1Adapter:BaseQuickAdapter<Data,BaseViewHolder>(R.layout.item1) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {
        helper?.itemView?.apply {
            GlideUtil.loadPicture(img_picture,item?.imgUrl?:"")
            tv_title.text=item?.title?.trim()
        }
    }
}