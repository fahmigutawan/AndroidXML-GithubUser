package com.example.githubuser.data

import com.example.githubuser.model.UserModel
import com.example.githubuser.model.detail.DetailModel
import com.example.githubuser.model.search.SearchModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val retrofit = Retrofit
    .Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface RetrofitClient {
    @GET("search/users")
    suspend fun searchByQuery(@Query("q") q: String): Response<SearchModel>

    @GET("users/{username}")
    suspend fun detailByUsername(@Path("username") username: String): Response<DetailModel>

    @GET("users/{username}/followers")
    suspend fun followersByUsername(@Path("username") username: String): Response<List<UserModel>>

    @GET("users/{username}/following")
    suspend fun followingByUsername(@Path("username") username: String): Response<List<UserModel>>
}