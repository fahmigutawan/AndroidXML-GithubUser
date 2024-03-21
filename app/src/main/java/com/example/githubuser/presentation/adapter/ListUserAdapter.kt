package com.example.githubuser.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.githubuser.databinding.FragmentListuserListBinding
import com.example.githubuser.databinding.UserCardBinding
import com.example.githubuser.model.UserModel
import com.example.githubuser.util.AppRecyclerViewAdapter

class ListUserAdapter : AppRecyclerViewAdapter<UserCardBinding, UserModel>() {
    override val itemHandler: (binding: UserCardBinding, type: UserModel) -> Unit
        get() = { binding, item ->
            binding.userCardUsernameTv.text = item.login
            binding.userCardAvatarIv.load(item.avatar_url)
        }
    override val inflateViewBinding: (viewGroup: ViewGroup) -> UserCardBinding
        get() = {
            UserCardBinding.inflate(LayoutInflater.from(it.context), it, false)
        }
}