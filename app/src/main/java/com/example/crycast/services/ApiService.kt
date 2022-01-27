package com.example.crycast.services

import com.example.crycast.Credentials
import com.example.crycast.model.User
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    companion object {
        var apiService: ApiService? = null
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        var client: OkHttpClient = Builder().apply {
            addInterceptor(interceptor)
        }.build()

        fun getInstance(): ApiService{
            if(apiService == null){
                apiService = Retrofit
                    .Builder()
                    .baseUrl("https://boiling-hollows-83192.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @POST("/login")
    suspend fun login(@Body credentials: Credentials): Response<User>

}