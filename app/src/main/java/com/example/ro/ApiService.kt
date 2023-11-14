package com.example.ro

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("people")
    fun getData(): Call<Responses>
}