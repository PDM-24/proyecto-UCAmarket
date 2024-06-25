package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiEtiqueta
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class etiquetaUseCase {
    private val api = apiClient.apiService

    suspend fun create(data: ApiEtiqueta): apiResponseSuccessful {
        return api.createEtiqueta(data)
    }

    suspend fun findAll(): List<ApiEtiqueta> {
        return api.findAllEtiqueta()
    }

    suspend fun findOne(id: String): ApiEtiqueta {
        return api.findOneEtiqueta(id)
    }

    suspend fun delete(id: String): apiResponseSuccessful {
        return api.deleteEtiqueta(id)
    }
}