package com.example.vmoneyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import com.example.vmoneyapp.data.remote.PeopleCall
import com.example.vmoneyapp.data.remote.RoomsCall
import com.example.vmoneyapp.data.repository.Repository
import com.example.vmoneyapp.databinding.FragmentRoomsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoomsFragment : Fragment() {
//    @Inject
//    lateinit var repository: Repository
    lateinit var binding: FragmentRoomsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewmodel : RoomsViewModel by viewModels<RoomsViewModel> ()

        binding = FragmentRoomsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        if(!viewmodel.isLoaded) {
            viewmodel.getRoomsData()
        }

        viewmodel.roomsLiveData.observe(viewLifecycleOwner) { roomsData ->
            loadData(roomsData)
        }
//        loadData()

        return binding.root
    }

    private fun loadData(result : List<SingleRoomModel>) {
        CoroutineScope(Dispatchers.Main).launch {
//            val result = repository.getAllRooms()

            binding.txAllRoomsHeader.text = result.size.toString()+" Rooms ("+result.filter { it.isOccupied==false }.size +" Available)"

            binding.rvAllPeople.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RoomsAdapter(result)
            }
        }
    }


}