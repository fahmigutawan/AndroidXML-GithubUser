package com.example.githubuser.model

data class UserModel(
    val login: String,
    val id: Long,
    val node_id: String,
    val avatar_url: String,
    val url: String,
    val html_url: String,
    val repos_url: String,
    val type: String
)