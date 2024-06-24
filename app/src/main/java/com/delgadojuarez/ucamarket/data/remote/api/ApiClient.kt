package com.delgadojuarez.ucamarket.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private val APIURL = ""
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(APIURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object apiClient {
    val apiService : ApiService by lazy {
        RetroFitClient.retrofit.create(ApiService::class.java)
    }
}