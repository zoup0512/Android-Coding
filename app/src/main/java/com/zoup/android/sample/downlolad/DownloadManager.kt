package com.zoup.android.sample.downlolad

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

object DownloadManager {

    fun download(url:String,file: File){
        val request= Request.Builder().url(url).get().build()
        val response=OkHttpClient.Builder().build().newCall(request).execute()
        if(response.isSuccessful){
            val inputStream=response.body()!!.byteStream()
            val byteArray=ByteArray(8*1024)
            inputStream.read(byteArray)
        }

    }
}