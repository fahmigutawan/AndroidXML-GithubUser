package com.example.githubuser.model.search

import com.example.githubuser.model.UserModel

data class SearchModel(
    val total_count: Int,
    val items: List<UserModel>
)
