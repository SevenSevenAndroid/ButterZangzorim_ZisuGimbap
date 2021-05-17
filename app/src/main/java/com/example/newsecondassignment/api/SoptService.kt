package com.example.newsecondassignment.api

import android.app.DownloadManager
import com.example.newsecondassignment.request.RequestLoginData
import com.example.newsecondassignment.request.RequestSignUpData
import com.example.newsecondassignment.response.ResponseSignUpData
import com.example.newsecondassignment.response.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body:RequestLoginData
    ): Call<ResponseLoginData>

    // 서버에 POST라는 행위를 요청
    // /login/signup이란 식별자에 해당하는 데이터를 body에 담아 보낸다
    @POST("/login/signup")
    fun postSignUp(
        @Body body:RequestSignUpData
    ):Call<ResponseSignUpData>
}