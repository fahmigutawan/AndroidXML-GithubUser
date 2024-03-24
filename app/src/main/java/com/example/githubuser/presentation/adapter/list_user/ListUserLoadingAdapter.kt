package com.example.githubuser.presentation.adapter.list_user

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubuser.R
import com.example.githubuser.databinding.UserCardLoadingBinding
import com.example.githubuser.util.AppRecyclerViewAdapter

class ListUserLoadingAdapter :
    AppRecyclerViewAdapter<UserCardLoadingBinding, Int>() {
    override val itemHandler: (binding: UserCardLoadingBinding, type: Int) -> Unit
        get() = { _, _ -> }
    override val inflateViewBinding: (viewGroup: ViewGroup) -> UserCardLoadingBinding
        get() = {
            UserCardLoadingBinding.inflate(LayoutInflater.from(it.context), it, false)
        }

    init { updateList(listOf(1,2,3,4,5,6,7,8,9,10)) }
    override fun getItemCount(): Int = 10
}