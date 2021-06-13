package com.example.newsecondassignment.request

import com.google.gson.annotations.SerializedName

//data class RequestLoginData(
//    val email: String,
//    val password: String
//)

data class RequestLoginData(
    @SerializedName("email")
    val id: String,
    val password: String
)