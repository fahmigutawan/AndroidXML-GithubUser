package com.example.githubuser.presentation.ui.list_user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.Repository
import com.example.githubuser.model.search.SearchModel
import com.example.githubuser.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListUserViewModel : ViewModel() {
    val searchResult = MutableStateFlow<Resource<SearchModel?>>(Resource.NotLoadedYet())

    fun searchByQuery(query: String){
        viewModelScope.launch {
            Repository.searchByQuery(query).collect{

                searchResult.value = it
            }
        }
    }
}