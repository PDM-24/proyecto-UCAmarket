package com.delgadojuarez.ucamarket.data.remote.api

import com.delgadojuarez.ucamarket.data.remote.model.ApiEtiqueta
import com.delgadojuarez.ucamarket.data.remote.model.ApiProduct
import com.delgadojuarez.ucamarket.data.remote.model.ApiServices
import com.delgadojuarez.ucamarket.data.remote.model.ApiUser
import com.delgadojuarez.ucamarket.data.remote.model.LoginResponse
import com.delgadojuarez.ucamarket.data.remote.model.UpdateProductResponse
import com.delgadojuarez.ucamarket.data.remote.model.UpdateUserResponse
import com.delgadojuarez.ucamarket.data.remote.model.apiResponseSuccessful
import com.delgadojuarez.ucamarket.data.remote.model.WishlistResponse
import com.delgadojuarez.ucamarket.di.ApiConstants
import com.delgadojuarez.ucamarket.domain.model.LoginData
import com.delgadojuarez.ucamarket.domain.model.RegisterData
import retrofit2.http.Body
import retrofit2.http.DELETE
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
    fun AboutMe(@Header("Authorization") token: String): ApiUser

    // Find all emprendimientos
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.FINDALL_PATH)
    fun findAll(): List<ApiUser>

    // Find One
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.FINDONE_PATH)
    fun findOne( @Query("id") id: String ): ApiUser

    // Update User
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.UPDATE_PATH)
    fun update( @Header("Authorization") token: String, @Body data: ApiUser ): UpdateUserResponse

    // change Password
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.PASSWORD_PATH)
    fun changePassword( @Header("Authorization") token: String, @Body psw: String): apiResponseSuccessful

    // edit Wishlist
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.USER_PATH + ApiConstants.WISHLIST_PATH)
    fun editWishlist(@Header("Authorization") token: String, @Query("id") id: String) : WishlistResponse

}

interface ProductApiController{
    // create
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.UPDATE_PATH)
    fun create(@Header("Authorization") token: String, @Body data: ApiProduct): apiResponseSuccessful

    // update
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.UPDATE_PATH)
    fun update(@Header("Authorization") token: String, @Query("id") id: String, @Body data: ApiProduct): apiResponseSuccessful

    // Find all
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.FINDALL_PATH)
    fun findAll(): List<ApiProduct>

    // Find One
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.FINDONE_PATH)
    fun findOne( @Query("id") id: String ): ApiProduct

    // find by etiqueta
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.BYETIQUETA_PATH)
    fun findByEtiqueta( @Query("id") id: String ): List<ApiProduct>

    // find by emprendimiento
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.BYEMPRENDIMIENTO_PATH)
    fun findByEmprendimiento( @Query("id") id: String ): List<ApiProduct>

    // find own
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.OWN_PATH)
    fun findOwn( @Header("Authorization") token: String ): List<ApiProduct>

    // change hidden
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.HIDDENPRODUCT_PATH)
    fun changeHidden( @Header("Authorization") token: String, @Query("id") id: String ): UpdateProductResponse

    // change status
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.STATUSPRODUCT_PATH)
    fun changeStatus( @Header("Authorization") token: String, @Query("id") id: String, @Body status: String) : UpdateProductResponse

    // delete
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = ApiConstants.API_PATH + ApiConstants.PRODUCT_PATH + ApiConstants.DELETE_PATH)
    fun delete( @Header("Authorization") token: String, @Query("id") id: String) : apiResponseSuccessful

}

interface ServiceApiController{
    // create
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.UPDATE_PATH)
    fun create(@Header("Authorization") token: String, @Body data: ApiServices): apiResponseSuccessful

    // update
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.UPDATE_PATH)
    fun update(@Header("Authorization") token: String, @Query("id") id: String, @Body data: ApiServices): apiResponseSuccessful

    // Find all
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.FINDALL_PATH)
    fun findAll(): List<ApiServices>

    // Find One
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.FINDONE_PATH)
    fun findOne( @Query("id") id: String ): ApiServices

    // find by emprendimiento
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.BYEMPRENDIMIENTO_PATH)
    fun findByEmprendimiento( @Query("id") id: String ): List<ApiServices>

    // find own
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.OWN_PATH)
    fun findOwn( @Header("Authorization") token: String ): List<ApiServices>

    // delete
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = ApiConstants.API_PATH + ApiConstants.SERVICE_PATH + ApiConstants.DELETE_PATH)
    fun delete( @Header("Authorization") token: String, @Query("id") id: String) : apiResponseSuccessful

}

interface EtiquetaApiController{
    // create
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.ETIQUETA_PATH + ApiConstants.UPDATE_PATH)
    fun create(@Body data: ApiEtiqueta): apiResponseSuccessful

    // Find all
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.ETIQUETA_PATH + ApiConstants.FINDALL_PATH)
    fun findAll(): List<ApiEtiqueta>

    // Find One
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.ETIQUETA_PATH + ApiConstants.FINDONE_PATH)
    fun findOne( @Query("id") id: String ): ApiEtiqueta

    // delete
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = ApiConstants.API_PATH + ApiConstants.ETIQUETA_PATH + ApiConstants.DELETE_PATH)
    fun delete( @Query("id") id: String) : apiResponseSuccessful

}