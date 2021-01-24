package com.example.newsapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.service.ServiceBuilder
import com.example.newsapp.api.service.TopHeadlines
import com.example.newsapp.model.remote.ArticlesItem
import com.example.newsapp.model.remote.ResponseNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel() : ViewModel() {
    companion object {
        private const val KEY_HOME = "key_homefragment"
    }
    private val topHeadlines = ServiceBuilder.buildService(TopHeadlines::class.java)
    private val data = MutableLiveData<List<ArticlesItem>>()
    private val dataCount = MutableLiveData<Int>()

    fun fetchAPI(country: String, category: String) {
        val call = topHeadlines.fetchHeadlines(country, category)
        call.enqueue(
            object : Callback<ResponseNews> {
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    response.body()?.run {
                        totalResults?.let {
                            dataCount.postValue(it)
                        }
                        articles?.let {
                            data.postValue(it)
                        }
                    }
                }
            }
        )
    }

    fun getDataCount() : LiveData<Int> = dataCount
    fun getData() : LiveData<List<ArticlesItem>> = data
}