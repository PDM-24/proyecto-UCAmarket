package com.delgadojuarez.ucamarket.data.remote.api

import com.delgadojuarez.ucamarket.di.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object apiClient {
    val apiService : ApiService by lazy {
        RetroFitClient.retrofit.create(ApiService::class.java)
    }
}