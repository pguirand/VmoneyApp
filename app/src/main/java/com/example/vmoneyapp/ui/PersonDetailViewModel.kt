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
class PersonDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val singlePersonLiveData : MutableLiveData<SinglePersonModel> by lazy {
        MutableLiveData<SinglePersonModel>()
    }

    var isLoaded = false

    fun getSinglePersonData(id:String) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getPersonById(id)
            singlePersonLiveData.postValue(result)

            isLoaded = true
        }
    }
}