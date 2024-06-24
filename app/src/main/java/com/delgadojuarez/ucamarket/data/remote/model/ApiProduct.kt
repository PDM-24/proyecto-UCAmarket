package com.delgadojuarez.ucamarket.data.remote.model

import com.delgadojuarez.ucamarket.di.ApiConstants
import com.google.gson.annotations.SerializedName

data class ApiServices(
    @SerializedName(value = ApiConstants.GENERAL_ID)
    val id: String? = null,

    @SerializedName(value = ApiConstants.PRODUCT_NAME)
    val name: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_DESCRIPTION)
    val desc: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_EMPRENDIMIENTO)
    val contacto: ApiUser

)

data class ApiProduct (
    @SerializedName(value = ApiConstants.GENERAL_ID)
    val id: String? = null,

    @SerializedName(value = ApiConstants.PRODUCT_NAME)
    val name: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_PICTURE)
    val picture: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_DESCRIPTION)
    val desc: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_PRICE)
    val precio: Double = 0.00,

    @SerializedName(value = ApiConstants.PRODUCT_STATUS)
    val estado: String = "",

    @SerializedName(value = ApiConstants.PRODUCT_EMPRENDIMIENTO)
    val emprendimiento: ApiUser,

    @SerializedName(value = ApiConstants.PRODUCT_ETIQUETAS)
    val etiquetas: MutableList<ApiEtiqueta> = arrayListOf(),

    @SerializedName(value = ApiConstants.GENERAL_TIMESTAMPS)
    val timestamp: String = ""

)

data class ApiEtiqueta (
    @SerializedName(value = ApiConstants.GENERAL_ID)
    val id: String? = null,

    @SerializedName(value = ApiConstants.ETIQUETA_NOMBRE)
    val name: String = ""
)