package com.example.android.sn

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.WindowManager
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_down_land.*
import zlc.season.rxdownload3.RxDownload
import zlc.season.rxdownload3.core.*
import zlc.season.rxdownload3.extension.ApkInstallExtension
import zlc.season.rxdownload3.extension.ApkOpenExtension
import zlc.season.rxdownload3.helper.dispose
import zlc.season.rxdownload3.helper.loge
import java.io.File

class DownLandActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    private var currentStatus = Status()
    private var url = ""
    private  var isEnable=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_land)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        url = intent.getStringExtra("url")
        if (url.contains("https")) {
            url = url.replace("https", "http")
        }
        RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {}
        create()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose(disposable)
    }

    private fun start() {
        RxDownload.start(url).subscribe()
    }

    private fun create() {
        disposable = RxDownload.create(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { status ->
                    if (currentStatus is Failed) {
                        loge("Failed", (currentStatus as Failed).throwable)
                    }
                    start()
                    currentStatus = status
                    setProgress(status)
                    setActionText(status)
                }
    }


    private fun stop() {
        RxDownload.stop(url).subscribe()
    }

    private fun install() {
        if (!isEnable) {
            return
        }
//        RxDownload.extension(url, ApkInstallExtension::class.java).subscribe()
        val install = Intent(Intent.ACTION_VIEW)
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val apkFile = File(Environment.getExternalStorageDirectory().toString() + "/yy/" + "dayingjiacaizy.apk")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val contentUri=   FileProvider.getUriForFile(getApplicationContext(), "com.example.android.sn.provider",apkFile)
            install.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        isEnable=false
        startActivity(install)
    }

    private fun open() {
        RxDownload.extension(url, ApkOpenExtension::class.java).subscribe()
    }

    private fun setProgress(status: Status) {
        progress.max = status.totalSize.toInt()
        progress.progress = status.downloadSize.toInt()
        percent.text = status.percent()
        size.text = status.formatString()
        if (status.percent() == "100.00%") {
            install()
        }
    }

    private fun setActionText(status: Status) {
        val text = when (status) {
            is Normal -> "开始"
            is Suspend -> "已暂停"
            is Waiting -> "等待中"
            is Downloading -> "下载中"
            is Failed -> "下载失败"
            is Succeed -> "安装中"
            is ApkInstallExtension.Installing -> "安装中"
            is ApkInstallExtension.Installed -> "打开"
            else -> ""
        }
        if (text == "安装中" || text == "") {
            install()
            action.text = text
        } else {
            Log.e("asdasdasd", text)
            action.text = text
        }
    }

}
