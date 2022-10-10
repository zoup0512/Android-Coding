package com.zoup.android.sample.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi():Api{
        return instance.create(Api::class.java)
    }
}