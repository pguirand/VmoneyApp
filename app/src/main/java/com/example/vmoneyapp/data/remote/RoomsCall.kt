package com.example.vmoneyapp.data.remote

import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomsCall {


    @GET(ApiDetails.SINGLE_ROOM_ENDPOINT)
    suspend fun getRoomById(@Path("id") id:Int) : SingleRoomModel

    @GET(ApiDetails.ALL_ROOMS_END_POINT)
    suspend fun getAllRooms() : List<SingleRoomModel>
}