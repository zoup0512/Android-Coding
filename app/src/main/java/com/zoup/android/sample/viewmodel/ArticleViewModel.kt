package com.zoup.android.sample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zoup.android.sample.model.Article
import com.zoup.android.sample.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application):AndroidViewModel(application) {

    var articleList = MutableLiveData<List<Article>>()

    fun getArticleList(){
        viewModelScope.launch {
           flow{
               emit(RetrofitClient.getApi().getArticleList())
           }.flowOn(Dispatchers.IO).catch { e ->e.printStackTrace() }
               .collect {
                   articleList.value = it
               }

        }
    }
}