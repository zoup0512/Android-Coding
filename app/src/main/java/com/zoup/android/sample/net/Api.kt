package com.zoup.android.sample.net

import com.zoup.android.sample.model.Article
import retrofit2.http.GET

interface Api {

    @GET("article/list/0/json")
    suspend fun getArticleList():List<Article>
}