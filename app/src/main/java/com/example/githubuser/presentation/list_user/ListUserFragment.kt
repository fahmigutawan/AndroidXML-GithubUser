package com.example.githubuser.presentation.list_user

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentListUserBinding

class ListUserFragment : Fragment(R.layout.fragment_list_user) {
    private lateinit var listUserBinding: FragmentListUserBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listUserBinding = FragmentListUserBinding.inflate(inflater, container, false)
        return listUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listUserBinding
            .fragmentListUserSearchLayout
            .setEndIconOnClickListener {
                //TODO Handle search
            }
    }


}