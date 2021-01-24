package com.example.newsapp.api.service

import com.example.newsapp.model.remote.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TopHeadlines {
    @GET("/v2/top-headlines")
    fun fetchHeadlines(@Query("country") country: String, @Query("category") category: String): Call<ResponseNews>
}