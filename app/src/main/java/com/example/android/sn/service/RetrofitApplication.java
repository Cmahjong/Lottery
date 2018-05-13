package com.example.android.sn.service;

import android.support.multidex.MultiDexApplication;

import com.yinjin.service.Client;

/**
 * desc:
 * time: 2018/3/19
 *
 * @author yinYin
 */
public abstract class RetrofitApplication extends MultiDexApplication {
    /** Retrofit client */
   public Client client;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 初始化Retrofit Client
     */
    protected void initClient() {
        client = new Client();
    }
}
