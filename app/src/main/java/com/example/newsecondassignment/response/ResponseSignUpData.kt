package com.example.newsecondassignment.response

import com.google.gson.annotations.SerializedName

data class ResponseSignUpData(
    val success: Boolean,
    val message: String,
    val data: SignUpData?
) {
    data class SignUpData(
        val user_nickname: String
    )
}


