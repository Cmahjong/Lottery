package com.example.android.sn.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.android.sn.Data
import com.example.android.sn.R
import kotlinx.android.synthetic.main.item.view.*

/**
 *  类描述：
 *  作者： YinJin
 *  创建时间：2018/5/12.23:21
 */
class BaseAdapter:BaseQuickAdapter<Data,BaseViewHolder>(R.layout.item) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {
        helper?.itemView?.apply {
            tv_title.text=item?.title?.trim()
            tv_look.text=item?.time?.trim()
            tv_from.text=item?.from?.trim()
        }
    }
}