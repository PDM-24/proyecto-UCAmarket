package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiServices
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class serviceUseCase {
    private val api = apiClient.apiService

    suspend fun save(_t: String, id: String?, data: ApiServices): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.saveService(token, id, data)
    }

    suspend fun findAll(): List<ApiServices> {
        return api.findAllServices()
    }

    suspend fun findOne(id: String): ApiServices {
        return api.findOneServices(id)
    }

    suspend fun findByEmprendimiento(id: String): List<ApiServices> {
        return api.findServicesByEmprendimiento(id)
    }

    suspend fun findOwn(_t: String): List<ApiServices> {
        val token = "Bearer $_t"
        return api.findOwnServices(token)
    }

    suspend fun delete(_t: String, id: String): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.deleteServices(token, id)
    }

}