package com.delgadojuarez.ucamarket.data.remote.model

import com.delgadojuarez.ucamarket.di.ApiConstants
import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName(value = ApiConstants.GENERAL_ID)
    val id: String? = null,

    @SerializedName(value = ApiConstants.USER_NAME)
    val name: String = "",

    @SerializedName(value = ApiConstants.USER_EMAIL)
    val correo: String = "",

    @SerializedName(value = ApiConstants.USER_ROLES)
    val roles: MutableList<String> = arrayListOf(),

    @SerializedName(value = ApiConstants.USER_PICTURE)
    val picture: String = "",

    @SerializedName(value = ApiConstants.USER_DESCRIPTION)
    val desc: String = "",

    @SerializedName(value = ApiConstants.USER_WHATSAPP)
    val whatsapp: String = "",

    @SerializedName(value = ApiConstants.USER_WISHLIST)
    val wishlist: MutableList<ApiProduct> = arrayListOf()
)