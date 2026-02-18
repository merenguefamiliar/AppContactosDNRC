package com.example.proyecto.data.remote.datasource

import com.example.proyecto.data.remote.model.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api")
    suspend fun getContact(): ApiResponse
}
