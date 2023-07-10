package com.example.vmoneyapp.data.repository

import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import retrofit2.http.Path

interface Repository {

    suspend fun getPersonById(@Path("id") id:String) : SinglePersonModel

    suspend fun getAllPeople() : List<SinglePersonModel>

    suspend fun getRoomById(@Path("id") id:Int) : SingleRoomModel

    suspend fun getAllRooms() : List<SingleRoomModel>

}