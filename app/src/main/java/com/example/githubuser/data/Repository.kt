package com.example.githubuser.data

import com.example.githubuser.util.getResponse

val retrofitClient = retrofit.create(RetrofitClient::class.java)

object Repository {
    fun searchByQuery(query: String) = getResponse {
        retrofitClient.searchByQuery(query)
    }

    fun detailByUsername(username: String) = getResponse {
        retrofitClient.detailByUsername(username)
    }

    fun followersByUsername(username: String) = getResponse {
        retrofitClient.followersByUsername(username)
    }

    fun followingByUsername(username: String) = getResponse {
        retrofitClient.followingByUsername(username)
    }
}