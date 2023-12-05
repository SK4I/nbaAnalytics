package com.example.network_services.network.models

import com.google.gson.annotations.SerializedName

data class Teams (

    @SerializedName("home" ) var home : Home? = Home(),
    @SerializedName("away" ) var away : Away? = Away()

)
