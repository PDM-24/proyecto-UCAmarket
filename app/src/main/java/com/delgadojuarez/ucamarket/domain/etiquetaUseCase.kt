package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiEtiqueta
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful

class etiquetaUseCase {
    private val api = apiClient.apiService.EtiquetaController()

    suspend fun create(data: ApiEtiqueta): apiResponseSuccessful {
        return api.create(data)
    }

    suspend fun findAll(): List<ApiEtiqueta> {
        return api.findAll()
    }

    suspend fun findOne(id: String): ApiEtiqueta {
        return api.findOne(id)
    }

    suspend fun delete(id: String): apiResponseSuccessful {
        return api.delete(id)
    }
}