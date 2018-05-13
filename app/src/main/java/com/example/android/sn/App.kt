package com.example.android.sn

import cn.jpush.android.api.JPushInterface
import com.example.android.sn.service.RetrofitApplication


/**
 * desc:
 * time: 2018/3/19
 * @author yinYin
 */
class App : RetrofitApplication() {

    override fun onCreate() {
        super.onCreate()
        JPushInterface.init(this)
        JPushInterface.setDebugMode(true)
        initClient()

    }
}