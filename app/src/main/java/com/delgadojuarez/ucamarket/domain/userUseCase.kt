package com.delgadojuarez.ucamarket.domain

import com.delgadojuarez.ucamarket.data.remote.api.apiClient
import com.delgadojuarez.ucamarket.data.remote.model.ApiUser
import com.delgadojuarez.ucamarket.data.remote.model.LoginResponse
import com.delgadojuarez.ucamarket.data.remote.model.UpdateUserResponse
import com.delgadojuarez.ucamarket.data.remote.model.WishlistResponse
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful
import com.delgadojuarez.ucamarket.domain.model.LoginData
import com.delgadojuarez.ucamarket.domain.model.RegisterData

class userUseCase {
    private val api = apiClient.apiService

    suspend fun register(name: String, correo: String, password: String, isEmprendedor: Boolean): apiResponseSuccessful {
        val data = RegisterData(
            username = name,
            email = correo,
            pwd = password,
            isEmprendimiento = isEmprendedor
        )
        return api.registerUser(data)
    }

    suspend fun login(correo: String, password: String): LoginResponse {
        val data = LoginData(
            email = correo,
            pwd = password
        )
        return api.LoginUser(data)
    }

    suspend fun getAboutMe(_t: String): ApiUser {
        val token = "Bearer $_t"
        return api.AboutMe(token)
    }

    suspend fun findAll(): List<ApiUser> {
        return api.findAllUsers()
    }

    suspend fun findOne(id: String): ApiUser {
        return api.findOneUser(id)
    }

    suspend fun updateUser(_t: String, data: ApiUser): UpdateUserResponse {
        val token = "Bearer $_t"
        return api.updateUser(token, data)
    }

    suspend fun changePassword(_t: String, psw: String): apiResponseSuccessful {
        val token = "Bearer $_t"
        return api.changePassword(token, psw)
    }

    suspend fun editWishlist(_t: String, id: String): WishlistResponse {
        val token = "Bearer $_t"
        return api.editWishlist(token, id)
    }

}