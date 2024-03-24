package com.example.githubuser.presentation.ui.list_user

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentListuserBinding
import com.example.githubuser.presentation.adapter.list_user.ListUserAdapter
import com.example.githubuser.presentation.adapter.list_user.ListUserLoadingAdapter
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
        val listAdapter = ListUserAdapter(
            onItemClick = { username ->
                findNavController().navigate(
                    ListUserFragmentDirections
                        .actionListUserFragmentToDetailUserFragment(username)
                )
            }
        )
        val listLoadingAdapter = ListUserLoadingAdapter()

        listUserBinding
            .fragmentListuserList
            .fragmentListuserListRv
            .layoutManager = LinearLayoutManager(this.requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResult.collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        showNotFound()
                    }

                    is Resource.Loading -> {
                        showList()
                        listUserBinding
                            .fragmentListuserList
                            .fragmentListuserListRv
                            .apply {
                                adapter = listLoadingAdapter
                            }
                    }

                    is Resource.NotLoadedYet -> {
                        showPreSearch()
                    }

                    is Resource.Success -> {
                        resource.data?.let {
                            if (it.items.isEmpty()) {
                                showNotFound()
                            } else {
                                showList()
                                listUserBinding
                                    .fragmentListuserList
                                    .fragmentListuserListRv
                                    .apply {
                                        listAdapter.updateList(it.items)
                                        adapter = listAdapter
                                    }
                            }
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
            .setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.searchByQuery(listUserBinding.fragmentListuserSearchInput.text.toString())
                }

                return@setOnEditorActionListener false
            }
    }

    fun showNotFound() {
        listUserBinding.apply {
            fragmentListuserList.root.visibility = View.GONE
            fragmentListuserPresearch.root.visibility = View.GONE
            fragmentListuserNotfound.root.visibility = View.VISIBLE
        }
    }

    fun showPreSearch() {
        listUserBinding.apply {
            fragmentListuserList.root.visibility = View.GONE
            fragmentListuserPresearch.root.visibility = View.VISIBLE
            fragmentListuserNotfound.root.visibility = View.GONE
        }
    }

    fun showList() {
        listUserBinding.apply {
            fragmentListuserList.root.visibility = View.VISIBLE
            fragmentListuserPresearch.root.visibility = View.GONE
            fragmentListuserNotfound.root.visibility = View.GONE
        }
    }
}