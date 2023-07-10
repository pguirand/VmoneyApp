package com.example.vmoneyapp.data.remote

import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleCall {

    @GET(ApiDetails.SINGLE_PERSON_END_POINT)
    suspend fun getPersonById(@Path("id") id:String) : SinglePersonModel

    @GET(ApiDetails.ALL_PEOPLE_END_POINT)
    suspend fun getAllPeople() : List<SinglePersonModel>

}