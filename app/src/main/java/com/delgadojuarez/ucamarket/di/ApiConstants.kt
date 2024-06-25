package com.delgadojuarez.ucamarket.di

class ApiConstants {
    companion object {
        // API URL
        const val BASE_URL = "https://ucamarket-backend.vercel.app"
        const val API_PATH = "/api"

        // API data
        const val GENERAL_ID = "_id"
        const val GENERAL_TIMESTAMPS = "timestamps"

        // Usuario
        const val USER_NAME = "username"
        const val USER_EMAIL = "correo"
        const val USER_PASSWORD = "contrasenia"
        const val USER_SALT = "salt"
        const val USER_TOKENS = "tokens"
        const val USER_ROLES = "roles"
        const val USER_PICTURE = "profile_pic"
        const val USER_DESCRIPTION = "desc"
        const val USER_WHATSAPP = "whatsapp"
        const val USER_WISHLIST = "wishlist"
        const val USER_PATH = "/auth"
        const val REGISTER_PATH = "/register"
        const val LOGIN_PATH = "/login"
        const val ABOUTME_PATH = "/aboutMe"
        const val PASSWORD_PATH = "/password"
        const val WISHLIST_PATH = "/wishlist"

        // Productos
        const val SERVICE_NAME = "nombre"
        const val SERVICE_DESCRIPTION = "desc"
        const val SERVICE_CONTACT = "contacto"
        const val SERVICE_PATH = "/service"

        // Productos
        const val PRODUCT_NAME = "nombre"
        const val PRODUCT_PICTURE = "picture"
        const val PRODUCT_DESCRIPTION = "desc"
        const val PRODUCT_PRICE = "precio"
        const val PRODUCT_ETIQUETAS = "etiqueta"
        const val PRODUCT_STATUS = "estado"
        const val PRODUCT_HIDDEN = "hidden"
        const val PRODUCT_EMPRENDIMIENTO = "emprendimiento"
        const val PRODUCT_PATH = "/product"
        const val HIDDENPRODUCT_PATH = "/hidden"
        const val STATUSPRODUCT_PATH = "/status"

        // Etiquetas
        const val ETIQUETA_NOMBRE = "nombre"
        const val ETIQUETA_PATH = "/etiqueta"

        // API path
        const val UPDATE_PATH = "/save"
        const val FINDONE_PATH = "/findOne"
        const val FINDALL_PATH = "/findAll"
        const val BYETIQUETA_PATH = "/byEtiqueta"
        const val BYEMPRENDIMIENTO_PATH = "/byEmprendimiento"
        const val OWN_PATH = "/own"
        const val DELETE_PATH = "/delete"

    }
}