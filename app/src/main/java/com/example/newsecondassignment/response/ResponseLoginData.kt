package com.example.newsecondassignment.response

import com.google.gson.annotations.SerializedName

data class ResponseLoginData (
    val success:Boolean,
    val message:String,
    val data:
)

data class  LoginData(
    @SerializedName("UserId")
    val userId:Int,
    val user_nickname
)