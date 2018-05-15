package com.example.android.sn

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import com.example.android.sn.service.bean.UpDataResponse
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {}
        Handler().postDelayed({
            refreshData()

        }, 3000)
    }

    private fun refreshData() {
        (application as App).client.userService
                .upData("cs999", "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<UpDataResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: UpDataResponse) {
                        if (t.data.is_jump == "1") {
                            val intent = Intent(this@StartActivity, DownLandActivity::class.java)
                            intent.putExtra("url", t.data.jump_url)
                            startActivity(intent)
                            onBackPressed()
                        } else {
                            startActivity(Intent(this@StartActivity, MainActivity::class.java))
                            onBackPressed()
                        }

                    }

                    override fun onError(e: Throwable) {
                        startActivity(Intent(this@StartActivity, MainActivity::class.java))
                        onBackPressed()
                    }
                })
    }
}
