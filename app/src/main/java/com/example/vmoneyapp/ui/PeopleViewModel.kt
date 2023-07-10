package com.example.vmoneyapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val peopleLiveData : MutableLiveData<List<SinglePersonModel>> by lazy {
        MutableLiveData<List<SinglePersonModel>>()
    }

    var isLoaded = false

    fun getPeopleData() {

        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getAllPeople()
            peopleLiveData.postValue(result)

            isLoaded = true

        }
    }

}