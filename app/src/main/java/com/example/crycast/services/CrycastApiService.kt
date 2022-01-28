package com.example.crycast.services

import com.example.crycast.dto.Credentials
import com.example.crycast.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CrycastApiService {
    companion object {
        var crycastApiService: CrycastApiService? = null
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        var client: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        fun getInstance(): CrycastApiService{
            if(crycastApiService == null){
                crycastApiService = Retrofit
                    .Builder()
                    .baseUrl("https://crycast.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(CrycastApiService::class.java)
            }
            return crycastApiService!!
        }
    }

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
    @GET("/usersprueba")
    suspend fun getUsersPrueba(): Response<List<User>>


    @POST("/auth/login")
    suspend fun login(@Body credentials: Credentials): Response<User>

}