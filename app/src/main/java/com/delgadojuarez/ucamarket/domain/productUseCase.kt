package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiProduct
import com.delgadojuarez.ucamarket.data.remote.model.UpdateProductResponse
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class productUseCase {
    private val api = apiClient.apiService

    suspend fun save(_t: String, id: String?, data: ApiProduct): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.saveProduct(token, id, data)
    }

    suspend fun findAll(): List<ApiProduct> {
        return api.findAllProducts()
    }

    suspend fun findOne(id: String): ApiProduct {
        return api.findOneProduct(id)
    }

    suspend fun findByEtiqueta(id: String): List<ApiProduct> {
        return api.findProductsByEtiqueta(id)
    }

    suspend fun findByEmprendimiento(id: String): List<ApiProduct> {
        return api.findProductsByEmprendimiento(id)
    }

    suspend fun findOwn(_t: String): List<ApiProduct> {
        val token = "Bearer $_t"
        return api.findOwnProducts(token)
    }

    suspend fun changeHidden(_t: String, id: String): UpdateProductResponse {
        val token = "Bearer $_t"
        return api.changeProductHidden(token, id)
    }

    suspend fun changeStatus(_t: String, id: String, status: String): UpdateProductResponse {
        val token = "Bearer $_t"
        return api.changeProductStatus(token, id, status)
    }

    suspend fun delete(_t: String, id: String): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.deleteProduct(token, id)
    }

}