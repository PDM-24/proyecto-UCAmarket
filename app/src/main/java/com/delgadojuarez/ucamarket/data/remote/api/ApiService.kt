package com.delgadojuarez.ucamarket.data.remote.api

import com.delgadojuarez.ucamarket.data.remote.model.ApiUser
import com.delgadojuarez.ucamarket.data.remote.model.LoginResponse
import com.delgadojuarez.ucamarket.data.remote.model.UpdateUserResponse
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful
import com.delgadojuarez.ucamarket.data.remote.model.wishlistResponse
import com.delgadojuarez.ucamarket.di.ApiConstants
import com.delgadojuarez.ucamarket.model.LoginData
import com.delgadojuarez.ucamarket.model.RegisterData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Usuario:
    fun UserController(): UserApiController
    // Servicios
    fun ServiceController(): ServiceApiController
    // Productos
    fun ProductController(): ProductApiController
    // Etiquetas
    fun EtiquetaController(): EtiquetaApiController
}

interface UserApiController{
    // Register
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.REGISTER_PATH)
    fun register(@Body data: RegisterData) : apiResponseSuccessful

    //Login
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.LOGIN_PATH)
    fun Login(@Body data: LoginData) : LoginResponse

    // About Me
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.ABOUTME_PATH)
    fun AboutMe(@Header("Authorization") token: LoginResponse): ApiUser

    // Find all user
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.FINDALL_PATH)
    fun findAll(): List<ApiUser>

    // Find One User
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.FINDONE_PATH)
    fun findOne( @Query("id") id: String ): ApiUser

    // Update User
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.UPDATE_PATH)
    fun update( @Body data: ApiUser ): UpdateUserResponse

    // change Password
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.PASSWORD_PATH)
    fun changePassword(@Body psw: String): apiResponseSuccessful

    // edit Wishlist
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.PASSWORD_PATH)
    fun editWishlist(@Header("Authorization") token: LoginResponse, @Query("id") id: String) : wishlistResponse

}

interface ProductApiController{

}

interface ServiceApiController{

}

interface EtiquetaApiController{

}