package com.example.githubuser.presentation.list_user

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentListuserBinding
import com.example.githubuser.databinding.FragmentListuserNotfoundBinding
import com.example.githubuser.util.Resource
import kotlinx.coroutines.launch

class ListUserFragment : Fragment(R.layout.fragment_listuser) {
    private lateinit var listUserBinding: FragmentListuserBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listUserBinding = FragmentListuserBinding.inflate(inflater, container, false)
        return listUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<ListUserViewModel>()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResult.collect {
                when (it) {
                    is Resource.Error -> {
                        showNotFound()
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.NotLoadedYet -> {
                        showPreSearch()
                    }

                    is Resource.Success -> {
                        it.data?.let {
                            showNotFound()
                        }
                    }
                }
            }
        }

        listUserBinding
            .fragmentListuserSearchLayout
            .setEndIconOnClickListener {
                viewModel.searchByQuery(listUserBinding.fragmentListuserSearchInput.text.toString())
            }

        listUserBinding
            .fragmentListuserSearchInput
            .setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    viewModel.searchByQuery(listUserBinding.fragmentListuserSearchInput.text.toString())
                    true
                } else false
            }
    }

    fun showNotFound(){
        parentFragmentManager
            .commit {
                replace<ListUserNotfoundFragment>(R.id.fragment_listuser_fragmentcontainer)
            }
    }

    fun showPreSearch(){
        parentFragmentManager
            .commit {
                replace<ListUserPresearchFragment>(R.id.fragment_listuser_fragmentcontainer)
            }
    }
}