package com.example.crycast.services

import com.example.crycast.dto.Credentials
import com.example.crycast.model.User
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface UsersApiService {

    companion object {
        var usersApiService: UsersApiService? = null
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        var client: OkHttpClient = Builder().apply {
            addInterceptor(interceptor)
        }.build()

        fun getInstance(): UsersApiService{
            if(usersApiService == null){
                usersApiService = Retrofit
                    .Builder()
                    .baseUrl("https://crysec-users.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(UsersApiService::class.java)
            }
            return usersApiService!!
        }
    }

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @POST("/auth/login")
    suspend fun login(@Body credentials: Credentials): Response<User>

}