package com.example.vmoneyapp.data.repository

import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import com.example.vmoneyapp.data.remote.PeopleCall
import com.example.vmoneyapp.data.remote.RoomsCall
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val peopleCall: PeopleCall,
    val roomsCall: RoomsCall
): Repository{
    override suspend fun getPersonById(id: String) = peopleCall.getPersonById(id)

    override suspend fun getAllPeople() = peopleCall.getAllPeople()

    override suspend fun getRoomById(id: Int) =  roomsCall.getRoomById(id)

    override suspend fun getAllRooms() =  roomsCall.getAllRooms()

}