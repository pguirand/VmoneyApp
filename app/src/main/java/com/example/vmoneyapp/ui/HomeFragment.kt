package com.example.vmoneyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vmoneyapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.NonDisposableHandle.parent

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    val currentUser = Firebase.auth.currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.txHomeUser.text = (currentUser?.displayName ?: currentUser?.email).toString()
        // Inflate the layout for this fragment


        return binding.root
    }


}