package com.example.android.sn.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.android.sn.OneFragment
import com.example.android.sn.ThreeFragment
import com.example.android.sn.TwoFragment

/**
 *  类描述：
 *  作者： YinJin
 *  创建时间：2018/5/12.23:07
 */
class PageAdapter(val fm: FragmentManager): FragmentPagerAdapter(fm) {
    val fragments= arrayListOf(
            OneFragment(),
            TwoFragment(),
            ThreeFragment()

    )
    override fun getItem(position: Int)= fragments[position]

    override fun getCount()= fragments.size
}