package com.example.vmoneyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.remote.PeopleCall
import com.example.vmoneyapp.data.repository.Repository
import com.example.vmoneyapp.databinding.FragmentPeopleBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PeopleFragment : Fragment() {

//    @Inject
//    lateinit var repository : Repository
    lateinit var binding:FragmentPeopleBinding
    val currentUser = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val viewmodel : PeopleViewModel by viewModels<PeopleViewModel>()
        // Inflate the layout for this fragment
        binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)

        if (!viewmodel.isLoaded) {
            viewmodel.getPeopleData()
        }

        viewmodel.peopleLiveData.observe(viewLifecycleOwner) { peopleDta ->

            loadData(peopleDta)
        }
//        loadData()

        return binding.root

    }

    private fun loadData(result: List<SinglePersonModel>) {
//            val result = repository.getAllPeople()
//            val result = ApiDetails.apiClient.getAllPeople()
        binding.txAllPeopleHeader.text = result.size.toString() +" Employees"
        binding.txInfoUser.text = (currentUser?.displayName ?: currentUser?.email).toString()



        binding.rvAllPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PeopleAdapter(result)

        }

    }

}

