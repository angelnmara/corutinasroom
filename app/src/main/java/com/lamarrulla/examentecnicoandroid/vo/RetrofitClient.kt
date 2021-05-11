package com.lamarrulla.examentecnicoandroid.vo

import com.google.gson.GsonBuilder
import com.lamarrulla.examentecnicoandroid.api.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.androidhive.info/json/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}