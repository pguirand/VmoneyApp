package com.example.vmoneyapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetails {

    const val BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/"
    const val SINGLE_PERSON_END_POINT = "people/{id}"
    const val ALL_PEOPLE_END_POINT = "people"
    const val SINGLE_ROOM_ENDPOINT = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/rooms/{id}"
    const val ALL_ROOMS_END_POINT = "rooms"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiClient = retrofitInstance.create(PeopleCall::class.java)
}