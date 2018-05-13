package com.example.android.sn

import android.Manifest
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.Toast
import com.example.android.sn.adapter.PageAdapter
import com.example.android.sn.service.bean.UpDataResponse
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignView()
        refreshData()
    }

    private fun refreshData() {
        (application as App).client.userService
                .upData("cs999","android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<UpDataResponse>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: UpDataResponse) {
                        Toast.makeText(this@MainActivity,"成功",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun assignView() {
        view_pager.adapter=PageAdapter(supportFragmentManager)
        view_pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0->{one()}
                    1->{two()}
                    2->{three()}
                }
            }
        })
        RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {}

        tv1.setOnClickListener {
            view_pager.currentItem=0
            one()
        }
        tv2.setOnClickListener {
            view_pager.currentItem=1
            two()

        }
        tv3.setOnClickListener {
            view_pager.currentItem=2
            three()
        }
    }

    private fun three() {
        tv3.setTextColor(Color.parseColor("#353535"))
        tv3.setBackgroundColor(Color.parseColor("#fec300"))
        tv1.setTextColor(Color.parseColor("#ffffff"))
        tv1.setBackgroundColor(Color.parseColor("#666666"))
        tv2.setTextColor(Color.parseColor("#ffffff"))
        tv2.setBackgroundColor(Color.parseColor("#666666"))
    }

    private fun two() {
        tv2.setTextColor(Color.parseColor("#353535"))
        tv2.setBackgroundColor(Color.parseColor("#fec300"))
        tv1.setTextColor(Color.parseColor("#ffffff"))
        tv1.setBackgroundColor(Color.parseColor("#666666"))
        tv3.setTextColor(Color.parseColor("#ffffff"))
        tv3.setBackgroundColor(Color.parseColor("#666666"))
    }

    private fun one() {
        tv1.setTextColor(Color.parseColor("#353535"))
        tv1.setBackgroundColor(Color.parseColor("#fec300"))
        tv2.setTextColor(Color.parseColor("#ffffff"))
        tv2.setBackgroundColor(Color.parseColor("#666666"))
        tv3.setTextColor(Color.parseColor("#ffffff"))
        tv3.setBackgroundColor(Color.parseColor("#666666"))
    }
}
