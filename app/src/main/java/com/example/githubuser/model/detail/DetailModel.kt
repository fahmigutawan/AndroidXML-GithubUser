package com.example.githubuser.model.detail

data class DetailModel (
    val login: String,
    val id: Long,
    val node_id: String,
    val avatar_url: String,
    val url: String,
    val name: String,
    val bio: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int
)