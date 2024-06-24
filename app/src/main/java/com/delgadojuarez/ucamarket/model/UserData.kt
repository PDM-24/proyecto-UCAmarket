package com.delgadojuarez.ucamarket.model

data class RegisterData(
    var username: String = "",
    var email: String = "",
    var pwd: String = "",
    var isEmprendimiento: Boolean = false
){
    fun isNotEmpty(): Boolean{
        return username.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty()
    }
}

data class LoginData(
    var email: String = "",
    var pwd: String = ""
){
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && pwd.isNotEmpty()
    }
}