package com.delgadojuarez.ucamarket.data.remote.model

data class apiResponseSuccessful(
    val result: String
)

data class apiResponseError(
    val message: String
)

data class LoginResponse(
    val token: String
)

data class UpdateUserResponse(
    val message: String,
    val user: ApiUser
)

data class wishlistResponse(
    val message: String,
    val wishlist: List<ApiProduct>
)