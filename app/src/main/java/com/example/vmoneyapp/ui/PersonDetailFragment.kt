package com.example.vmoneyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.vmoneyapp.R
import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.remote.PeopleCall
import com.example.vmoneyapp.data.repository.Repository
import com.example.vmoneyapp.databinding.FragmentPersonDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PersonDetailFragment : Fragment() {

//    @Inject
//    lateinit var repository: Repository
    lateinit var binding:FragmentPersonDetailBinding
    lateinit var personId : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personId = arguments?.getString(("person_id")).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewmodel : PersonDetailViewModel by viewModels<PersonDetailViewModel>()

        binding = FragmentPersonDetailBinding.inflate(inflater, container, false)

        if(!viewmodel.isLoaded) {
            viewmodel.getSinglePersonData(personId)
        }

        viewmodel.singlePersonLiveData.observe(viewLifecycleOwner) { singlePersonData ->
            loadData(singlePersonData)

        }

//        loadData()



        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_person_detail, container, false)
    }

    private fun loadData(result : SinglePersonModel) {

        CoroutineScope(Dispatchers.Main).launch {
//            val result = personId?.let { result.getPersonById(it) }

            binding.apply {
                txPersonDetailFullName.text = result?.firstName + " "+ result?.lastName
                txPersonDetailFirstName.text = result?.firstName
                txPersonDetailLastName.text = result?.lastName
                textPersonDetailEmail.text = result?.email
                textPersonDetailDate.text = result?.createdAt
                textPersonDetailFavColor.text = result?.favouriteColor
                textPersonDetailJobTitle.text = result?.jobtitle

                Glide
                    .with(requireContext())
                    .load(result?.avatar)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivPersonDetail)

            }

        }
    }

}