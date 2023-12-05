package com.example.network_services.network.models

import com.google.gson.annotations.SerializedName

data class Scores (

    @SerializedName("home" ) var home : HomeScore? = HomeScore(),
    @SerializedName("away" ) var away : AwayScore? = AwayScore()

)
