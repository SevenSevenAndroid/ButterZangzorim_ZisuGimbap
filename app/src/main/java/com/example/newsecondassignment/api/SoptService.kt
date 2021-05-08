package com.example.newsecondassignment.api

import android.app.DownloadManager
import android.telecom.Call
import com.example.newsecondassignment.request.RequestLoginData
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body:RequestLoginData
    ): Call<RequestLoginData>
}