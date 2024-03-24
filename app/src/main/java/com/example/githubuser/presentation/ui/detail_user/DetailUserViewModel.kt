package com.example.githubuser.presentation.ui.detail_user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.Repository
import com.example.githubuser.model.detail.DetailModel
import com.example.githubuser.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailUserViewModel : ViewModel() {
    val userResult = MutableStateFlow<Resource<DetailModel?>>(Resource.NotLoadedYet())

    fun getUserByUsername(username: String){
        viewModelScope.launch {
            Repository.detailByUsername(username).collect{
                userResult.value = it
            }
        }
    }
}