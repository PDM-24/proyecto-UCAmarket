package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiProduct
import com.delgadojuarez.ucamarket.data.remote.model.UpdateProductResponse
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class productUseCase {
    private val api = apiClient.apiService.ProductController()

    suspend fun create(_t: String, data: ApiProduct): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.create(token, data)
    }

    suspend fun update(_t: String, id: String, data: ApiProduct): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.update(token, id, data)
    }

    suspend fun findAll(): List<ApiProduct> {
        return api.findAll()
    }

    suspend fun findOne(id: String): ApiProduct {
        return api.findOne(id)
    }

    suspend fun findByEtiqueta(id: String): List<ApiProduct> {
        return api.findByEtiqueta(id)
    }

    suspend fun findByEmprendimiento(id: String): List<ApiProduct> {
        return api.findByEmprendimiento(id)
    }

    suspend fun findOwn(_t: String): List<ApiProduct> {
        val token = "Bearer $_t"
        return api.findOwn(token)
    }

    suspend fun changeHidden(_t: String, id: String): UpdateProductResponse {
        val token = "Bearer $_t"
        return api.changeHidden(token, id)
    }

    suspend fun changeStatus(_t: String, id: String, status: String): UpdateProductResponse {
        val token = "Bearer $_t"
        return api.changeStatus(token, id, status)
    }

    suspend fun delete(_t: String, id: String): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.delete(token, id)
    }

}