package com.example.network_services.network.models

import com.google.gson.annotations.SerializedName

data class Away (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("logo" ) var logo : String? = null

)
