package com.example.githubuser.presentation.ui.detail_user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailuserBinding
import com.example.githubuser.databinding.FragmentListuserBinding
import com.example.githubuser.util.Resource
import kotlinx.coroutines.launch

var detailUserViewModel: DetailUserViewModel? = null

class DetailUserFragment : Fragment(R.layout.fragment_detailuser) {
    private lateinit var binding: FragmentDetailuserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailuserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val username = DetailUserFragmentArgs.fromBundle(arguments as Bundle).username
        detailUserViewModel = ViewModelProvider(requireActivity()).get()

        detailUserViewModel?.let { viewModel ->
            viewModel.getUserByUsername(username)

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.userResult.collect{
                    when (it) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            showInformationLoading()
                        }
                        is Resource.NotLoadedYet -> {
                            showInformationLoading()
                        }
                        is Resource.Success -> {
                            hideInformationLoading()
                            it.data?.let {
                                binding.fragmentDetailuserInformation.apply {
                                    detailuserInformationAvatar.load(it.avatar_url)
                                    detailuserInformationName.text = it.name
                                    detailuserInformationUsername.text = it.login
                                    detailuserInformationFollower.text = "${it.followers} Follower"
                                    detailuserInformationFollowing.text = "${it.following} Following"
                                }
                            }
                        }
                    }
                }
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailUserViewModel = null
    }

    fun showInformationLoading() {
        binding.apply {
            fragmentDetailuserInformation.root.visibility = View.GONE
            fragmentDetailuserInformationLoading.root.visibility = View.VISIBLE
        }
    }

    fun hideInformationLoading() {
        binding.apply {
            fragmentDetailuserInformation.root.visibility = View.VISIBLE
            fragmentDetailuserInformationLoading.root.visibility = View.GONE
        }
    }
}