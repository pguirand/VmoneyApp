package com.example.vmoneyapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import com.example.vmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val roomsLiveData : MutableLiveData<List<SingleRoomModel>> by lazy {
        MutableLiveData<List<SingleRoomModel>>()
    }

    var isLoaded = false

    fun getRoomsData() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getAllRooms()
            roomsLiveData.postValue(result)

            isLoaded = true

        }
    }
}