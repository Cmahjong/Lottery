package com.example.android.sn.service

import com.example.android.sn.service.bean.UpDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * desc:
 * time: 2018/3/19
 * @author yinYin
 */
interface UserService {
    @GET("/Lottery_server/get_init_data")
    fun upData(@Query("appid") appid: String, @Query("type") type: String):Observable<UpDataResponse>
}