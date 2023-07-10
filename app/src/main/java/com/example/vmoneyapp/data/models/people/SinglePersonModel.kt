package com.example.vmoneyapp.data.models.people


import com.google.gson.annotations.SerializedName

data class SinglePersonModel(
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("favouriteColor")
    val favouriteColor: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("jobtitle")
    val jobtitle: String? = "",
    @SerializedName("lastName")
    val lastName: String? = ""
)