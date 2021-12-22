package com.example.headsupprep

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("/celebrities/")
    fun getAPIUsers(): Call<List<UsersItem>>

    @POST("/celebrities/")
    fun addUser(@Body userData: UsersItem): Call<UsersItem>

    @PUT("/celebrities/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: UsersItem): Call<UsersItem>

    @DELETE("/celebrities/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>


}

