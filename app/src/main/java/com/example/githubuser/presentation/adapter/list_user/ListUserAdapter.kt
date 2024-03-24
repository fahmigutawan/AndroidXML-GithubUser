package com.example.githubuser.presentation.adapter.list_user

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.githubuser.databinding.FragmentListuserListBinding
import com.example.githubuser.databinding.UserCardBinding
import com.example.githubuser.model.UserModel
import com.example.githubuser.util.AppRecyclerViewAdapter

class ListUserAdapter(
    private val onItemClick:(username: String) -> Unit
) : AppRecyclerViewAdapter<UserCardBinding, UserModel>() {
    override val itemHandler: (binding: UserCardBinding, type: UserModel) -> Unit
        get() = { binding, item ->
            binding.userCardUsernameTv.text = item.login
            binding.userCardAvatarIv.load(item.avatar_url)
            binding.userCardContainer.setOnClickListener {
                onItemClick(item.login)
            }
        }
    override val inflateViewBinding: (viewGroup: ViewGroup) -> UserCardBinding
        get() = {
            UserCardBinding.inflate(LayoutInflater.from(it.context), it, false)
        }
}