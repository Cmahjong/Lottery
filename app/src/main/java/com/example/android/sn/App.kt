package com.example.android.sn

import android.os.Environment
import cn.jpush.android.api.JPushInterface
import com.example.android.sn.service.RetrofitApplication
import zlc.season.rxdownload3.core.DownloadConfig
import zlc.season.rxdownload3.extension.ApkInstallExtension


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

        val builder = DownloadConfig.Builder.create(this)
                .enableDb(true)
                .setDefaultPath(Environment.getExternalStorageDirectory().toString() + "/yy/")     //设置默认的下载地址
                .enableNotification(true)
                .enableService(true)                        //启用Service
                .enableNotification(true)
                .addExtension(ApkInstallExtension::class.java)
        DownloadConfig.init(builder)
    }
}