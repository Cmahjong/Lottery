package com.yinjin.service

import com.example.android.sn.BuildConfig
import com.example.android.sn.service.UserService
import com.example.android.sn.service.baseResponseConverter.BaseResponseConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * desc:
 * time: 2018/3/19
 * @author yinYin
 */
class Client {

    /** retrofit  */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(BaseResponseConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /** http client  */
    private val httpClient: OkHttpClient by lazy {
        createOkHttpClient()
    }
    var userService: UserService

    constructor() {
        userService = retrofit.create(UserService::class.java)
    }

    /**
     * 创建 ok http
     *
     * @param context
     *         上下文
     * @return ok http
     */
    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    //本拦截器用于加headers
                    var request = chain.request()
                    val requestBuilder = request.newBuilder()
                    val method = request.method()
                    val url = request.url().toString()
                    request = requestBuilder
                            .build()
                    chain.proceed(request)
                }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        builder
                .readTimeout(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(false)
        return builder.build()
    }

    /**
     * 创建gson
     *
     * @return gson
     */
    private fun createGson(): Gson {
        return GsonBuilder()
                .create()
    }
    companion object {
        /** base url */
        const val BASE_URL = "http://www.6122c.com"
        /** http请求的超时时间 */
        const val TIMEOUT_TIME = 30

    }
}