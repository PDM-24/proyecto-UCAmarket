package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiServices
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class serviceUseCase {
    private val api = apiClient.apiService.ServiceController()

    suspend fun create(_t: String, data: ApiServices): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.create(token, data)
    }

    suspend fun update(_t: String, id: String, data: ApiServices): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.update(token, id, data)
    }

    suspend fun findAll(): List<ApiServices> {
        return api.findAll()
    }

    suspend fun findOne(id: String): ApiServices {
        return api.findOne(id)
    }

    suspend fun findByEmprendimiento(id: String): List<ApiServices> {
        return api.findByEmprendimiento(id)
    }

    suspend fun findOwn(_t: String): List<ApiServices> {
        val token = "Bearer $_t"
        return api.findOwn(token)
    }

    suspend fun delete(_t: String, id: String): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.delete(token, id)
    }

}